package com.wolfpub.models;

import java.sql.Date;

public class GeneratePayment {

    int staffID;
    int paycheckID;
    int accountantID;
    Date date;
    float amount;
    boolean isClaimed;

    public GeneratePayment(int staffID, int paycheckID, int accountantID, Date date, float amount, boolean isClaimed) {
        this.staffID = staffID;
        this.paycheckID = paycheckID;
        this.accountantID = accountantID;
        this.date = date;
        this.amount = amount;
        this.isClaimed = isClaimed;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getPaycheckID() {
        return paycheckID;
    }

    public void setPaycheckID(int paycheckID) {
        this.paycheckID = paycheckID;
    }

    public int getAccountantID() {
        return accountantID;
    }

    public void setAccountantID(int accountantID) {
        this.accountantID = accountantID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }
}
