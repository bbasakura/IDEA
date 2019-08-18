package com.itheima.Request_01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/request_body")
public class RequestBodyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传入参数，
            request.setCharacterEncoding("utf-8");
        System.out.println("username"+request.getParameter("username"));
        System.out.println("password"+request.getParameter("password"));
        System.out.println("hobby"+request.getParameterValues("hobby"));
        System.out.println("================================");
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String pname = parameterNames.nextElement();
            System.out.println(pname+request.getParameter(pname));
        }
        System.out.println("================================");
//        得到map集合，再用entrySet(),实现，遍历entryset，得到一个个键值对的对象，entry，
//        在调用entry的getkey()和getvalue()方法实现数据的获取、。
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println(entry.getKey()+ Arrays.toString(entry.getValue()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
    }
}
