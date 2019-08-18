package spring_09;


import com.itheima.spring_08.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    public  void doPost(HttpServletRequest request, HttpServletResponse response) {
        //传统方式：
        //new service
        //HelloService helloService = new HelloService();
        //spring方式：只要看到new，你就想到spring容器中的<bean>
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext applicationContext =
                (ApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);




        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.sayHello();


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
