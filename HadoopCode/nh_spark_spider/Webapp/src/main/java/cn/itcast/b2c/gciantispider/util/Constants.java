package cn.itcast.b2c.gciantispider.util;

/**
 * 定义常见的常量
 *
 */
public class Constants {
     /**
      * 存储密钥
      */
     public static final String KEY = "_KEY"; 
     /**
      * 存储密钥的键值
      */
     public static final String RSA_KEY = "_RSA_KEY"; 
     /**
      * 用来当前登录用户的账号
      */
     public static final String ACCOUNT = "_account";
     /**
     * 用来当前登录用户的中文名
     */
    public static final String USER_CNNAME = "_user_cnname";
     /**
      * 已登录标记
      */
     public static final String HAVE_LOGIN = "_have_login"; 
     /**
      * 用户权限
      */
     public static final String USER_PERMISSION = "_user_permission";
     /**
      * ajax请求未登录的返回码
      */
     public static final int RETURN_CODE_NOTLOGIN = 666;
     /**
      * 加载系统权限标识
      */
     public static final String LOAD_SYS_PERMISSION = "_load_sys_permisson";
     /**
      * 流程信息改变信息标识
      */
     public static final String PROCESS_CHANGE = "ProcessChangeFlag";
     /**
      * 流量数据的键值表示
      */
     public static final String CSANTI_MONITOR_DP = "CSANTI_MONITOR_DP";
     /**
      * 流量数据的键值表示
      */
     public static final String CSANTI_MONITOR_LP = "CSANTI_MONITOR_LP";
     /**
      * 数据分析速度的键值表示，实时
      */
     public static final String CSANTI_MONITOR_QUERY = "CSANTI_MONITOR_QUERY";
     /**
      * 数据分析速度的键值表示，半实时
      */
     public static final String CSANTI_MONITOR_BOOK = "CSANTI_MONITOR_BOOK";
     /**
      * 当天流量值总和标识
      */
     public static final String CURR_FLOW_SUM = "CURR_FLOW_SUM";
     /**
      * 数据处理，规则发生变化时的标识
      */
     public static final String ANALYZERULE_CHANGE = "AnalyzeRuleChangeFlag";
     
}     
