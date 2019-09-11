package cn.itcast.data

import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.{SparkConf, TaskContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object SparkKuduDataDemo {


  //插入数据
  def insertData(spark: SparkSession, context: KuduContext, tableName: String ): Unit = {


    // a. 模拟产生数据
    val usersDF: DataFrame = spark.createDataFrame(
      Seq(
        (1001, "zhangsan", 23, "男"),
        (1002, "lisi", 22, "男"),
        (1003, "xiaohong", 24, "女"),
        (1005, "zhaoliu2", 33, "男")
      )
    ).toDF("id", "name", "age", "gender")

    // 插入数据方式一：如果行已经存在，INSERT将不允许插入行（导致失败）
    //context.insertRows(usersDF, tableName)
    // 插入数据方式一：将DataFrame的行插入Kudu表。如果行存在，则忽略插入动作。
    context.insertIgnoreRows(usersDF, tableName)
  }


  //查询数据
  def selectData(sparkSession: SparkSession, context: KuduContext, tableName: String) = {

    // 指定获取的列名称
    val columnProjection: Seq[String] = Seq("id", "name", "age")

    val datasRDD: RDD[Row] = context.kuduRDD(sparkSession.sparkContext, tableName, columnProjection)
    // 数据打印出来
    datasRDD.foreachPartition{iter =>
      iter.foreach{ row =>
        println(s"p-${TaskContext.getPartitionId()}: id = ${row.getInt(0)}" +
          s", name = ${row.getString(1)}, age = ${row.getInt(2)}")
      }
    }
  }

  //update更新数据
  def updateData(sparkSession: SparkSession, context: KuduContext, tableName: String) = {

    // a. 模拟产生数据
    val usersDF: DataFrame = sparkSession.createDataFrame(
      Seq(
        (1001, "zhangsan22", 24, "男"),
        (1002, "lisi22", 23, "男"),
        (1003, "xiaohong11", 24, "女"),
        (1005, "zhaoliu244", 33, "男")
      )
    ).toDF("id", "name", "age", "gender")
    context.updateRows(usersDF, tableName)
  }


  //插入更新数据
  def upsertData(sparkSession: SparkSession, context: KuduContext, tableName: String) = {

    // a. 模拟产生数据
    val usersDF: DataFrame = sparkSession.createDataFrame(
      Seq(
        (1001, "zhangsa风", 24, "男"),
        (1006, "tianqi", 33, "男")
      )
    ).toDF("id", "name", "age", "gender")
    context.upsertRows(usersDF, tableName)



  }

  //删除数据

  def deleteData(sparkSession: SparkSession, context: KuduContext, tableName: String) = {

    import sparkSession.implicits._   //就是一个实例对象

    val usersDF: DataFrame =
      sparkSession.sparkContext.parallelize(List(1006)).toDF("id")
    context.deleteRows(usersDF, tableName)
  }


  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[2]").setAppName(this.getClass.getSimpleName.stripSuffix("$"))

     val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    sparkSession.sparkContext.setLogLevel("WARN")


    val kuduMaster= "hadoop01:7051,hadoop02:7051,hadoop03:7051"

    val kuduContext = new KuduContext(kuduMaster,sparkSession.sparkContext)

    val tableName = "spark_table_test02"

    //插入数据
    //insertData(sparkSession,kuduContext,tableName)

    // 查询数据
    //selectData(sparkSession, kuduContext, tableName)
    // 更新数据
    //updateData(sparkSession, kuduContext, tableName)
    // 插入更新数据
    //upsertData(sparkSession, kuduContext, tableName)
    // 删除数据
    deleteData(sparkSession, kuduContext, tableName)
    // TODO：4、应用结束，关闭资源
    sparkSession.stop()

  }

}
