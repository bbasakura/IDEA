package com.itheima.web;

import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/query_contacts")
public class QueryContactsServlet extends HttpServlet {
    //同理，这一层与service交互，所以大量用到service对象，因此放到成员变量中
    private ContactInfoService service= new ContactInfoService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 分页查询时需要用到5个变量
         *    1. [JSP传输给Servlet]每页显示的数据条数: pageSize(默认为10)
         *    2. [JSP传输给Servlet]当前页码: currentPage(默认为1)
         *    3. [Servlet用于运算]查询时的起始条数: pageOffset
         *    4. [Servlet用于运算]总记录条数: recordCount
         *    5. [Servlet传输给JSP]页数: pageCount
         * 2. 页码数和每页显示多少数据都是页面上用户选择的。需要从前台传递过来
         * 3. [计算规则]页面数：Math.ceil(recordCount / pageSize)
         * 4. [计算规则]起始条数：pageOffset = (pageCount-1)*pageSize;
         */
        // 定义当前页码
        // 先获取浏览器发送的页码的参数
        int currentPage = 1;
        String currentPageParam = request.getParameter("currentPage");
        if (currentPageParam != null) {
            currentPage = Integer.parseInt(currentPageParam);
        }
        // 需要先知道数据库当中，有多少条数据
        int recordCount = service.queryCount();

        // 每页显示的数据条数，先假定每页10条
        int pageSize = 10;
        String pageSizeParam = request.getParameter("pageSize");
        if (pageSizeParam != null) {
            pageSize = Integer.parseInt(pageSizeParam);
        }

        // 这个是Limit第一关键字 = (当前页 - 1) 乘以 一页多少条，因为用户的数字是从1开始
        int pageOffset = (currentPage - 1) * pageSize;

        // 总记录条数
        int pageCount = (int)Math.ceil((double)recordCount / pageSize);

        // 将总记录条数发送给jsp
        request.setAttribute("pageCount", pageCount);

        // 将当前页面的页码数传给jsp
        request.setAttribute("currentPage", currentPage);
        //获取所有的通讯信息

        // 将n条每页，传给jsp
        request.setAttribute("pageSize", pageSize);

        List<ContactInfo> contactInfoList = service.queryAllf(pageOffset, pageSize);
        //得到的集合要在JSP中使用，所以要放到作用域中
        request.setAttribute("contacts", contactInfoList);
        //转发到JSp中
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
