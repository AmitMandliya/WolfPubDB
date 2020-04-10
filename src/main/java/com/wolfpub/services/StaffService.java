package com.wolfpub.services;

import com.wolfpub.connection.DBManager;
import com.wolfpub.models.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class StaffService {
    int option;
    Scanner sc;
    Connection connection;
    int staffID;
    String isInvitedString;
    boolean isInvited;
    String staffName;
    String staffType;
    public StaffService(int option) {
        this.option = option;
        this.sc = new Scanner(System.in);
    }
    public void performOperation() {
        connection = (new DBManager()).getConnection();
        switch(option) {
            case 1:
                System.out.println("Enter the StaffID");
                staffID = sc.nextInt();
                sc.nextLine();
                System.out.println("Is the Staff invited? (true, false)");
                isInvitedString = sc.nextLine();
                isInvited = Boolean.parseBoolean(isInvitedString);
                System.out.println("Enter the Staff Name");
                staffName = sc.nextLine();
                System.out.println("Enter the Staff Type (editor, author, journalist)");
                boolean flag = true;
                while(flag) {
                    staffType = sc.nextLine();
                    //System.out.println(staffType);
                    if (!(staffType.equals("editor") || staffType.equals("author") || staffType.equals("journalist"))){
                        System.out.println("Please enter the correct type string of staff (editor, author, journalist)");
                    }else{
                        flag = false;
                    }
                }
                Staff staff = new Staff(staffID, isInvited, staffName, staffType);
                enterStaffInfo(staff);
        }


    }

    private void enterStaffInfo(Staff staff) {
        PreparedStatement ps, psInsert2;
        String insertQuery = "INSERT INTO STAFF (StaffID , IsInvited , StaffName , Type) VALUES (?,?,?,?)";
        String insertQuery2 = "";
        String t = staff.getType();
        if(t.equals("editor"))
            insertQuery2 = "INSERT INTO EDITORS (StaffID) VALUES (?)";
        else if (t.equals("author"))
            insertQuery2 = "INSERT INTO AUTHORS (StaffID) VALUES (?)";
        else if (t.equals("journalist"))
            insertQuery2 = "INSERT INTO JOURNALISTS (StaffID) VALUES (?)";
        try{
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, staff.getStaffID());
            ps.setBoolean(2, staff.isInvited());
            ps.setString(3, staff.getStaffName());
            ps.setString(4, staff.getType());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Staff Details.");
            else
                System.out.println("Unsuccessful.");

            psInsert2 = connection.prepareStatement(insertQuery2);
            //System.out.println(psInsert2);
            psInsert2.setInt(1, staff.getStaffID());

            result = psInsert2.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted "+staff.getType().toUpperCase()+" Details.");
            else
                System.out.println("Unsuccessful in inserting "+staff.getType().toUpperCase()+" Details.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
