package com.itheima.user;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author itheima
 * @Title: UserServiceImpl
 * @ProjectName gossip-dubboxDemo-parent
 * @Description: 服务的实现类
 * @date 2019/7/517:23
 */
     //使用dubbo的注解, 将当前服务注册到注册中心上
    //dubbo的注解既有spring注解的功能,还有注册到注册中心的功能
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserById(String id) {
        return "铁蛋" + id;
    }
}
