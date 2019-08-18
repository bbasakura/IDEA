package com.itheima.reflect;

import java.lang.reflect.Field;

/*
* 步骤:
1. 获取字节码对象
2. 创建字节码对象所表示的类的对象
3. 获取字段
4. 暴力访问(访问私有成员时)
5. 操作字段
*/
public class ReflactField {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //1.获取字节码对象
        Class<?> claz = Class.forName("com.itheima.reflect.Student");
        //2.创建字节码对象所代表的对象的类
        Object obj = claz.newInstance();
        //3.获取字段
        Field name = claz.getField("name");
        //4.操作字段 包括赋值和获取
        name.set(obj, "王五");
        System.out.println(name.get(obj));

        System.out.println("================");
        /*//私有方法的获得方法和公共字段的获得方法不同
        这点字和方法想似
        方法的是：
        claz.getMethod();
        和
        claz.getDeclaredMethod();
        注意传的参数
         invoke*/

        Field name1 = claz.getDeclaredField("name");
        name1.setAccessible(true);
        name1.set(obj, "一二一");
        System.out.println(name1.get(obj));

    }
}
