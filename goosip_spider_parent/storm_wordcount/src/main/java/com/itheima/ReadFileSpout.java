package com.itheima;

import java.io.BufferedReader;

/**
 * @ProjectName: goosip_spider_parent
 * @Package: com.itheima
 * @ClassName: ReadFileSpout
 * @Description: 打开文件, 每次读取文件中的一行, 向下游bolt发送这一行内容
 * @Author: sakura
 * @CreateDate: 2019/7/16 0016 17:37
 * @Version: 1.0
 */
public class ReadFileSpout extends BaseRichSpout {


    private BufferedReader bufferedReader = null;

    private SpoutOutputcollector collector = null;


}
