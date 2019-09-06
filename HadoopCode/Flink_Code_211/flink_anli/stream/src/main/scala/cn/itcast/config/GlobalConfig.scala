package cn.itcast.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  *  2019/9/4
  *       加载配文件（application.properties）
  */
object GlobalConfig {

  //会自动resources下面的配置文件
  private val config: Config = ConfigFactory.load()

  //kafka的配置
  val kafkaBroker: String = config.getString("bootstrap.servers")
  val kafkaZk: String = config.getString("zookeeper.connect")
  val kafkaTopic: String = config.getString("input.topic")
  val kafkaGroupID: String = config.getString("group.id")

  //hbase的配置
  val hbaseZk: String = config.getString("hbase.zookeeper.quorum")
  val hbaseRpc: String = config.getString("hbase.rpc.timeout")
  val hbaseOperationTimeout: String = config.getString("hbase.client.operation.timeout")
  val hbaseScanTimeout: String = config.getString("hbase.client.scanner.timeout.period")

}
