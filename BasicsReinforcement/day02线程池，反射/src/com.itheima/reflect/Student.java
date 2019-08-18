package com.itheima.reflect;

public class Student {
    public String name;
    public int age;
    private  String  id;
    //公有的带参构造
    public Student(String name){
        this.name =name;
    }
    //无参构造
    public Student() {
    }
    //私有的构造方法
    private Student(String name,int age){
        this.name= name;
        this.age= age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
