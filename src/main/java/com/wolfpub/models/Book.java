package com.wolfpub.models;

public class Book {
    int publicationID;
    int isbn;
    int edition;
    String topic;

    public Book(int publicationID, int isbn, int edition, String topic) {
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

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
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
