package com.itheima.Hive;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: HIveTest
 * @Package: com.itheima.Hive
 * @ClassName: dateTransUDF
 * @Description:  /**
 * 实现Hive的自定义函数：将 15/Aug/2017:18:00:00 =>   yyyy-MM-dd HH:mm:ss
 *      UDF:一进一出
 *      UDAF（聚合）：多进一出
 *      UDTF：一进多出
 * @CreateDate: 2019/8/4 0004 21:57
 * @Version: 1.0
 * Locale.ENGLISH-----指定时区
 */

public class dateTransUDF extends UDF{

    private Text outputDate = new Text();
    private SimpleDateFormat input = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    private SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);

    public Text evaluate (Text inputDate) throws ParseException, ParseException {
        //解析输入的日期
        Date parse = input.parse(inputDate.toString());
        //格式化
        String format = output.format(parse);
        this.outputDate.set(format);
        return outputDate;
    }



}
