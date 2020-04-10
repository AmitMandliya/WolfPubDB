package com.wolfpub.services;

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
            case 4:
                //Create a report of count of current distributors
                rs = getDistributors();
                try {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    for (int i = 1; i <= columnCount; i++ )
                    {
                        String name = rsmd.getColumnName(i);
                        System.out.print(name+"\t");
                    }
                    System.out.println("");
                    while(rs.next())
                    {
                        System.out.println(rs.getInt(1));
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case 5:
                //Create a report of total revenue grouped by distributor, city, location since Inception
                System.out.println("Enter option to view report \n 1- per distributor\t 2-per city\t3-per location");
                int op = sc.nextInt();
                rs = getTotalRevenue(op);
                try {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    for (int i = 1; i <= columnCount; i++ )
                    {
                        String name = rsmd.getColumnName(i);
                        System.out.print(name+"\t");
                    }
                    System.out.println("");
                    while (rs.next())
                    {
                        if(op==2)
                        {
                            System.out.print(rs.getString(1)+"\t \t");
                        }
                        else
                        {
                            System.out.print(rs.getInt(1)+"\t \t");
                        }
                        System.out.print(rs.getFloat(2)+"\n");
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

                break;
            case 6:
                //Create a report of  total payment given to Staff between startDate and endDate per work type
                System.out.println("Enter Start date :");
                String stDate=sc.nextLine();
                System.out.println("Enter end date :");
                endDate=sc.nextLine();
                rs = getStaffPaymentbetweenDates(java.sql.Date.valueOf(stDate),java.sql.Date.valueOf(endDate));
                try{
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getFloat(2));
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
    ResultSet getStaffPaymentbetweenDates(java.sql.Date st, java.sql.Date end)
        {
            ResultSet result=null;
            String query = "SELECT WORKTYPE, Payment from (SELECT SUM(Amount) AS Payment, \'AUTHORSHIP\' AS WORKTYPE FROM GENERATEPAYMENT, AUTHORS " +
                            "WHERE GENERATEPAYMENT.StaffID  = AUTHORS.StaffID " +
                            "AND Date BETWEEN ? AND ? UNION ALL " +
                            "SELECT SUM(Amount) AS Payment, \'EDITORSHIP\' AS WORKTYPE " +
                            "FROM GENERATEPAYMENT, EDITORS " +
                            "WHERE GENERATEPAYMENT.StaffID  = EDITORS.StaffID " +
                            "AND Date BETWEEN ? AND ? " +
                            "UNION ALL " +
                            "SELECT SUM(Amount) AS  Payment, \'JOURNALISM\' AS WORKTYPE " +
                            "FROM GENERATEPAYMENT, JOURNALISTS WHERE GENERATEPAYMENT.StaffID  = JOURNALISTS.StaffID " +
                            "AND Date BETWEEN ? AND ? ) DERIVEDRELATION;";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, st.toString());
                ps.setDate(2, end);
                ps.setString(3, st.toString());
                ps.setDate(4, end);
                ps.setString(5, st.toString());
                ps.setDate(6, end);
                result = ps.executeQuery();
                return result;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

    ResultSet getTotalRevenue(int op)
        {
            ResultSet result=null;
            if (op==1)
            {
                //per distributor
                String query = "SELECT DISTRIBUTORS.DistributorID, SUM(PaymentAmount) FROM DISTRIBUTORS, CLEARDUES WHERE DISTRIBUTORS.DistributorID = CLEARDUES.DistributorID GROUP BY DISTRIBUTORS.DistributorID;";
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    result = ps.executeQuery();
                    return result;
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(op==2)
            {
                //per city
                String query = "SELECT DISTRIBUTORS.City, SUM(PaymentAmount) FROM DISTRIBUTORS, CLEARDUES WHERE DISTRIBUTORS.DistributorID = CLEARDUES.DistributorID GROUP BY DISTRIBUTORS.City;";
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    result = ps.executeQuery();
                    return result;
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                // per location
                String query = "SELECT CITIES.Location, SUM(PaymentAmount) FROM DISTRIBUTORS, CITIES, CLEARDUES WHERE DISTRIBUTORS.DistributorID = CLEARDUES.DistributorID AND DISTRIBUTORS.City = CITIES.City GROUP BY CITIES.Location;";
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    result = ps.executeQuery();
                    return result;
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

	ResultSet getDistributors()
        {
            ResultSet result=null;
            String query = "SELECT COUNT(*) FROM DISTRIBUTORS;";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                result = ps.executeQuery();
                return result;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
}


