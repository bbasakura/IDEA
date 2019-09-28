package cn.itcast.b2c.gciantispider.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 格式化日期工具类
 * 声明:类中凡使用的"_"都表示"-"
 * User: DP
 */
public class DateFormatter {

    private static final SimpleDateFormat YY_MM_DDHHmm = new SimpleDateFormat("yy-MM-dd HH:mm");

    private static final SimpleDateFormat YY_MM_DD = new SimpleDateFormat("yy-MM-dd");

    private static final SimpleDateFormat YYYY_MM_DDHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat YYYY_MM_DDHH24miss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    private static final SimpleDateFormat YY = new SimpleDateFormat("yy");

    private static final SimpleDateFormat MM = new SimpleDateFormat("MM");

    private static final SimpleDateFormat DD = new SimpleDateFormat("dd");

    private static final SimpleDateFormat YYMMDD = new SimpleDateFormat("yyMMdd");

    private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat YYYYMMDDHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat YYYYcnMMcnDDcn = new SimpleDateFormat("yyyy年MM月dd日");

    private static final SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyyMM");

    static {
        YY_MM_DDHHmm.setLenient(false);
        YY_MM_DD.setLenient(false);
        YYYY_MM_DDHHmm.setLenient(false);
        YYYY_MM_DD.setLenient(false);
        YYYY.setLenient(false);
        YY.setLenient(false);
        MM.setLenient(false);
        DD.setLenient(false);
        YYMMDD.setLenient(false);
        YYYYMMDD.setLenient(false);
        YYYYMMDDHHmmss.setLenient(false);
        YYYYcnMMcnDDcn.setLenient(false);
        YYYYMM.setLenient(false);
    }

    /**
     * 得到几号
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 01
     * </pre>
     * @param time  日期
     * @return  几号
     */
    public static String getDDFromLong(Date time) {
        if (time == null) return "";
        return DD.format(time);
    }

    /**
     * 得到几月
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 02
     * </pre>
     * @param time  日期
     * @return  几月
     */
    public static String getMMFromLong(Date time) {
        if (time == null) return "";
        return MM.format(time);
    }

    /**
     * 得到几年
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 2012
     * </pre>
     * @param time  日期
     * @return  几年
     */
    public static String getYYYYFromLong(Date time) {
        if (time == null) return "";
        return YYYY.format(time);
    }

    /**
     * 得到年月
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 201202
     * </pre>
     * @param time 日期
     * @return 年月
     */
    public static String getYYYYMMFromLong(Date time) {
        if (time == null) return "";
        return YYYYMM.format(time);
    }

    /**
     * 得到年月日
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 20120201
     * </pre>
     * @param time 日期
     * @return     年月日
     */
    public static String long2YYYYMMDD(Date time) {
        if (time == null) return "";
        return YYYYMMDD.format(time);
    }

    /**
     * 得到年月日和时间
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 20120201000000
     * </pre>
     * @param time 日期
     * @return     年月日和时间
     */
    public static String long2YYYYMMDDHHmmss(Date time) {
        if (time == null) return "";
        return YYYYMMDDHHmmss.format(time);
    }

    /**
     * 得到年月日和时间
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 2012-02-01 00:00:00
     * </pre>
     * @param time 日期
     * @return     年月日和时间
     */
    public static String long2YYYY_MM_DDHH24miss(Date time) {
        if (time == null) return "";
        return YYYY_MM_DDHH24miss.format(time);
    }

    /**
     * 得到年月日
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 2012_02_01
     * </pre>
     * @param time 日期
     * @return     年月日
     */
    public static String long2YYYY_MM_DD(Date time) {
        if (time == null) return "";
        return YYYY_MM_DD.format(time);
    }
    /**
     * 得到年月日小时,分
     * <pre>
     *      null -> ""
     *      2012-02-01 -> 2012-02-01 00:00
     * </pre>
     * @param time 日期
     * @return     年月日小时分
     */
    public static String long2YYYY_MM_DDHHmm(Date time) {
        if (time == null) return "";
        return YYYY_MM_DDHHmm.format(time);
    }

    public static String long2YYYYcnMMcnDDcn(Date time) {
        if (time == null) return "";
        return YYYYcnMMcnDDcn.format(time);
    }

    public static Timestamp yyyyMMDD2Timestamp(String yyyymmdd) {
        try {
            return new Timestamp(YYYYMMDD.parse(yyyymmdd).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmdd + " ERROR." + e.getMessage());
        }
    }

    public static Timestamp yyyy_MM_DD2Timestamp(String yyyy_mm_dd) {
        try {
            return new Timestamp(YYYY_MM_DD.parse(yyyy_mm_dd).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_mm_dd + " ERROR." + e.getMessage());
        }
    }


    public static Timestamp yyyycnMMcnDDcn2Timestamp(String yyyycnMMcnDDcn) {
        try {
            return new Timestamp(YYYYcnMMcnDDcn.parse(yyyycnMMcnDDcn).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + e.getMessage());
        }
    }
    
    public static Timestamp yyyy_MM_DDHH24miss(String yyyy_mm_ddhh24miss) {
        try {
            return new Timestamp(YYYY_MM_DDHH24miss.parse(yyyy_mm_ddhh24miss).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_mm_ddhh24miss + " ERROR." + e.getMessage());
        }
    }
    

    public static Date yyyycnMMcnDDcn2Date(String yyyycnMMcnDDcn) {
        try {
            return YYYYcnMMcnDDcn.parse(yyyycnMMcnDDcn);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + e.getMessage());
        }
    }


    public static Date yyyyMMDD2Date(String yyyyMMDD) {
        try {
            return new Date(YYYYMMDD.parse(yyyyMMDD).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMMDD + " ERROR." + e.getMessage());
        }
    }

    public static Date yyyy_MM_DD2Date(String yyyy_MM_DD) {
        try {
            return new Date(YYYY_MM_DD.parse(yyyy_MM_DD).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_MM_DD + " ERROR." + e.getMessage());
        }
    }

    public static long yyyycnMMcnDDcn2Long(String yyyycnMMcnDDcn) {
        try {
            return YYYYcnMMcnDDcn.parse(yyyycnMMcnDDcn).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + e.getMessage());
        }
    }

    public static long yyyyMMDDHH24miss2Long(String yyyyMMDDHH24miss) {
        try {
            return YYYY_MM_DDHH24miss.parse(yyyyMMDDHH24miss).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMMDDHH24miss + " ERROR." + e.getMessage());
        }
    }
    
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {
        Date date = null;
        try {
            date = YYYY_MM_DDHHmm.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
     }
    
    
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {
        Date date = null;
        try {
            date = YYYY_MM_DD.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
     }
    
    /**
     * 获取当前的时间
     * @return
     */
    public static String getCurrTime(){
        return YYYY_MM_DDHHmm.format(new Date());
    }
    
    /**
     * 两个时间相减，获取相差天数
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return -1;
        }
        
        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int)(intervalMilli/(24 * 60 * 60 * 1000));
     }
    /**
     * 获取特定日期的前一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay){ 
        Calendar c = Calendar.getInstance(); 
        Date date=null; 
        try { 
        date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
        } catch (ParseException e) { 
        e.printStackTrace(); 
        } 
        c.setTime(date); 
        int day=c.get(Calendar.DATE); 
        c.set(Calendar.DATE,day-1); 
        
        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
        return dayBefore; 
    } 
    
    
   public static void main(String[] args) {
       System.out.println(getSpecifiedDayBefore("2017-06-01"));
   }
}

