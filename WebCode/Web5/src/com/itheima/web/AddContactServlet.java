package com.itheima.web;

import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactInfoService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/add_contact")
public class AddContactServlet extends HttpServlet {
    //同理，这一层与service交互，所以大量用到service对象，因此放到成员变量中
    private ContactInfoService service= new ContactInfoService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 提交的参数里面有中文，所以需要解决请求的乱码问题
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        ContactInfo newContact = new ContactInfo();
        // 借助BeanUtils，把参数当中的所有与用户相关的参数，设置到JavaBean当中
        try {
            BeanUtils.populate(newContact,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 参数需要的是一个ContactInfo JavaBean,所以需要提前设置好这个JavaBean
        service.addContact(newContact);
        // 当添加成功之后，需要回到query_contacts页面
        // 转发
        // 重定向
        response.sendRedirect(request.getContextPath()+"/query_contacts");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
