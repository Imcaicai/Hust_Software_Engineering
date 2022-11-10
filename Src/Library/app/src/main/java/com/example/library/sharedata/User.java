package com.example.library.sharedata;

public class User {
    private String id;
    private String account;
    private String name;
    private String password;



    public User(String id,String account,String name){
        super();
        this.id=id;
        this.account=account;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
