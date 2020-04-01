package com.wolfpub.models;
import java.sql.Date;
public class Chapter {

    private int chapterID;
    private Date CreationDate;
    private String text;
    private String title;
    private String category;
    private int publicationID;

    public Chapter(int chapterID, Date creationDate, String text, String title, String category, int publicationID)
    {
        this.chapterID = chapterID;
        CreationDate = creationDate;
        this.text = text;
        this.title = title;
        this.category = category;
        this.publicationID = publicationID;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }
}
