package com.wolfpub.models;

import java.sql.Date;

public class GeneratePayment {

    private int staffID;
    private int paycheckID;
    private int accountantID;
    private Date date;
    private float amount;
    private boolean isClaimed;

    public GeneratePayment(int staffID, int paycheckID, int accountantID, Date date, float amount, boolean isClaimed) {
        this.setStaffID(staffID);
        this.setPaycheckID(paycheckID);
        this.setAccountantID(accountantID);
        this.setDate(date);
        this.setAmount(amount);
        this.setClaimed(isClaimed);
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
