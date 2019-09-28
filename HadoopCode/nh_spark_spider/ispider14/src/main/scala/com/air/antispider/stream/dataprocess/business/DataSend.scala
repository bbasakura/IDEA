package com.air.antispider.stream.dataprocess.business

import com.air.antispider.stream.common.bean.ProcessedData
import com.air.antispider.stream.common.util.jedis.PropertiesUtil
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.rdd.RDD

/**
  * 数据推送工具类
  */
object DataSend {

  /**
    * 数据推送到Kafka
    *
    * @param processedDataRDD
    */
  def sendData(processedDataRDD: RDD[ProcessedData]) = {
//    1. 我们当前ProcessedData里面有RequestType,RequestType里面就有我们当前的操作类型:查询/预订.
//    2. 我们会根据当前的查询/预订将数据发送到不同的Topic中.
    //发送查询数据
    sendToKafa(processedDataRDD, 0)
    //发送预订数据
    sendToKafa(processedDataRDD, 1)

  }

  /**
    * 根据指定的类型,将数据发送给指定的topic
    * @param processedDataRDD
    * @param topicType
    */
  def sendToKafa(processedDataRDD: RDD[ProcessedData], topicType: Int) = {
    //    3. 直接使用Filter将我们需要的数据过滤出来.
    val filterRDD: RDD[ProcessedData] = processedDataRDD.filter(processedData => {
      //看本条消息时一个查询还是预订.
      processedData.requestType.behaviorType.id == topicType
    })
    //我们要发送的Topic
    var topic = ""
    //查询数据的 topic：target.query.topic = processedQuery
    val queryTopic = PropertiesUtil.getStringByKey("target.query.topic", "kafkaConfig.properties")
    val bookTopic = PropertiesUtil.getStringByKey("target.book.topic", "kafkaConfig.properties")
    //判断本次发送的Topic
    if (topicType == 0) {
      topic = queryTopic
    } else if (topicType == 1) {
      topic = bookTopic
    }

    //创建 map 封装 kafka 参数
    val props = new java.util.HashMap[String, Object]()
    //设置 brokers
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,PropertiesUtil.getStringByKey("default.brokers", "kafkaConfig.properties"))
    //key 序列化方法
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, PropertiesUtil.getStringByKey("default.key_serializer_class_config", "kafkaConfig.properties"))
    //value 序列化方法
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,PropertiesUtil.getStringByKey("default.value_serializer_class_config", "kafkaConfig.properties"))
    //批发送设置：100 作为一批次或 10ms 作为一批次 ,两个条件,如果有任何一个达到,都会发送.
    props.put(ProducerConfig.BATCH_SIZE_CONFIG,PropertiesUtil.getStringByKey("default.batch_size_config", "kafkaConfig.properties"))
    props.put(ProducerConfig.LINGER_MS_CONFIG,PropertiesUtil.getStringByKey("default.linger_ms_config", "kafkaConfig.properties"))


    //按照分区进行遍历
    filterRDD.foreachPartition(iter => {
      //创建Kafka生产者对象
      val producer = new KafkaProducer[String, String](props)
      //遍历迭代器,发送消息
      iter.foreach(data => {
        //    4. 将ProcessedData转换为字符串.我们可以使用Json.使用字符串分割#CS#
        val message: String = data.toKafkaString()
        println(message)
        //    5. 将消息发送给Kafka.
        producer.send(new ProducerRecord[String, String](topic, message))
      })
      //关闭连接
      producer.close()
    })

    }
}
