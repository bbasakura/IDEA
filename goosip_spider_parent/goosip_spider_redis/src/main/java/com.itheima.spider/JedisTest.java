package com.itheima.spider;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 这是Jedis的测试方法
 */
public class JedisTest {

    public static final String host = "192.168.72.142";

    public static final Integer port = 6379;
    public Jedis jedis;

    @Before
    public void init() {
//        创建jedis的链接对象

        jedis = new Jedis(host, port);

    }

//    @Test
//    public void testJedis() {
////        创建jedis的对象
//
//        Jedis jedis = new Jedis(host, port);
//
//
////        测试ping
//
//        String ping = jedis.ping();
//
//        System.out.println("ping = " + ping);
//
//    }
//
//
//    @Test
//    public void testOfString()  {
//
////1.设值之前先判断
//        if (jedis.exists("name")) {
//            jedis.del("name");
//
//        }
//        if (jedis.exists("age")) {
//            jedis.del("age");
//
//        }
////2.设值
//        jedis.set("name", "nihao");
//        jedis.set("age", "13");
//
////3.取值
//        String name = jedis.get("name");
//
//        System.out.println("name = " + name);
//
//        String age = jedis.get("age");
//
//        System.out.println("age = " + age);
//
//
////        加一 减一  加几 减几  计数器   针对可以转换成整数的字符串
//
//        Long age1 = jedis.incr("age");
//
//        System.out.println("age1 = " + age1);
//
//        Long age2 = jedis.decr("age");
//
//        System.out.println("age2 = " + age2);
//
//        Long age3 = jedis.incrBy("age", 68);
//
//
//        System.out.println("age3 = " + age3);
//
//        Long age4 = jedis.decrBy("age", 78);
//
//        System.out.println("age4 = " + age4);
//
////        设置存活时间
//
//
//        String setex = jedis.setex("nini", 30, "zz");
//
//        while(jedis.exists("nini")){
//
//            Long ll = jedis.ttl("nini");
//
//
//            System.out.println(ll);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        jedis.close();
//
//
//    }


    /**
     * list队列
     *
     */

/*    @Test
    public  void testOfList(){
        if(jedis.exists("list1")){
            jedis.del("list1");
        }

//          设值
        Long lpush = jedis.lpush("list1", "A", "B", "C", "D", "E");

//        从右侧弹出元素，这个元素就不见l
        String rpop = jedis.rpop("list1");
        System.out.println("rpop = " + rpop);

//        获取一个范围内的元素

        List<String> list1 = jedis.lrange("list1", 0, 1);

        for (String s : list1) {
            System.out.println("s = " + s);
        }


//        索取元素的个数


        Long list11 = jedis.llen("list1");


        System.out.println("list11 = " + list11);



//        插入元素，在C的后面插入一个W

        Long linsert = jedis.linsert("list1", BinaryClient.LIST_POSITION.BEFORE, "C", "W");


        Long linsert1 = jedis.linsert("list1", BinaryClient.LIST_POSITION.AFTER, "B", "W");

        List<String> list12 = jedis.lrange("list1", 0, -1);

        for (String s : list12) {
            System.out.println("s = " + s);
        }

//       刪除立面的元素

        Long lrem = jedis.lrem("list1", 1, "W");

        List<String> list13 = jedis.lrange("list1", 0, -1);
        for (String s : list13) {
            System.out.println("ss = " + s);
        }*/
/*
        Long lrem2 = jedis.lrem("list1", 2, "W");

        List<String> list14 = jedis.lrange("list1", 0, -1);
        for (String s : list13) {
            System.out.println("sss = " + s);
        }*/


