package com.example.library.model;

public class Map {
    private int floorId;

    private int sloganId;

    private int mapId_0;

    private int mapId_1;

    private String floorName;

    public Map(int floorId, int sloganId, int mapId_0, int mapId_1, String floorName) {
        this.floorId = floorId;
        this.sloganId = sloganId;
        this.mapId_0 = mapId_0;
        this.mapId_1 = mapId_1;
        this.floorName = floorName;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getSloganId() {
        return sloganId;
    }

    public void setSloganId(int sloganId) {
        this.sloganId = sloganId;
    }

    public int getMapId_0() {
        return mapId_0;
    }

    public void setMapId_0(int mapId_0) {
        this.mapId_0 = mapId_0;
    }

    public int getMapId_1() {
        return mapId_1;
    }

    public void setMapId_1(int mapId_1) {
        this.mapId_1 = mapId_1;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
}
