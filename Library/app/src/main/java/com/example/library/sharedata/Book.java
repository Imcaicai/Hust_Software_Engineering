package com.example.library.sharedata;

public class Book {
    private String name;
    private String author;
    private String publish;
    private String location;
    private String id;
    private String state;
    private String form;
    private String ISBN;
    private String theme;
    private String introduction;
    private int collectId;
    private int coverId;

    public Book(String name, String author, String publish, String location, String id, String state, String form, String ISBN, String theme, String introduction, int collectId, int coverId) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.location = location;
        this.id = id;
        this.state = state;
        this.form = form;
        this.ISBN = ISBN;
        this.theme = theme;
        this.introduction = introduction;
        this.collectId = collectId;
        this.coverId = coverId;
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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
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

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public int getCoverId() {
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }
}
