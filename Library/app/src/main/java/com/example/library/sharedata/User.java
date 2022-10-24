package com.example.library.sharedata;

public class User {
    private int id;
    private String account;
    private String name;
    private String password;

    public User(String account,String name,String password){
        super();
        this.account=account;
        this.name=name;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "User{id="+id+",account="+account+",name="+name+",password="+password+"}";
    }
}
