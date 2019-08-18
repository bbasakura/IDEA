package com.itheima.Myannotation;


import java.lang.reflect.Field;
//@Myannotation01()
public class Myannotation {


    protected static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.itheima.Myannotation.Myannotation");

        Field nameFiled = aClass.getDeclaredField("name");

        boolean flag = nameFiled.isAnnotationPresent(Myannotation01.class);
        if(flag){
            Myannotation01 annotation = nameFiled.getAnnotation(Myannotation01.class);
            System.out.println(annotation.value());

        }



    }

}
