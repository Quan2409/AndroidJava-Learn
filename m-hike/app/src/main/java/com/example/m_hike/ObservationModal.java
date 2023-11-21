package com.example.m_hike;

public class ObservationModal {
    private int obs_id;
    private String obs_name;
    private String obs_time;
    private String obs_comment;

    //Observation Constructor
    public ObservationModal(int obs_id, String obs_name, String obs_time, String obs_comment) {
        this.obs_id = obs_id;
        this.obs_name = obs_name;
        this.obs_time = obs_time;
        this.obs_comment = obs_comment;
    }

    //Observation Getter and Setter
    public int getObs_id() {
        return obs_id;
    }

    public void setObs_id(int obs_id) {
        this.obs_id = obs_id;
    }

    public String getObs_name() {
        return obs_name;
    }

    public void setObs_name(String obs_name) {
        this.obs_name = obs_name;
    }

    public String getObs_time() {
        return obs_time;
    }

    public void setObs_time(String obs_time) {
        this.obs_time = obs_time;
    }

    public String getObs_comment() {
        return obs_comment;
    }

    public void setObs_comment(String obs_comment) {
        this.obs_comment = obs_comment;
    }
}
