package com.example.contact_database;

import android.graphics.Bitmap;

public class PersonModal {
    private String contactName;
    private String contactDOB;
    private String contactEmail;
    private Bitmap contactAvatar;

    //constructor
    public PersonModal(String name, String dob, String email, Bitmap image) {
        this.contactName = name;
        this.contactDOB = dob;
        this.contactEmail = email;
        this.contactAvatar = image;
    }
    //Getter
    public String getName() {
        return contactName;
    }
    public String getDob(){
        return contactDOB;
    }
    public String getEmail() {
        return contactEmail;
    }
    public Bitmap getImage() {
        return contactAvatar;
    }
}
