package com.itheima;

import java.util.*;

/**
 * @author itheima
 * @Title: HashMapSortTest
 * @ProjectName gossip-parent
 * @Description: 对hashMap进行排序的测试(按照hashMap的value进行排序(升序或者降序))
 * @date 2019/7/1615:56
 */
public class HashMapSortTest {


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("d", 2);
        map.put("c", 1);
        map.put("b", 4);
        map.put("a", 3);


        //第三种遍历map的方式
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
        }



        //hashMap转换成list结构
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());


        System.out.println();

        // 对HashMap中的 value 进行排序:  需要给一个比较器
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return -(o1.getValue()).toString().compareTo(o2.getValue().toString());
            }
        });

        // 对HashMap中的 value 进行排序后  显示排序结果
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id + "  ");
        }

    }
}
