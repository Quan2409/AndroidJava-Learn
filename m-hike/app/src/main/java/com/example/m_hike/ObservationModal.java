package com.example.m_hike;

public class ObservationModal {
    private int observationID;
    private int hikeIDForeign;
    private String observationName;
    private String observationTime;
    private String observationComment;

    //Observation Constructor
    public ObservationModal(int ObservationID, int HikeID ,String ObservationName, String ObservationTime, String ObservationComments) {
        this.observationID = ObservationID;
        this.hikeIDForeign = HikeID;
        this.observationName = ObservationName;
        this.observationTime = ObservationTime;
        this.observationComment = ObservationComments;
    }

    //Observation Getter
    public int getObservationID() {
        return observationID;
    }
    public int getHikeIDForeign() {
        return hikeIDForeign;
    }
    public String getObservationName() {
        return observationName;
    }
    public String getObservationTime() {
        return observationTime;
    }
    public String getObservationComment() {
        return observationComment;
    }
}
