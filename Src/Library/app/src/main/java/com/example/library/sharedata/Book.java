package com.example.library.sharedata;

public class Book {
    private String name;
    private String author;
    private String publish;
    private String location;
    private String id;
    private String state;
    private String ISBN;
    private String theme;
    private String introduction;
    private String cover;

    public Book(String name, String author, String publish, String location, String cover, String state, String ISBN, String theme, String introduction) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.location = location;
        this.state = state;
        this.ISBN = ISBN;
        this.theme = theme;
        this.introduction = introduction;
        this.cover=cover;
    }

    public Book(String id,String name,String author,String state,String cover){
        this.id=id;
        this.name=name;
        this.author=author;
        this.state=state;
        this.cover=cover;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
