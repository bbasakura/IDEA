/**
 * 
 */
package cn.itcast.b2c.gciantispider.util;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class CharacterUtil {

	/**
	 * 设置编码集为utf-8
	 * @param content
	 * @return
	 */
	public static String utf8(String content){
		if(content==null){
			return null;
		}
		try {
			content = new String(content.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return content;
	}
}
