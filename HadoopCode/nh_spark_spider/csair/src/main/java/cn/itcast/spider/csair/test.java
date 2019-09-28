package cn.itcast.spider.csair;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(getGoTime().replaceAll("-", ""));
		System.out.println(getBackTime());
	}

	public static String getGoTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String nowAsISO = df.format(new Date());
		return nowAsISO;
	}

	public static String getBackTime() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, +1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
}
