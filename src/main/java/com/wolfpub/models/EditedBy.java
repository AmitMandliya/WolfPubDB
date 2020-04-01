package com.wolfpub.models;

public class EditedBy {
    private int publicationID;
    private int staffID;

    public EditedBy(int publicationID, int staffID) {
        this.publicationID = publicationID;
        this.staffID = staffID;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
