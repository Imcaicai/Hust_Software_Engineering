package com.example.library.model;

public class LostAndFound {
    private String name;

    private int imageId;

    private String time;

    private String location;

    private String remark;

    public LostAndFound(String name, int imageId, String time, String location, String remark) {
        this.name = name;
        this.imageId = imageId;
        this.time = time;
        this.location = location;
        this.remark = remark;
    }


    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getRemark() {
        return remark;
    }
}
