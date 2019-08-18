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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    //同理，这一层与service交互，所以大量用到service对象，因此放到成员变量中
    private ContactInfoService service = new ContactInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 因为表单里面会有通讯录的姓名，所以中文乱码要解决
        request.setCharacterEncoding("utf-8");
        //为啥是Map，能用单个对象的Bean？
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 创建"被更新的用户"
        ContactInfo updateContact = new ContactInfo();
        try {
            BeanUtils.populate(updateContact, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 通过service更新通讯录
        // 因为ContactInfo里边该有的字段，都有，所以直接向service传输一个JavaBean
        service.updateContact(updateContact);

        // 更新好了之后，需要跳转到查询页面
        // 因为UpdateServlet没有任何的东西向Servlet或jsp传输，所以跳转可以选择重定向
        response.sendRedirect(request.getContextPath() + "/query_contacts");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
