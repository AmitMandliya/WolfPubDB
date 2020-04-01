package com.wolfpub.models;

import java.sql.Date;

public class OrdersAndBill {

    private int orderID;
    private Date billDate;
    private int accountantID;
    private float billAmount;

    public OrdersAndBill(int orderID, Date billDate, int accountantID, float billAmount) {
        this.setOrderID(orderID);
        this.setBillDate(billDate);
        this.setAccountantID(accountantID);
        this.setBillAmount(billAmount);
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
