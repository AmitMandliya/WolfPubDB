package com.wolfpub.models;
import java.sql.Date;
public class Publication {
    private int publicationID;
    private String title;
    java.sql.Date publicationDate;
    public Publication(int publicationID, String title, java.sql.Date publicationDate) {
        this.publicationID = publicationID;
        this.title = title;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.sql.Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

}
