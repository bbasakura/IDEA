package cn.itcast.springmvc.controller;

import java.util.List;

public class UserVO {



    private List<User> users;

    public  List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
