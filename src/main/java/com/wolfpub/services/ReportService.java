package com.wolfpub.services;

import com.wolfpub.connection.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ReportService {
    int option;
    Scanner sc;
    Connection connection;
    public ReportService(int option) {
        this.option = option;
        this.sc = new Scanner(System.in);
    }
    public void performOperation(){
        connection = (new DBManager()).getConnection();
        switch(option){
            case 1:
                break;
            case 2:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
