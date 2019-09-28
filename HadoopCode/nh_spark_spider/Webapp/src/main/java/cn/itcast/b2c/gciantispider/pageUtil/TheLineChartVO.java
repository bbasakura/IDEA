/**
 * 
 */
package cn.itcast.b2c.gciantispider.pageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itheima
 * 制作折线图数据
 */
public class TheLineChartVO {
	private String hours;
	private Float value;
	private Integer Type;
	
	
	/**
	 * @param hours
	 * @param value
	 * @param type
	 */
	public TheLineChartVO(String hours, Float value, Integer type) {
		super();
		this.hours = hours;
		this.value = value;
		Type = type;
	}
	/**
	 * 
	 */
	public TheLineChartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the hours
	 */
	public String getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}
	/**
	 * @return the value
	 */
	public Float getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Float value) {
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		Type = type;
	}
	
	
}
