package com.air.antispider.stream.common.bean

/**
 * 流程类：对应表nh_process_info
  * 包含流程ID/流程的阈值/流程的规则
 * （规则配置集合和阈值参数）
 */
case class FlowCollocation(flowId: String, //流程ID
                           flowName: String, //流程的名字:国庆节
                           rules: List[RuleCollocation], //当前流程的所有规则
                           flowLimitScore: Double = 100, //当前流程的阈值
                           strategyCode:String //流程ID.
                          )
