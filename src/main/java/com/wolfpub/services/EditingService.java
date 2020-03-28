package com.wolfpub.services;
import com.wolfpub.connection.DBManager;

import com.wolfpub.models.Publication;
import java.util.*;
import java.sql.*;
import java.sql.Date;
public class EditingService {
    int option;
    Scanner sc;
    Connection connection;
    public EditingService(int option){
        this.option = option;
        this.sc = new Scanner(System.in);
    }
    public void performOperation(){
        connection = (new DBManager()).getConnection();
        switch(option){
            case 1:
                //Enter basic information for new publication
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID. This should be unique and Integer type:");
                int publicationID = sc.nextInt();
                System.out.println("Please enter the Title of String type:");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                String title = sc.nextLine();
                System.out.println("Please enter Publication Date in (\"yyyy-mm-dd\") format:");
                String publicationDate = sc.nextLine();
                Publication publication = new Publication(publicationID,title,java.sql.Date.valueOf(publicationDate));
                enterPublicationInfo(publication);
                break;
            case 2:
                //Add a new book
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
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

    private void enterPublicationInfo(Publication publication) {
        String query = "INSERT INTO PUBLICATIONS(PublicationID,Title,PublicationDate) VALUES(?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,publication.getPublicationID());
            ps.setString(2, publication.getTitle());
            ps.setDate(3, publication.getPublicationDate());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Publication Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
