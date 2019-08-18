package cn.itcast.springmvc.controller;

import java.util.Arrays;

public class User {

    private Integer id;
    private String userName;
    private String name;
    private Integer age;
    private boolean isMarry;
    private Double income;
    private String[] interests;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isMarry() {
        return isMarry;
    }

    public void setMarry(boolean isMarry) {
        this.isMarry = isMarry;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", isMarry=" + isMarry
                + ", income=" + income + ", interests="
                + Arrays.toString(interests) + "]";
    }



}

