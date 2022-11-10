package com.example.library.sharedata;

public class Borrow {
    private String borrowId;
    private String boTime;
    private String bookId;
    private String userId;
    private String reTime;
    private String state;

    public Borrow(){
        super();
    }

    public Borrow(String borrowId, String boTime, String bookId, String userId, String reTime, String state) {
        this.borrowId = borrowId;
        this.boTime = boTime;
        this.bookId = bookId;
        this.userId = userId;
        this.reTime = reTime;
        this.state = state;
    }

    public Borrow(String boTime, String bookId, String userId, String reTime, String state) {
        this.boTime = boTime;
        this.bookId = bookId;
        this.userId = userId;
        this.reTime = reTime;
        this.state = state;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBoTime() {
        return boTime;
    }

    public void setBoTime(String boTime) {
        this.boTime = boTime;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReTime() {
        return reTime;
    }

    public void setReTime(String reTime) {
        this.reTime = reTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
