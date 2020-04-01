package com.wolfpub.models;

public class PeriodicPublication {

    private int publicationID;
    private String periodicity;
    private int issueNumber;
    private String type;
    private String category;

    public PeriodicPublication(int publicationID, String periodicity, int issueNumber, String type, String category) {
        this.setPublicationID(publicationID);
        this.setPeriodicity(periodicity);
        this.setIssueNumber(issueNumber);
        this.setType(type);
        this.setCategory(category);
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
