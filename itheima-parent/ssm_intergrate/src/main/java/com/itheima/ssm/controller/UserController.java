package com.itheima.ssm.controller;


import com.itheima.ssm.pojo.User;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("showuser")
    public String toUser(Model model, @RequestParam("id") long id) {


        User user = userService.fiundUserById(id);

        model.addAttribute("user", user);

        return "user";
    }


    @RequestMapping("deleteuser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "user";
    }

}
