package com.wolfpub.models;
import java.sql.Date;
public class Article{
    int articleID;
    Date creationDate;
    String text;
    String title;
    int publicationID;

    public Article(int articleID, Date creationDate, String text, String title, int publicationID) {
        this.articleID = articleID;
        this.creationDate = creationDate;
        this.text = text;
        this.title = title;
        this.publicationID = publicationID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }


}
