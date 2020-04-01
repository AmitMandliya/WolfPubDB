package com.wolfpub.services;

import com.wolfpub.connection.DBManager;
import com.wolfpub.models.*;

import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class DistributionService {
    int option;
    Scanner sc;
    Connection connection;
    public DistributionService(int option) {
        this.option = option;
        this.sc = new Scanner(System.in);
    }
    public void performOperation(){
        int distributorID;
        String address;
        String name;
        String contact;
        String city;
        int location;
        float balance;
        Distributor distributor;
        OrdersAndBill ordersAndBill;
        ClearDues clearDues;
        City cityObj;
        Order order;
        String balanceString;
        String locationString;
        String orderIDString;
        String distributorIDString;
        String publicationIDString;
        int publicationID;
        int orderID;
        String orderDateString;
        Date orderDate;
        String expectedDateString;
        Date expectedDate;
        String shippingCostString;
        float shippingCost;
        int numberCopies;
        String priceString;
        float price;
        boolean isPaid;
        String billDateString;
        String billAmountString;
        Date billDate;
        Float billAmount;
        int accountantID;
        int paymentID;
        Date paymentDate;
        String paymentAmountString;
        float paymentAmount;
        String paymentDateString;
        connection = (new DBManager()).getConnection();
        switch(option){
            case 1:
                //Enter new distributor
                System.out.println("Please enter following information ");
                System.out.println("Please enter the Distributor ID in integer format:");
                distributorID = sc.nextInt();
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                System.out.println("Please enter the address:");
                address = sc.nextLine();
                System.out.println("Please enter the name:");
                name = sc.nextLine();
                System.out.println("Please enter the contact:");
                contact = sc.nextLine();
                System.out.println("Please enter the city:");
                city = sc.nextLine();
                System.out.println("Please enter the ZIP Code/location in Integer format:");
                location = sc.nextInt();
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                System.out.println("Please enter the contact person name:");
                String contactPerson = sc.nextLine();
                System.out.println("Please enter the distributor type:");
                String distributorType = sc.nextLine();
                System.out.println("Please enter the balance, keep it null if not available at the moment:");
                //balance = sc.nextFloat();
                balanceString = sc.nextLine();
                while(true) {
                    try {
                        if (balanceString.length() == 0)
                            balance = Float.MIN_VALUE;
                        else
                            balance = Float.parseFloat(balanceString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("Balance should be a float value, please provide a valid value.");
                        balanceString = sc.nextLine();
                    }
                }
                distributor = new Distributor(distributorID, address, name, contact, city, contactPerson, distributorType, balance);
                cityObj = new City(city,location);
                enterDistributorInfo(distributor,cityObj);
                break;
            case 2:
                // Update distributor information
                System.out.println("Please enter following information (Only the Distributor is mandatory, rest all can be null if you do not want to update them, simply press enter in such cases:");
                System.out.println("Please enter the Distributor ID. This is a mandatory field. This should be unique and Integer type:");
                distributorID = sc.nextInt();
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                System.out.println("Please enter the address:");
                address = sc.nextLine();
                System.out.println("Please enter the name:");
                name = sc.nextLine();
                System.out.println("Please enter the contact:");
                contact = sc.nextLine();
                System.out.println("Please enter the city:");
                city = sc.nextLine();
                System.out.println("Please enter the ZIP Code/location in Integer format:");
                //location = sc.nextInt();
                locationString = sc.nextLine();
                while(true) {
                    try {
                        if (locationString.length() == 0)
                            location = Integer.MIN_VALUE;
                        else
                            location = Integer.parseInt(locationString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("Balance should be a integer value, please provide a valid value.");
                        locationString = sc.nextLine();
                    }
                }
                //sc.nextLine(); //throw away the \n not consumed by nextInt()
                System.out.println("Please enter the contact person name:");
                contactPerson = sc.nextLine();
                System.out.println("Please enter the distributor type:");
                distributorType = sc.nextLine();
                System.out.println("Please enter the balance, keep it null if not available at the moment:");
                //balance = sc.nextFloat();
                balanceString = sc.nextLine();
                while(true) {
                    try {
                        if (balanceString.length() == 0)
                            balance = Float.MIN_VALUE;
                        else
                            balance = Float.parseFloat(balanceString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("Balance should be a float value, please provide a valid value.");
                        balanceString = sc.nextLine();
                    }
                }
                distributor = new Distributor(distributorID, address, name, contact, city, contactPerson, distributorType, balance);
                cityObj = new City(city,location);
                updateDistributorInfo(distributor,cityObj);
                break;
            case 3:
                // Delete a distributor
                System.out.println("Please enter following information ");
                System.out.println("Please enter the Distributor ID in integer format:");
                distributorID = sc.nextInt();
                deleteDistributorInfo(distributorID);
                sc.nextLine(); // throw away the new line character
            case 4:
                // Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date
                System.out.println("Please enter following information ");
                System.out.println("Please enter the Distributor ID in integer format:");
                distributorIDString = sc.nextLine();
                while(true) {
                    try {
                        distributorID = Integer.parseInt(distributorIDString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("distributorID should be a integer value, please provide a valid value.");
                        distributorIDString = sc.nextLine();
                    }
                }
                System.out.println("Please enter the Order ID in integer format:");
                orderIDString = sc.nextLine();
                while(true) {
                    try {
                        orderID = Integer.parseInt(orderIDString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("orderID should be a integer value, please provide a valid value.");
                        orderIDString = sc.nextLine();
                    }
                }
                System.out.println("Please enter the Publication ID in integer format:");
                publicationIDString = sc.nextLine();
                while(true) {
                    try {
                        publicationID = Integer.parseInt(publicationIDString);
                        break;
                    } catch (NumberFormatException exp) {
                        System.out.println("orderID should be a integer value, please provide a valid value.");
                        publicationIDString = sc.nextLine();
                    }
                }
                System.out.println("Please enter Order Date in (\"yyyy-mm-dd\") format, let it be null if unknown:");
                orderDateString = sc.nextLine();
                orderDate = java.sql.Date.valueOf(orderDateString);
                System.out.println("Please enter expected Date in (\"yyyy-mm-dd\") format, let it be null if unknown:");
                expectedDateString = sc.nextLine();
                expectedDate = java.sql.Date.valueOf(expectedDateString);
                System.out.println("Please enter the shipping cost (float value), let it be null if unknown:");
                shippingCostString = sc.nextLine();
                shippingCost = Float.parseFloat(shippingCostString);
                System.out.println("Please enter the number of copies:");
                numberCopies = sc.nextInt();
                sc.nextLine(); //throw away the newline character
                priceString = sc.nextLine();
                price = Float.parseFloat(priceString);
                sc.nextLine(); // throw away the newline character
                isPaid = false; // new order
                order = new Order(orderID, orderDate, expectedDate, shippingCost, isPaid, numberCopies, price, distributorID, publicationID);
                placeOrder(order);
                break;
            case 5:
                //Bill distributor for an order
                System.out.println("Please enter the Order ID");
                orderID = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter Bill Date in (\"yyyy-mm-dd\") format:");
                billDateString = sc.nextLine();
                billDate = java.sql.Date.valueOf(billDateString);
                System.out.println("Please enter the accountant ID:");
                accountantID = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter the Bill Amount (float):");
                billAmountString = sc.nextLine();
                billAmount = Float.parseFloat(billAmountString);
                ordersAndBill = new OrdersAndBill(orderID,billDate,accountantID,billAmount);
                enterBillingInfo(ordersAndBill);
                break;
            case 6:
                // Change outstanding balance of a distributor on receipt of a payment.
                System.out.println("Please enter the Distributor ID:");
                distributorID = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter the Payment ID:");
                paymentID = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter the Accountant ID:");
                accountantID = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter Payment Date in (\"yyyy-mm-dd\") format:");
                paymentDateString = sc.nextLine();
                paymentDate = java.sql.Date.valueOf(paymentDateString);
                System.out.println("Please enter Payment amount:");
                paymentAmountString = sc.nextLine();
                paymentAmount = Float.parseFloat(paymentAmountString);
                clearDues = new ClearDues(paymentID,distributorID,accountantID,paymentDate,paymentAmount);
                updateOutstandingBalance(clearDues);
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

    private void updateOutstandingBalance(ClearDues clearDues) {
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        String insertQuery = "INSERT INTO CLEARDUES(PaymentID,DistributorID,AccountantID,PaymentDate,PaymentAmount) VALUES (?,?,?,?,?)";
        String updateQuery = "UPDATE DISTRIBUTORS SET BALANCE = BALANCE - ? WHERE  DistributorID = ?";

        try {
            connection.setAutoCommit(false);

            psInsert = connection.prepareStatement(insertQuery);
            psUpdate = connection.prepareStatement(updateQuery);

            psInsert.setInt(1, clearDues.getPaymentID());
            psInsert.setInt(2, clearDues.getDistributorID());
            psInsert.setInt(3, clearDues.getAccountantID());
            psInsert.setDate(4, clearDues.getPaymentDate());
            psInsert.setFloat(5, clearDues.getPaymentAmount());

            psUpdate.setFloat(1, clearDues.getPaymentAmount());
            psUpdate.setInt(2, clearDues.getDistributorID());

            psInsert.executeUpdate();
            psUpdate.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Rolled back the transaction.");
            } catch (SQLException ex) {
                System.out.println("Rollback unsuccessful.");
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            // since this object is shared, set the autocommit to true again
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enterBillingInfo(OrdersAndBill ordersAndBill) {
        PreparedStatement ps;
        String insertQuery = "INSERT INTO  ORDERSANDBILL(OrderID,BillDate,AccountantID,BillAmount) VALUES (?,?,?,?)";
        try{
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1,ordersAndBill.getOrderID());
            ps.setDate(2,ordersAndBill.getBillDate());
            ps.setInt(3,ordersAndBill.getAccountantID());
            ps.setFloat(4,ordersAndBill.getBillAmount());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Bill Details.");
            else
                System.out.println("Unsuccessful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void placeOrder(Order order) {
        PreparedStatement ps;
        String insertQuery = "INSERT INTO ORDERS VALUES OrderID = ?, OrderDate = ?, ExpectedDate = ?, ShippingCost = ?, IsPaid = ?, NumberCopies= ?, Price = ?, DistributorID = ?, PublicationID = ?";
        try{
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, order.getOrderID());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getExpectedDate());
            ps.setFloat(4, order.getShippingCost());
            ps.setBoolean(5, order.isPaid());
            ps.setInt(6, order.getNumberCopies());
            ps.setFloat(7, order.getPrice());
            ps.setInt(8, order.getDistributorID());
            ps.setInt(9, order.getPublicationID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Order Details.");
            else
                System.out.println("Unsuccessful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDistributorInfo(int distributorID) {
        PreparedStatement ps;
        String deleteQuery = "DELETE FROM DISTRIBUTORS WHERE DistributorID = ?";
        try{
            ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, distributorID);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully deleted distributor Details.");
            else
                System.out.println("Unsuccessful. distributorID is invalid");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDistributorInfo(Distributor distributor, City cityObj) {
        PreparedStatement ps;
        int distributorID = distributor.getDistributorID();
        String address = distributor.getAddress(), addressBefore;
        String name = distributor.getName(), nameBefore;
        String contact = distributor.getContact(), contactBefore;
        String city = distributor.getCity(), cityBefore;
        String contactPerson = distributor.getContactPerson(), contactPersonBefore;
        String distributorType = distributor.getDistributorType(), distributorTypeBefore;
        float balance = distributor.getBalance(), balanceBefore;
        int location = cityObj.getLocation(), locationBefore;
        // Fetch the old data, in case the user does not want to update a particular attribute
        String selectQuery = "SELECT Address, Name, Contact, City, ContactPerson, DistributorType, Balance FROM DISTRIBUTORS WHERE DistributorID = ?";
        try{
            ps = connection.prepareStatement(selectQuery);
            ps.setInt(1,distributorID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                addressBefore = rs.getString(1);
                nameBefore = rs.getString(2);
                contactBefore = rs.getString(3);
                cityBefore = rs.getString(4);
                contactPersonBefore = rs.getString(5);
                distributorTypeBefore = rs.getString(6);
                balanceBefore = rs.getFloat(7);
            }else {
                System.out.println("distributorID not found.");
                return;
            }
            if(address == "" || address == null || address.length() == 0){
                address = addressBefore;
            }
            if(name == "" || name == null || name.length() == 0){
                name = nameBefore;
            }
            if(contact == "" || contact == null || contact.length() == 0){
                contact = contactBefore;
            }
            if(city == "" || city == null || city.length() == 0){
                city = cityBefore;
            }
            if(distributorType == "" || distributorType == null || distributorType.length() == 0){
                distributorType = distributorTypeBefore;
            }
            if(balance == Float.MIN_VALUE){
                balance = balanceBefore;
            }
            if(contactPerson == "" || contactPerson == null || contactPerson.length() == 0){
                contactPerson = contactPersonBefore;
            }
            // Update the data as per user provided attribute values
            String updateQuery = "UPDATE DISTRIBUTORS SET Name = ?, Address = ?,  Contact = ?, City = ?, ContactPerson = ?, DistributorType = ? WHERE DistributorID = ?";
            ps = connection.prepareStatement(updateQuery);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contact);
            ps.setString(4, city);
            ps.setString(5, contactPerson);
            ps.setString(6,distributorType);
            ps.setInt(7, distributorID);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully updated distributor Details.");
            else
                System.out.println("Unsuccessful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enterDistributorInfo(Distributor distributor, City cityObj) {
        PreparedStatement ps;
        int distributorID = distributor.getDistributorID();
        String address = distributor.getAddress();
        String name = distributor.getName();
        String contact = distributor.getContact();
        String city = distributor.getCity();
        String contactPerson = distributor.getContactPerson();
        String distributorType = distributor.getDistributorType();
        float balance = distributor.getBalance();
        int location = cityObj.getLocation();
        String insertQuery = "INSERT DISTRIBUTORS SET  distributorID = ?, Name = ?, Address = ?,  Contact = ?, City = ?, ContactPerson = ?, DistributorType = ?";
        try{
            // Update the data as per user provided attribute values
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, distributorID);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, contact);
            ps.setString(5, city);
            ps.setString(6, contactPerson);
            ps.setString(7,distributorType);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted distributor Details.");
            else
                System.out.println("Unsuccessful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
