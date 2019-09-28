package cn.itcast.b2c.gciantispider.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 页面跳转控制器
 * 
 */
@Controller
@RequestMapping("/base")
public class BaseController {
    /**
     * 用户管理、权限管理等管理页面跳转
     * @param request
     * @param view
     * @return
     */
    @RequestMapping(value="/{view}")
    public ModelAndView redirect (HttpServletRequest request,@PathVariable String view){
        view = "manage/" + view;
        return new ModelAndView(view);
    }
    
    @RequestMapping(value="/error/{code}")
    public ModelAndView error(HttpServletRequest request,@PathVariable String code){
        String url = request.getParameter("url");
        ModelAndView model = new ModelAndView();
        Map<String,Object> error = new HashMap<String,Object>();
        if(code.equals("403")){
            error.put("code", code);
            error.put("msg", "您无权限访问该页面...");
        }else if(code.equals("404")){
            error.put("code", code);
            error.put("msg", "您访问的页面不存在...");
        }else if(code.equals("500")){
            error.put("code", code);
            error.put("msg", "服务器内部错误...");
        }
        error.put("url", url);
        model.addObject("error", error);
        model.setViewName("error");
        return model;
    }
    
    /**
     * 用户跳转JSP页面
     * 
     * 此方法不考虑权限控制
     * 
     * @param folder
     *            路径
     * @param jspName
     *            JSP名称(不加后缀)
     * @return 指定JSP页面
     */
    @RequestMapping("/to/{folder}/{jspName}")
    public String redirectJsp(HttpServletRequest request, @PathVariable String folder, @PathVariable String jspName, Model mode) {
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        mode.addAttribute("id", id);
        mode.addAttribute("status",status);
        return "/" + folder + "/" + jspName;
    }
    
}
