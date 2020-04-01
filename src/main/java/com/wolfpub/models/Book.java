package com.wolfpub.models;

public class Book {
    private int publicationID;
    private long isbn;
    private int edition;
    private String topic;

    public Book(int publicationID, long isbn, int edition, String topic) {
        this.publicationID = publicationID;
        this.isbn = isbn;
        this.edition = edition;
        this.topic = topic;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
