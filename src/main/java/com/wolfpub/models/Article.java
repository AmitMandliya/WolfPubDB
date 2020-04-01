package com.wolfpub.models;
import java.sql.Date;
public class Article{
    private int articleID;
    private Date creationDate;
    private String text;
    private String title;
    private int publicationID;

    public Article(int articleID, Date creationDate, String text, String title, int publicationID) {
        this.setArticleID(articleID);
        this.setCreationDate(creationDate);
        this.setText(text);
        this.setTitle(title);
        this.setPublicationID(publicationID);
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
