package com.wolfpub.models;

public class PeriodicPublication {

    int publicationID;
    String periodicity;
    int issueNumber;
    String type;
    String category;

    public PeriodicPublication(int publicationID, String periodicity, int issueNumber, String type, String category) {
        this.publicationID = publicationID;
        this.periodicity = periodicity;
        this.issueNumber = issueNumber;
        this.type = type;
        this.category = category;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
