package com.wolfpub.models;

public class Distributor {
    int distributorID;
    String address;
    String name;
    String contact;
    String city;
    String contactPerson;
    String distributorType;
    float balance;

    public Distributor(int distributorID, String address, String name, String contact, String city, String contactPerson, String distributorType, float balance) {
        this.distributorID = distributorID;
        this.address = address;
        this.name = name;
        this.contact = contact;
        this.city = city;
        this.contactPerson = contactPerson;
        this.distributorType = distributorType;
        this.balance = balance;
    }

    public int getDistributorID() {
        return distributorID;
    }

    public void setDistributorID(int distributorID) {
        this.distributorID = distributorID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDistributorType() {
        return distributorType;
    }

    public void setDistributorType(String distributorType) {
        this.distributorType = distributorType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
