package cn.itcast.b2c.gciantispider.util;

import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.pageUtil.LinkJsonVO;

import com.alibaba.fastjson.JSON;

/**
 *将redis传过来的json字符串解析成JsonVO
 */
public class JsonResolveUtil {
    
    public static JsonVO resolveJson(String value) {
        if(null==value|| value==""){
            return null;
        }
        JsonVO jsonVO = new JsonVO();
        jsonVO = JSON.parseObject(value, JsonVO.class);
        return jsonVO;
    }
    public static LinkJsonVO resolveLinkJson(String value) {
        if(null==value|| value==""){
            return null;
        }
        LinkJsonVO jsonVO = new LinkJsonVO();
        jsonVO = JSON.parseObject(value, LinkJsonVO.class);
        return jsonVO;
    }
}
