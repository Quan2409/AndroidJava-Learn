package com.example.m_hike;

public class HikeModal {
    private int id;
    private String name;
    private String location;
    private String length;
    private String level;
    private String date;
    private String parking;
    private String description;

    //Constructor
    public HikeModal(int id, String name, String location, String length, String level, String date, String parking, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.length = length;
        this.level = level;
        this.date = date;
        this.parking = parking;
        this.description = description;
    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
