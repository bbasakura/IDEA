package cn.itcast.b2c.gciantispider.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    /**
     * 获取当前时间
     * @param hour
     * @return
     */
    public static String getCurrTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }    /**
     * 获取当天零点时间yyyy-MM-dd 00:00:00
     * @param hour
     * @return
     */
    public static String getZeroTime() {
        return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
    }
    /**
     * 获取当前时间之前或之后几小时 hour
     * 
     * @param hour
     * @return
     */
    public static String getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
    /**
     * 获取当前时间之前或之后几分钟 minute
     * @param minute
     * @return
     */
    public static String getTimeByMinute(Timestamp timestamp, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        //return calendar.getTimeInMillis()+"";
    }
    /**
     * 字符串转化成时间格式
     * 
     * @param yyyy_mm_ddhh24miss
     * @return
     */
    public static Timestamp yyyy_MM_DDHH24miss(String yyyy_mm_ddhh24miss) {
        try {
            return new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yyyy_mm_ddhh24miss).getTime());
        }
        catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_mm_ddhh24miss + " ERROR." + e.getMessage());
        }
    }

    /**
     * 生成随机时间
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);   // 构造开始日期
            Date end = format.parse(endDate);       // 构造结束日期
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取两者之间的随意数
     * @param begin
     * @param end
     * @return
     */
    public static long random(long begin, long end) {
        long rtn = begin + (long)(Math.random() * (end-begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn==begin || rtn==end) {
            return random(begin, end);
        }
        return rtn;
    }
    
    /**
     * 获取时间差
     * @param fromDate
     * @param toDate
     * @return
     * @throws ParseException 
     */
    public static long getTimeDiff(String fromDate, String toDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //前的时间   
        Date fd = df.parse(fromDate);  
        //后的时间  
        Date td = df.parse(toDate);  
        //两时间差,精确到毫秒   
        long diff = td.getTime() - fd.getTime();
        return diff;
    }
    /**
     * 获取系统前一天时间(零时整)（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getLastDayTime(){
        Date nowDate = new Date();
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋值给日历
        calendar.setTime(nowDate);
        //设置为前一天
        calendar.add(calendar.DAY_OF_MONTH, -1);
        //得到前一天的时间
        nowDate=calendar.getTime();
        String lastDay = sdf.format(nowDate);
        return lastDay;
    }
    /**
     * 获取系统前一天时间（yyyy-MM-dd）
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getLastDay(){
        Date nowDate = new Date();
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋值给日历
        calendar.setTime(nowDate);
        //设置为前一天
        calendar.add(calendar.DAY_OF_MONTH, -1);
        //得到前一天的时间
        nowDate=calendar.getTime();
        String lastDay = sdf.format(nowDate);
        return lastDay;
    }
    public static void main(String[] args){
        System.out.println(getLastDayTime());
        System.out.println(getZeroTime());
    }

}
