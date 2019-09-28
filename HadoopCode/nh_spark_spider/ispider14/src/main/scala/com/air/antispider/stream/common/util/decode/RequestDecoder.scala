package com.air.antispider.stream.common.util.decode

import java.net.URLDecoder

/**
 * Created by panyx on 2017/6/22.
 */
object RequestDecoder {
  /**
   * 解码POST请求中，request_body中的json数据
    *@param encoded 解码串
   */
  def decodePostRequest(encoded: String): String = {
    val decoded = URLDecoder.decode(encoded,"utf-8")
    decoded
  }

}
