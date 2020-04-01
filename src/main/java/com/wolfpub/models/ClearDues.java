package com.wolfpub.models;

import java.sql.Date;

public class ClearDues {

    private int paymentID;
    private int distributorID;
    private int accountantID;
    private Date paymentDate;
    private float paymentAmount;

    public ClearDues(int paymentID, int distributorID, int accountantID, Date paymentDate, float paymentAmount) {
        this.setPaymentID(paymentID);
        this.setDistributorID(distributorID);
        this.setAccountantID(accountantID);
        this.setPaymentDate(paymentDate);
        this.setPaymentAmount(paymentAmount);
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getDistributorID() {
        return distributorID;
    }

    public void setDistributorID(int distributorID) {
        this.distributorID = distributorID;
    }

    public int getAccountantID() {
        return accountantID;
    }

    public void setAccountantID(int accountantID) {
        this.accountantID = accountantID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
