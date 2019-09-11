package cn.itcast.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.pojo.User;
import cn.itcast.service.HbaseService;

@Controller
@RequestMapping("/spring")
public class UserController {
	
	@Autowired
	private HbaseService hbaseService;
	
	@RequestMapping("/userList")
	@ResponseBody
	public List<User> showUserList(HttpServletRequest request, HttpServletResponse response,User user){
		List<User> userList = hbaseService.queryUserByCondition(user);
		for (User user1 : userList) {
			System.out.println(user1.getUser_name());
		}
		return userList;
	}
}
