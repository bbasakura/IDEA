package com.itheima.web;

import com.itheima.service.ContactInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del_by_id")
public class DelByIdServlet extends HttpServlet {
    //同理，这一层与service交互，所以大量用到service对象，因此放到成员变量中
    private ContactInfoService service= new ContactInfoService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//删除操作// 获取删除的通讯录的id
        String id = request.getParameter("id");
        service.delById(id);
        // Servlet擅长的是Java代码、业务处理
        // 现在则是删除后再给用户看到效果，所以需要跳转到其他的资源
        // 删除完成后之后，对于用户来说，要看到最新的、已经被删除掉之的通讯录列表
        // 跳转到list.jsp，重新进行遍历，显示
        // 转发？？？
        // 重定向？？？
        // 不可能手动刷新，所以浏览器的url地址会变，所以使用重定向
        response.sendRedirect(request.getContextPath()+"/query_contacts");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
