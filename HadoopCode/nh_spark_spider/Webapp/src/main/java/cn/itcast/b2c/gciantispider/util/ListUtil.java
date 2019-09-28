package cn.itcast.b2c.gciantispider.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    /**
     * 将list里面的空值，null去掉
     * @param list
     * @return
     */
    public static List<String> normalList(List<String> list) {
        List<String> tempList = new ArrayList<String>();
        if (null==list || 0==list.size()) {       
            return null;
        }else{
            for (String str : list) {
                if (!"".equals(str) && null!=str) {
                    tempList.add(str);
                }
            }
            return tempList;
        }    
    }
}
