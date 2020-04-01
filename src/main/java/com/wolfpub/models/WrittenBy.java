package com.wolfpub.models;

public class WrittenBy {

    private int articleID;
    private int staffID;

    public WrittenBy(int articleID, int staffID) {
        this.articleID = articleID;
        this.staffID = staffID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
