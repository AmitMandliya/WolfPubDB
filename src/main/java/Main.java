import com.wolfpub.services.*;
import com.wolfpub.connection.DBManager;

import java.sql.*;
import java.util.*;
public class Main {
    public static Scanner sc = null;
    public static Connection connection;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        //connection = (new DBManager()).getConnection();
        displayWelcomeMessage();
        displayMainMenu();
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to WolfPubDB");
    }

    private static void displayMainMenu() {
        System.out.println("Please select a choice out of the following options");
        System.out.println("Press 1 for Getting the API Menu");
        System.out.println("Press 2 for Exit");
        int option = sc.nextInt();
        switch(option){
            case 1:
                displayAPIMenu();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid option selected");
                displayMainMenu();
                break;
        }

    }

    private static void displayAPIMenu()
    {
        System.out.println("Please select a which category of task/operation do you want to pursue");
        System.out.println("1: Editing and Publishing");
        System.out.println("2: Production of a book edition or of an issue of a publication");
        System.out.println("3: Distribution");
        System.out.println("4: Reports");
        System.out.println("5: Staff");
        System.out.println("6: Back to Main Menu");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                displayEditingMenu();
                break;
            case 2:
                displayProductionMenu();
                break;
            case 3:
                displayDistributionMenu();
                break;
            case 4:
                displayReportsMenu();
                break;
            case 5:
                displayStaffMenu();
                break;
            case 6:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid option selected");
                displayAPIMenu();
                break;
        }


    }

    private static void displayStaffMenu() {
        System.out.println("Please select one of the following choices:");
        System.out.println("1: Enter Staff Details");
        System.out.println("2: Go back to API Menu");
        int option = sc.nextInt();
        if(option == 2){
            displayAPIMenu();
        }else if(option < 1 || option > 2){
            System.out.println("Invalid option selected");
            displayStaffMenu();
        }
        else {
            StaffService staffService = new StaffService(option);
            staffService.performOperation();
            System.out.println("Current operation is finished");
            System.out.println("Please select a choice:");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            int nextOption = sc.nextInt();
            switch (nextOption) {
                case 1:
                    displayStaffMenu();
                    break;
                case 2:
                    displayAPIMenu();
                    break;
            }
        }
    }

    private static void displayReportsMenu() {
        System.out.println("Please select one of the following choices:");
        System.out.println("1: Create a report of number and total price of copies of each publication bought per distributor per month");
        System.out.println("2: Create a report of total amount collected from distributors per month");
        System.out.println("3: Create a report of sum of shipping cost of orders and salary given to staff per month");
        System.out.println("4: Create a report of count of current distributors");
        System.out.println("5: Create a report of total revenue grouped by distributor, city, location since Inception");
        System.out.println("6: Create a report of  total payment given to Staff between startDate and endDate per work type");
        System.out.println("7: Go back to API Menu");
        int option = sc.nextInt();
        if(option == 7){
            displayAPIMenu();
        }else if(option < 1 || option > 7){
            System.out.println("Invalid option selected");
            displayReportsMenu();
        }
        else {
            ReportService reportService = new ReportService(option);
            reportService.performOperation();
            System.out.println("Current operation is finished");
            System.out.println("Please select a choice:");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            int nextOption = sc.nextInt();
            switch (nextOption) {
                case 1:
                    displayReportsMenu();
                    break;
                case 2:
                    displayAPIMenu();
                    break;
            }
        }
    }

    private static void displayDistributionMenu() {
        System.out.println("Please select one of the following choices:");
        System.out.println("1: Enter new distributor");
        System.out.println("2: Update distributor information");
        System.out.println("3: Delete a distributor");
        System.out.println("4: Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date");
        System.out.println("5: Bill distributor for an order");
        System.out.println("6: Change outstanding balance of a distributor on receipt of a payment");
        System.out.println("7: Go back to API Menu");
        int option = sc.nextInt();
        if(option == 7){
            displayAPIMenu();
        }else if(option < 1 || option > 7){
            System.out.println("Invalid option selected");
            displayDistributionMenu();
        }
        else {
            DistributionService distributionService = new DistributionService(option);
            distributionService.performOperation();
            System.out.println("Current operation is finished");
            System.out.println("Please select a choice:");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            int nextOption = sc.nextInt();
            switch (nextOption) {
                case 1:
                    displayDistributionMenu();
                    break;
                case 2:
                    displayAPIMenu();
                    break;
            }
        }
    }

    private static void displayProductionMenu() {
        System.out.println("Please select one of the following choices:");
        System.out.println("1: Enter a new book edition");
        System.out.println("2: Enter new issue of a publication");
        System.out.println("3: Update a book edition");
        System.out.println("4: Delete a book edition");
        System.out.println("5: Update Publication Issue");
        System.out.println("6: Delete Publication Issue");
        System.out.println("7: Enter Chapter");
        System.out.println("8: Update Chapter");
        System.out.println("9: Enter Article");
        System.out.println("10: Update Article");
        System.out.println("11: Enter Article Text");
        System.out.println("12: Update Article Text");
        System.out.println("13: Find books by topic, date, authors name");
        System.out.println("14: Find articles by topic, date, authors name");
        System.out.println("15: Enter payment for author or editor");
        System.out.println("16: Keep track of when each payment was claimed by its addressee");
        System.out.println("17: Go back to API Menu");
        int option = sc.nextInt();
        if(option == 17){
            displayAPIMenu();
        }else if(option < 1 || option > 17){
            System.out.println("Invalid option selected");
            displayProductionMenu();
        }
        else {
            ProductionService productionService = new ProductionService(option);
            productionService.performOperation();
            System.out.println("Current operation is finished");
            System.out.println("Please select a choice:");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            int nextOption = sc.nextInt();
            switch (nextOption) {
                case 1:
                    displayProductionMenu();
                    break;
                case 2:
                    displayAPIMenu();
                    break;
            }
        }
    }

    private static void displayEditingMenu() {
        System.out.println("Please select one of the following choices:");
        System.out.println("1: Enter basic information for new publication");
        System.out.println("2: Add a new book");
        System.out.println("3: Add new periodic publication information");
        System.out.println("4: Update information of publication");
        System.out.println("5: Update book information");
        System.out.println("6: Update periodic publication information");
        System.out.println("7: Assign editors to publication");
        System.out.println("8: View the information for a Editor on the publications he/she is responsible for");
        System.out.println("9: View publication information ");
        System.out.println("10: Add new article");
        System.out.println("11: Delete an article  ");
        System.out.println("12: Add new chapter in Book");
        System.out.println("13: Delete Chapter From Book");
        System.out.println("14: Go back to API Menu");
        int option = sc.nextInt();
        if (option == 14) {
            displayAPIMenu();
        } else if (option < 1 || option > 14) {
            System.out.println("Invalid option selected");
            displayEditingMenu();
        }
        else
        {
            EditingService editingService = new EditingService(option);
            editingService.performOperation();
            System.out.println("Current operation is finished");
            System.out.println("Please select a choice:");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            int nextOption = sc.nextInt();
            switch (nextOption) {
                case 1:
                    displayEditingMenu();
                    break;
                case 2:
                    displayAPIMenu();
                    break;
        }
    }
    }
}
