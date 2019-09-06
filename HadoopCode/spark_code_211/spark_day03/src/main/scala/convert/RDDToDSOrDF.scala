package convert

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}



/**
  * 将RDD转换转换成DS或者DF
  */
object RDDToDSOrDF {

  def main(args: Array[String]): Unit = {
    // TODO: 1、构建SparkSession实例对象这是一个方法

    val spark: SparkSession = {
      val session: SparkSession = SparkSession.builder()
        .master("local[3]")
        .appName(this.getClass.getSimpleName.stripSuffix("$"))
        .getOrCreate()
      //设置日志级别
      session.sparkContext.setLogLevel("WARN")
      //返回去一个sparkSession的实例对象
      session

    }
    import spark.implicits._        //spark是一个对象  =====val spark: SparkSession

    //2.读取数据，从本地文件系统------多此一举，读出的是一个dataset??????

    val rawsRDD: RDD[String] = spark.read.textFile("datas/ml-100k/u.data").rdd

    //3.采用反射的方式将RDD转换成DataFrame
    /**
      * 1:RDD[caseClass]
      * 2.RDD.toDF ,RDD.toDS
      */

    var mlsRDD: RDD[MovieRating] =rawsRDD.mapPartitions { iter =>
      iter.map { line =>
        //按照制表符分割，之后存入Arrays
        val Array(userId, itemId, rating, timestamp) = line.trim.split("\\t")

        //之后返回的是一个MovieRating的Case 的对象
        MovieRating(userId, itemId, rating.toDouble, timestamp.toLong)
      }
    }
    //隐式转换
    val mlsDF: DataFrame = mlsRDD.toDF()


    val mlsDS: Dataset[MovieRating] = mlsRDD.toDS()
    mlsDS.printSchema()
    mlsDS.show(10)
    println("===================================================")


    // TODO: 4、自定义Schema方式
    /*
      a. RDD[Row]
      b. Schema
        StructType -> StructFiled
      c. createDataFrame
     */


    val rowsRDD: RDD[Row] = rawsRDD.mapPartitions{ iter =>
      iter.map{ line =>
        // 按照制表符分割
        val Array(userId, itemId, rating, timestamp) = line.trim.split("\\t")
        // 返回MovieRating对象
        Row(userId, itemId, rating.toDouble, timestamp.toLong)
      }
    }
    // 自定义Schema信息  user id | item id | rating | timestamp.
    val mlSchema: StructType = StructType(
      Array(
        StructField("userId", StringType, nullable = true),
        StructField("itemId", StringType, nullable = true),
        StructField("rating", DoubleType, nullable = true),
        StructField("timestamp", LongType, nullable = true)
      )
    )
    // 调用SparkSession中函数
    val ratingsDF: DataFrame = spark.createDataFrame(rowsRDD, mlSchema)
    ratingsDF.printSchema()
    ratingsDF.show(10, truncate = false) // false表示将每列值全部显示，不进行截取

    // 将DataFrame转换为Dataset，需要指定强类型就接口
    val ratingsDS = ratingsDF.as[MovieRating]
    ratingsDS.printSchema()
    ratingsDS.show(10, truncate = false)

    // 应用运行结束，关闭资源
    spark.stop()






  }
}
