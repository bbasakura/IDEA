package com.itheima.web;

import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_pre")
public class UpdatePreServlet extends HttpServlet {

    //同理，这一层与service交互，所以大量用到service对象，因此放到成员变量中
    private ContactInfoService service = new ContactInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收通讯录的id
        String contactId = request.getParameter("contactId");

// 将要更新的通讯录JavaBean获取到
        ContactInfo updateContact = service.getContact(contactId);

        // 将获取到的JavaBean传输给JSP
        request.setAttribute("contact", updateContact);

        // 跳转到jsp
        // 重定向/转发
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
