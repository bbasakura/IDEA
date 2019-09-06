package cn.itcast.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  *  2019/9/4
  *       加载配文件（application.properties）
  */
object GlobalConfig {

  //会自动resources下面的配置文件  ------此处不拥在设置Hbase的配置，自动加载的Hbase-site.xml
  private val config: Config = ConfigFactory.load()

  //kafka的配置
  val kafkaBroker: String = config.getString("bootstrap.servers")
  val kafkaZk: String = config.getString("zookeeper.connect")
  val kafkaTopic: String = config.getString("input.topic")
  val kafkaGroupID: String = config.getString("group.id")


}
