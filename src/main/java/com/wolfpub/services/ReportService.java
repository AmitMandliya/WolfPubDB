package com.wolfpub.services;

/**
 * @author devarshshah
 * @date 2020-04-02
 */
import com.wolfpub.connection.DBManager;

import java.sql.*;
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
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Month. This should be integer type:");
                String month = sc.nextLine();
                System.out.println("Please enter the Year. This should be integer type:");
                String year = sc.nextLine();
                ResultSet rs= getMonthlyPublicationReport(month,year);
                try
                {

                    System.out.println("DistributorID\tPublicationID\tSum(NumberCopies)\tSum(Price)");
                    while(rs.next()!= false)
                    {
                        String x=String.format("%13d %15d %19d %12.2f ",rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getFloat(4));
                        System.out.println(x);

                    }

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Month. This should be integer type:");
                month = sc.nextLine();
                System.out.println("Please enter the Year. This should be integer type:");
                year = sc.nextLine();
                rs= getMonthlyRevenueReport(month,year);
                try
                {

                    System.out.println("MONTH(PaymentDate)\tYEAR(PaymentDate)\tSum(PaymentAmount)");
                    while(rs.next()!= false)
                    {
                        String x=String.format("%18s %18s %20.2f",rs.getString(1), rs.getString(2),
                                rs.getFloat(3));
                        System.out.println(x);

                    }

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Starting Date in (\"yyyy-mm-dd\") format:");
                String startDate = sc.nextLine();
                System.out.println("Please enter the Ending Date in (\"yyyy-mm-dd\") format:");
                String endDate = sc.nextLine();
                Date value1= java.sql.Date.valueOf(startDate);
                Date value2= java.sql.Date.valueOf(endDate);
                rs= getMonthlyExpenseReport(value1,value2);
                try
                {
                    System.out.println("TotalCost");
                    while(rs.next()!= false)
                    {
                        String x=String.format("%9.2f",rs.getFloat(1));
                        System.out.println(x);
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
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
    private ResultSet getMonthlyPublicationReport(String month, String year)
    {
        String query = "SELECT DistributorID, PublicationID, SUM(NumberCopies), SUM(Price) " +
                "from ORDERS where MONTH(OrderDate) = ? AND YEAR(OrderDate) = ?  GROUP BY DistributorID,PublicationID;";

        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,month);
            ps.setString(2,year);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    private ResultSet getMonthlyRevenueReport(String month, String year)
    {
        String query = "SELECT MONTH(PaymentDate),YEAR(PaymentDate),SUM(PaymentAmount)"+
        "from CLEARDUES where MONTH(PaymentDate) = ? AND YEAR(PaymentDate) = ?;";
        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,month);
            ps.setString(2,year);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    private ResultSet getMonthlyExpenseReport(Date value1, Date value2)
    {
        String query = "SELECT SUM(TotalCost) TotalCost FROM ( SELECT SUM(ShippingCost) TotalCost"+
        " FROM ORDERS WHERE OrderDate BETWEEN ? AND ? UNION ALL  SELECT SUM(Amount)"+
            " FROM GENERATEPAYMENT WHERE Date BETWEEN ? AND ?) DERIVEDRELATION;";
        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1,value1);
            ps.setDate(2,value2);
            ps.setDate(3,value1);
            ps.setDate(4,value2);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }
}


