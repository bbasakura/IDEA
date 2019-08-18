package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author itheima
 * @Title: UserController
 * @ProjectName gossip-dubboxDemo-parent
 * @Description: 用户controller
 * @date 2019/7/517:51
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //@Autowired   找spring容器注入这个接口的实现类对象

    //找zookeeper注册中心,远程注入这个接口的实现类对象
    @Reference(timeout = 3000)
    private UserService userService;


    /**
     *  根据用户id,获取用户信息, 返回json数据
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserById")
    public String getUserById(String userId){

       return  userService.getUserById(userId);
    }

}
