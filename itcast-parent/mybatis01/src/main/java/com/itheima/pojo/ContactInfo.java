package com.itheima.pojo;



public class ContactInfo {
    // 编号
    private String id;

    // 姓名
    private String name;

    // 性别
    private String gender;

    // 生日
    private long birthday;

    // 籍贯
    private String birthplace;

    // 手机
    private String mobile;

    // 邮箱
    private String email;

    public ContactInfo() {
    }

    public ContactInfo(String id, String name, String gender, long birthday, String birthplace, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.mobile = mobile;
        this.email = email;
    }

    public ContactInfo(String name, String gender, long birthday, String birthplace, String mobile, String email) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.mobile = mobile;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", birthplace='" + birthplace + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public long getAge() {
        long now = System.currentTimeMillis();
        return (now - birthday) / 1000 / 60 / 60 / 24 / 365;
    }

    public void setFormatBirthday(String formatBirthday) {
        birthday = DateUtil.transStringToDate(formatBirthday).getTime();
    }

    public String getFormatBirthday() {
        return DateUtil.transDateToString(birthday);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
