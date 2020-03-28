package com.wolfpub.models;
import java.sql.Date;
public class Order {

    int orderID;
    Date orderDate;
    Date expectedDate;
    float shippingCost;
    boolean isPaid;
    int numberCopies;
    float price;
    int distributorID;
    int publicationID;

    public Order(int orderID, Date orderDate, Date expectedDate, float shippingCost, boolean isPaid, int numberCopies, float price, int distributorID, int publicationID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.expectedDate = expectedDate;
        this.shippingCost = shippingCost;
        this.isPaid = isPaid;
        this.numberCopies = numberCopies;
        this.price = price;
        this.distributorID = distributorID;
        this.publicationID = publicationID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDistributorID() {
        return distributorID;
    }

    public void setDistributorID(int distributorID) {
        this.distributorID = distributorID;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }
}
