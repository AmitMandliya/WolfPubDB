package com.wolfpub.models;

import java.sql.Date;

public class OrdersAndBill {

    int orderID;
    Date billDate;
    int accountantID;
    float billAmount;

    public OrdersAndBill(int orderID, Date billDate, int accountantID, float billAmount) {
        this.orderID = orderID;
        this.billDate = billDate;
        this.accountantID = accountantID;
        this.billAmount = billAmount;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public int getAccountantID() {
        return accountantID;
    }

    public void setAccountantID(int accountantID) {
        this.accountantID = accountantID;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }
}
