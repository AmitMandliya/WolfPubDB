package com.wolfpub.models;

public class Staff {

    private int staffID;
    private boolean isInvited;
    private String staffName;
    private String type;

    public Staff(int staffID, boolean isInvited, String staffName, String type) {
        this.setStaffID(staffID);
        this.setInvited(isInvited);
        this.setStaffName(staffName);
        this.setType(type);
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public boolean isInvited() {
        return isInvited;
    }

    public void setInvited(boolean invited) {
        isInvited = invited;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