    /**
     *
     * hashMap 结构
     */

/*
        @Test

    public void testOfHasg(){
            if(jedis.exists("person")){
                jedis.del("person");
            }

//    设值

            Long hset = jedis.hset("person", "name", "老王");
            Long hset1 = jedis.hset("person", "age", "16");
            Long hset2 = jedis.hset("person", "hobby", "chi");


//            取值 获取key，一次性取出多个值
            String name = jedis.hget("person", "name");

            System.out.println("name = " + name);

            String age = jedis.hget("person", "age");

            System.out.println("age = " + age);

            String hobby = jedis.hget("person", "hobby");

            System.out.println("hobby = " + hobby);


            List<String> hmget = jedis.hmget("person", "name", "age");

            for (String s : hmget) {
                System.out.println("s = " + s);

            }


//            获取所有的值，包含key和value


            Map<String, String> person = jedis.hgetAll("person");

            for (String s : person.keySet()) {
                System.out.println("key"+ s + "值："+person.get(s));
            }



//            删除某个字段


            Long hdel = jedis.hdel("person", "hobby");

            Map<String, String> person1 = jedis.hgetAll("person");

            for (String s : person1.keySet()) {

                System.out.println("s = " + s + person.get(s));



            }


        }
*/

    /**
     * set    ----- hashSet     : 无序   不重复         应用场景:  url去重        共同好友的运算
     */

 /*   @Test
    public void testOfSet() {

        Long sadd = jedis.sadd("set1", "a", "b", "c", "d", "e");

        Long sadd2 = jedis.sadd("set2", "a", "b", "f", "h", "j");
//
//        jedis.srem("set1","f");
//        jedis.srem("set1","h");
//        jedis.srem("set1","j");


//        獲取值（一次性獲取所有元素的neiron）

        Set<String> set1 = jedis.smembers("set1");

        for (String s : set1) {
            System.out.println("s = " + s);
        }

//        獲取的元素的個數
        Long set11 = jedis.scard("set1");
        System.out.println("set11 = " + set11);


//交集

        Set<String> sinter = jedis.sinter("set1", "set2");
        for (String s : sinter) {
            System.out.println("s = " + s);

        }
//病集

        Set<String> sunion = jedis.sunion("set1", "set2");

        for (String s : sunion) {

            System.out.println("s = " + s);

        }

        System.out.println("============================");

//        差集


        Set<String> sdiff = jedis.sdiff("set1", "set2");

        for (String s : sdiff) {

            System.out.println("s = " + s);


        }

    }*/


    /**
     * sortedSet数据结构的操作, 场景:  排行榜  热搜榜    特点:  有序   不重复
     */
    @Test
    public void testOfSortedset() {
//        设定值

        Long zadd = jedis.zadd("math", 78, "一号");

        Long zadd1 = jedis.zadd("math", 79, "二号");

        Long zadd2 = jedis.zadd("math", 80, "三号");

        Long zadd3 = jedis.zadd("math", 81, "四号");

//        索取值，小道大 不带score


        Set<String> math = jedis.zrange("math", 0, -1);
        for (String s : math) {
            System.out.println("s = " + s);
        }
        System.out.println("===================");


//        带score
        Set<Tuple> math1 = jedis.zrangeWithScores("math", 0, -1);

        for (Tuple tuple : math1) {

            System.out.println(tuple.getElement()+tuple.getScore());

        }

        System.out.println("=-=======================");


//        大道小


        Set<String> math2 = jedis.zrevrange("math", 0, -1);

        for (String s : math2) {
            System.out.println("s = " + s);

        }

        System.out.println("-========-==================");


        Set<Tuple> math3 = jedis.zrevrangeWithScores("math", 0, -1);

        for (Tuple tuple : math3) {

            System.out.println(tuple.getElement()+tuple.getScore());



        }

//获取某 个元素 的排名  正序  或者 倒序

        Long zrank = jedis.zrank("math", "三号");
        System.out.println("zrank = " + zrank);

        Long zrevrank = jedis.zrevrank("math", "三号");

        System.out.println("zrevrank = " + zrevrank);
        //获取某个元素的分数


        Double zscore = jedis.zscore("math", "四号");

        System.out.println("zscore = " + zscore);




    }


}
