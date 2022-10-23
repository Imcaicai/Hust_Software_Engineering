package com.example.library.bean;

public class RegisterBodyBean {
    private String userName;
    private String userId;
    private String password;
    private String userMajor;

    public RegisterBodyBean(String userName, String userId, String password, String userMajor) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.userMajor = userMajor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }
}


