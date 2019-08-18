package com.itheima;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class BooksDe4j {
    public static void main(String[] args) throws Exception {
        //创建度对象
        SAXReader saxReader = new SAXReader();
        //读文件
        Document document = saxReader.read("day03Xml&工具类/books.XML");
        //得到元素
        Element rootElement = document.getRootElement();
        //创建元素的集合
        List<Element> elements = rootElement.elements();
        //遍历集合，拿到的是属性即是---- 作者
        for (Element book : elements) {
            String authername = book.attributeValue("author");
            System.out.println(authername);
            //作者作为一个元素，用来获取其中的文本内容

            Element name = book.element("name");
            Element price = book.element("price");
            System.out.println(name.getText()+"===="+price.getText());

        }
    }
}
