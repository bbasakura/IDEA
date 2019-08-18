package com.itheima.loginDemo;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
   private static List<User> userList = new ArrayList<User>();
   static {

       ArrayList<String> list1 = new ArrayList<>();
       list1.add("1");
       User user1 = new User();
       user1.setNickname("一号");
       user1.setUsername("哈哈哈");
       user1.setPassword("111");
       user1.setList(list1);
       userList.add(user1);

       ArrayList<String> list2 = new ArrayList<>();
       list2.add("2");
       list2.add("3");
       User user2 = new User();
       user2.setNickname("二号");
       user2.setUsername("嘻嘻嘻");
       user2.setPassword("111");
       user2.setList(list2);
       userList.add(user2);

       ArrayList<String> list3 = new ArrayList<>();
       User user3 = new User();
       list3.add("1");
       list3.add("2");
       list3.add("3");
       list3.add("4");
       user3.setNickname("三号");
       user3.setUsername("啦啦啦");
       user3.setPassword("111");
       user3.setList(list3);
       userList.add(user3);


   }
    public  static List<User> getUserList(){
        return userList;
    }
}
