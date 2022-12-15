package com.example.library.model;

public class Notify {
    private String name;

    private int coverId;

    private int labelId;

    private String start;

    private String end;

    private String location;

    private String introduction;

    public Notify(String name, int coverId, int labelId, String start, String end, String location, String introduction) {
        this.name = name;
        this.coverId = coverId;
        this.labelId = labelId;
        this.start = start;
        this.end = end;
        this.location = location;
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoverId() {
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
