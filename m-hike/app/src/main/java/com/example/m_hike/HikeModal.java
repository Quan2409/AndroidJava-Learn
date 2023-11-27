package com.example.m_hike;

public class HikeModal {
    private int hikeID;
    private String hikeName;
    private String hikeLocation;
    private String hikeLength;
    private String hikeLevel;
    private String hikeDate;
    private String hikeParking;
    private String hikeDescription;

    //Constructor
    public HikeModal(int HikeID, String HikeName , String HikeLocation, String HikeLength, String HikeLevel, String HikeDate, String HikeParking, String HikeDescription) {
        this.hikeID = HikeID;
        this.hikeName = HikeName;
        this.hikeLocation = HikeLocation;
        this.hikeLength = HikeLength;
        this.hikeDate = HikeDate;
        this.hikeLevel = HikeLevel;
        this.hikeParking = HikeParking;
        this.hikeDescription = HikeDescription;
    }

    //Getter
    public int getHikeID() {
        return hikeID;
    }
    public String getHikeName() {
        return hikeName;
    }
    public String getHikeLocation() {
        return hikeLocation;
    }
    public String getHikeLength() {
        return hikeLength;
    }
    public String getHikeLevel() {
        return hikeLevel;
    }
    public String getHikeDate() {
        return hikeDate;
    }
    public String getHikeParking() {
        return hikeParking;
    }
    public String getHikeDescription() {
        return hikeDescription;
    }
}
