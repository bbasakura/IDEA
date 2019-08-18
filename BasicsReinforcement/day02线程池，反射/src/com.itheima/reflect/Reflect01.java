package com.itheima.reflect;

public class Reflect01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Student student = new Student();
        //first---类名.class
        Class<Student> cazz1 = Student.class;
        System.out.println(cazz1.getName());
        //第二种，类的对象.class
        Class<? extends Student> cazz2 = student.getClass();
        System.out.println(cazz2.getName());
        System.out.println(cazz2.getSimpleName());
        //第三种，class.forName(相对路径)
        Class<?> cazz3 = Class.forName("com.itheima.reflect.Reflect01");
        System.out.println(cazz3.getName());

    }
}
