package cn.itcast.springmvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();


		mv.addObject("msg","springmvc的第一个程序");//封装模型
		//request.setAttribute("msg","springmvc的第一个程序");
		mv.setViewName("hello");//设置视图名称


		return mv;
	}
}
