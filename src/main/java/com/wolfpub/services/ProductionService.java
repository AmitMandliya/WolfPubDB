package com.wolfpub.services;

import com.wolfpub.connection.DBManager;
import com.wolfpub.models.Article;
import com.wolfpub.models.Book;
import com.wolfpub.models.Chapter;
import com.wolfpub.models.GeneratePayment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * @author devarshshah
 * @date 2020-03-28
 */

public class ProductionService {
    private int option;
    Scanner sc;
    Connection connection;
    public ProductionService(int option)
    {
        this.option = option;
        this.sc = new Scanner(System.in);
    }

    public void performOperation(){
        connection = (new DBManager()).getConnection();
        switch(option)
        {
            case 1:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID. This should be unique and Integer type:");
                int publicationID = sc.nextInt();
                System.out.println("Please enter the ISBN. This should be unique and Integer type:");
                long isbn = sc.nextLong();
                System.out.println("Please enter the Edition of String type:");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                int edition = sc.nextInt();
                System.out.println("Please enter Topic of String Type");
                sc.nextLine();
                String topic = sc.nextLine();
                Book book = new Book(publicationID,isbn,edition,topic);
                enterBookInfo(book);
                break;
            case 2:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID. This should be unique and Integer type:");
                int articleID = sc.nextInt();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();//throw away the \n not consumed by nextInt()
                String creationDate = sc.nextLine();
                System.out.println("Please enter the Text of String type:");
                String text = sc.nextLine();
                System.out.println("Please enter the Title of String type:");
                String title = sc.nextLine();
                System.out.println("Please enter the Publication ID. This should be  Integer type:");
                publicationID = sc.nextInt();
                Article article = new Article(articleID,java.sql.Date.valueOf(creationDate),text,title,publicationID);
                enterArticleInfo(article);
                break;
            case 3:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter ISBN (Enter -1 if do not want to change)");
                isbn = sc.nextLong();
                System.out.println("Please enter edition (Enter -1 if do not want to change)");
                edition = sc.nextInt();
                System.out.println("Please enter Topic of book (press enter if do not want to change)");
                sc.nextLine();
                topic = sc.nextLine();
                // ====== handle unchanged data =======
                String query1 = "SELECT * FROM BOOKS WHERE PublicationID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, publicationID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        if (isbn == -1)
                        {
                            isbn = rs.getLong(2);
                        }
                        if (edition == -1)
                        {
                            edition = rs.getInt(3);
                        }
                        if (topic.length() == 0)
                        {
                            topic = rs.getString(4);
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                book = new Book(publicationID,isbn,edition,topic);
                updateBookEdition(book);
                break;
            case 4:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter ISBN ");
                isbn = sc.nextLong();
                deleteBookEdition(publicationID, isbn);
                break;
            case 5:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID.");
                articleID = sc.nextInt();
                System.out.println("Please enter Title (press enter if do not want to change)");
                sc.nextLine();
                title = sc.nextLine();
                System.out.println("Please enter Text (press enter if do not want to change)");
                text = sc.nextLine();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format: (press enter if do not want to change)");
                creationDate = sc.nextLine();
                // ====== handle unchanged data =======
                query1 = "SELECT * FROM ARTICLES WHERE ArticleID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, articleID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next())
                    {
                        if (title.length() == 0)
                        {
                            title = rs.getString(4);
                        }
                        if (text.length() == 0)
                        {
                            text = rs.getString(3);
                        }
                        if (creationDate.length() == 0)
                        {
                            creationDate = rs.getString(2);
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                Date value= java.sql.Date.valueOf(creationDate);
                updateArticleInfo(articleID,title,text,value);
                break;
            case 6:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID.");
                articleID = sc.nextInt();
                deleteArticleInfo(articleID);
                break;
            case 7:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Chapter ID. This should be unique and Integer type:");
                int chapterID = sc.nextInt();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();//throw away the \n not consumed by nextInt()
                creationDate = sc.nextLine();
                System.out.println("Please enter the Text of String type:");
                text = sc.nextLine();
                System.out.println("Please enter the Title of String type:");
                title = sc.nextLine();
                System.out.println("Please enter the Category of String type:");
                String category = sc.nextLine();
                System.out.println("Please enter the Publication ID. This should be  Integer type:");
                publicationID = sc.nextInt();
                Chapter chapter = new Chapter(chapterID,java.sql.Date.valueOf(creationDate),text,title,category,publicationID);
                enterChapterInfo(chapter);
                break;
            case 8:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Chapter ID.");
                chapterID = sc.nextInt();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format: (press enter if do not want to change)");
                sc.nextLine();
                creationDate = sc.nextLine();
                System.out.println("Please enter Text (press enter if do not want to change)");
                text = sc.nextLine();
                System.out.println("Please enter Title (press enter if do not want to change)");
                title = sc.nextLine();
                System.out.println("Please enter Category (press enter if do not want to change)");
                category = sc.nextLine();

                // ====== handle unchanged data =======
                query1 = "SELECT * FROM CHAPTERS WHERE ChapterID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, chapterID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next())
                    {
                        if (creationDate.length() == 0)
                        {
                            creationDate = rs.getString(2);
                        }
                        if (text.length() == 0)
                        {
                            text = rs.getString(3);
                        }
                        if (title.length() == 0)
                        {
                            title = rs.getString(4);
                        }
                        if (category.length() == 0)
                        {
                            category = rs.getString(5);
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                value= java.sql.Date.valueOf(creationDate);
                updateChapterInfo(chapterID,value,text,title,category);
                break;
            case 9:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID. This should be unique and Integer type:");
                articleID = sc.nextInt();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();//throw away the \n not consumed by nextInt()
                creationDate = sc.nextLine();
                System.out.println("Please enter the Text of String type:");
                text = sc.nextLine();
                System.out.println("Please enter the Title of String type:");
                title = sc.nextLine();
                System.out.println("Please enter the Publication ID. This should be  Integer type:");
                publicationID = sc.nextInt();
                article = new Article(articleID,java.sql.Date.valueOf(creationDate),text,title,publicationID);
                enterArticleInfo(article);
                break;
            case 10:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID.");
                articleID = sc.nextInt();
                System.out.println("Please enter Title (press enter if do not want to change)");
                sc.nextLine();
                title = sc.nextLine();
                System.out.println("Please enter Text (press enter if do not want to change)");
                text = sc.nextLine();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format: (press enter if do not want to change)");
                creationDate = sc.nextLine();
                // ====== handle unchanged data =======
                query1 = "SELECT * FROM ARTICLES WHERE ArticleID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, articleID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next())
                    {
                        if (title.length() == 0)
                        {
                            title = rs.getString(4);
                        }
                        if (text.length() == 0)
                        {
                            text = rs.getString(3);
                        }
                        if (creationDate.length() == 0)
                        {
                            creationDate = rs.getString(2);
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                value= java.sql.Date.valueOf(creationDate);
                updateArticleInfo(articleID,title,text,value);
                break;
            case 11:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID. This should be unique and Integer type:");
                articleID = sc.nextInt();
                System.out.println("Please enter the Text of String type:");
                sc.nextLine();
                text = sc.nextLine();
                enterArticleText(articleID,text);
                break;
            case 12:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID.");
                articleID = sc.nextInt();
                System.out.println("Please enter Text.");
                sc.nextLine();
                text = sc.nextLine();
                updateArticleText(articleID,text);
                break;
            case 13:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the topic.");
                topic = sc.nextLine();
                System.out.println("Please enter Creation Date in (\"yyyy-mm-dd\") format:");
                String publicationDate = sc.nextLine();
                System.out.println("Please enter AuthorName");
                String staffName = sc.nextLine();
                value=java.sql.Date.valueOf(publicationDate);
                ResultSet rs= getBooks(topic,value,staffName);
                try
                {
                    System.out.println("PublicationID\tTitle\tPublicationDate\tISBN\tEdition\tTopic");
                    while(rs.next()!= false)
                    {
                        System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDate(3).toString()+"\t"
                                +rs.getLong(4)+"\t"
                                +rs.getInt(5)+"\t"
                                +rs.getString(6));
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case 14:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the title.");
                title = sc.nextLine();
                System.out.println("Please enter Date in (\"yyyy-mm-dd\") format:");
                creationDate = sc.nextLine();
                System.out.println("Please enter AuthorName");
                staffName = sc.nextLine();
                value=java.sql.Date.valueOf(creationDate);
                rs= getArticles(title,value,staffName);
                try
                {
                    System.out.println("ArticleID\tCreationDate\tText\tTitle");
                    while(rs.next()!= false)
                    {
                        System.out.println(rs.getInt(1)+"\t"+rs.getDate(2).toString()+"\t"+rs.getString(3)+"\t"
                                +rs.getString(4));
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;

            case 15:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Staff ID.");
                int staffID = sc.nextInt();
                System.out.println("Please enter the Paycheck ID. It should be unique.");
                int paycheckID = sc.nextInt();
                System.out.println("Please enter the Accountant ID.");
                int accountantID = sc.nextInt();
                System.out.println("Please enter Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();//throw away the \n not consumed by nextInt()
                String date = sc.nextLine();
                System.out.println("Please enter the Amount:");
                float amount = sc.nextFloat();
                value= java.sql.Date.valueOf(date);
                System.out.println("Please enter the value of IsClaimed. Input should be true or false.");
                sc.nextLine();
                boolean isClaimed=sc.nextBoolean();
                enterStaffPayment(staffID,paycheckID,accountantID,value,amount,isClaimed);
                break;
            case 16:
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Paycheck ID.");
                paycheckID = sc.nextInt();
                rs= getPaymentClaimStatus(paycheckID);
                try
                {

                    System.out.println("StaffID\tPaycheckID\tAccountantID\tDate\tAmount\tIsClaimed");
                    while(rs.next()!= false)
                    {
                        System.out.println(rs.getInt(1)+"  \t"+"\t"+rs.getInt(2)+"  \t"+"\t"+"\t"+rs.getInt(3)+"  \t"
                              +"\t"+"\t"  +rs.getDate(4).toString()+"  \t"
                               +rs.getFloat(5)+"  \t"
                                +rs.getBoolean(6));
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
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void enterBookInfo(Book book)
    {
        String query = "INSERT INTO BOOKS(PublicationID,ISBN,Edition,Topic) VALUES(?,?,?,?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,book.getPublicationID());
            ps.setLong(2, book.getIsbn());
            ps.setInt(3, book.getEdition());
            ps.setString(4, book.getTopic());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Book Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void enterArticleInfo(Article article)
    {
        String query = "INSERT INTO ARTICLES(ArticleID,CreationDate,Text,Title,PublicationID) VALUES(?,?,?,?,?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,article.getArticleID());
            ps.setDate(2, article.getCreationDate());
            ps.setString(3, article.getText());
            ps.setString(4, article.getTitle());
            ps.setInt(5, article.getPublicationID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Article Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    private void updateBookEdition(Book book)
    {
        String query = "UPDATE BOOKS SET ISBN= ?, EDITION=?,Topic=? WHERE PublicationID=?;";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(4,book.getPublicationID());
            ps.setLong(1,book.getIsbn());
            ps.setInt(2, book.getEdition());
            ps.setString(3,book.getTopic());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Book edition Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void deleteBookEdition(int publicationID, long isbn)
    {
        String query = "DELETE FROM BOOKS where PublicationID=? and ISBN=?;";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,publicationID);
            ps.setLong(2,isbn);
            int result=ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Deleted Book edition Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void updateArticleInfo(int articleID, String title, String text, Date value)
    {
        String query = "UPDATE ARTICLES SET Title=?,Text=?,CreationDate=? where ArticleID=?;";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(4,articleID);
            ps.setString(1,title);
            ps.setString(2, text);
            ps.setDate(3,value);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Article Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void deleteArticleInfo(int articleID)
    {
        String query = "DELETE FROM ARTICLES where ArticleID=?;";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,articleID);
            int result=ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Deleted Article Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void enterChapterInfo(Chapter chapter)
    {
        String query = "INSERT INTO CHAPTERS(ChapterID,CreationDate,Text,Title,Category,PublicationID) VALUES(?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,chapter.getChapterID());
            ps.setDate(2, chapter.getCreationDate());
            ps.setString(3, chapter.getText());
            ps.setString(4, chapter.getTitle());
            ps.setString(5, chapter.getCategory());
            ps.setInt(6, chapter.getPublicationID());

            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Chapter Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    private void updateChapterInfo(int chapterID, Date value, String text, String title, String category)
    {
        String query = "UPDATE CHAPTERS SET CreationDate=?,Text=?,Title=?,Category=? where ChapterID=?;";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(5,chapterID);
            ps.setDate(1,value);
            ps.setString(2, text);
            ps.setString(3, title);
            ps.setString(4, category);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Chapter Details.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void enterArticleText(int articleID, String text)
    {
        String query = "UPDATE ARTICLES SET Text=? where ArticleID=?;";
        String query1 = "SELECT * FROM ARTICLES WHERE ArticleID= ?;";
        String text1=null;
        int result=0;
        ResultSet rs=null;
        try
        {
            PreparedStatement ps1 = connection.prepareStatement(query1);
            ps1.setInt(1,articleID);
            rs = ps1.executeQuery();

            while(rs.next()) {
                text1 = rs.getString(3);

            }

            PreparedStatement ps = connection.prepareStatement(query);
            if(text1==null || text1.length()==0)
            {
                ps.setInt(2, articleID);
                ps.setString(1, text);
                result=ps.executeUpdate();

            }
            else
            {
                ps.setInt(2,articleID);
                ps.setString(1,text1);

                result = ps.executeUpdate();
                result=0;

            }

            if(result == 1)
                System.out.println("Successfully inserted Article Text.");
            else
                System.out.println("ArticleText already exists. Please use updateArticleTextAPI");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void updateArticleText(int articleID, String text)
    {
        String query = "UPDATE ARTICLES SET Text=? where ArticleID=?;";
        String query1 = "SELECT * FROM ARTICLES WHERE ArticleID= ?;";
        String text1=null;
        int result=0;
        ResultSet rs=null;
        try
        {
            PreparedStatement ps1 = connection.prepareStatement(query1);
            ps1.setInt(1,articleID);
            rs = ps1.executeQuery();

            while(rs.next()) {
                text1 = rs.getString(3);

            }
            PreparedStatement ps = connection.prepareStatement(query);
            if(text1==null || text1.length()==0)
            {
                ps.setInt(2,articleID);
                ps.setString(1,text1);

                result = ps.executeUpdate();
                result=0;
            }
            else
            {

                ps.setInt(2, articleID);
                ps.setString(1, text);
                result=ps.executeUpdate();


            }


            if(result == 1)
                System.out.println("Successfully inserted Article Text.");
            else
                System.out.println("ArticleText doesn't exist. Please use insertArticleTextAPI");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void enterStaffPayment(int staffID, int paycheckID,int accountantID, Date value, float amount, boolean isClaimed)
    {
        String query = "INSERT INTO GENERATEPAYMENT(StaffID,PaycheckID,AccountantID,Date,Amount,IsClaimed) VALUES(?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,staffID);
            ps.setInt(2,paycheckID);
            ps.setInt(3,accountantID);
            ps.setDate(4,value);
            ps.setFloat(5, amount);
            ps.setBoolean(6,isClaimed);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted staff payment.");
            else
                System.out.println("Unsuccessful.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private ResultSet getPaymentClaimStatus(int paycheckID)
    {
        String query = "SELECT * FROM GENERATEPAYMENT WHERE PaycheckID = ?;";
        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,paycheckID);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    private ResultSet getBooks(String topic, Date value, String staffName)
    {
        String query = "SELECT BOOKS.PublicationID, PUBLICATIONS.Title,PUBLICATIONS.PublicationDate, BOOKS.ISBN,BOOKS.Edition,BOOKS.Topic  " +
                "FROM BOOKS,STAFF,PUBLICATIONS, AUTHOREDBY \tWHERE BOOKS.PublicationID=AUTHOREDBY.PublicationID AND PUBLICATIONS.PublicationID=BOOKS.PublicationID " +
                "AND AUTHOREDBY.StaffID=STAFF.StaffID AND BOOKS.Topic=? AND PUBLICATIONS.PublicationDate=? " +
                "AND STAFF.StaffName=?;";
        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,topic);
            ps.setDate(2,value);
            ps.setString(3, staffName);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    private ResultSet getArticles(String title, Date value, String staffName)
    {
        String query = "SELECT ARTICLES.ArticleID,  ARTICLES.CreationDate, ARTICLES.Text,ARTICLES.Title  FROM ARTICLES,STAFF, WRITTENBY " +
                "WHERE ARTICLES.ArticleID = WRITTENBY.ArticleID AND WRITTENBY.StaffID=STAFF.StaffId AND ARTICLES.Title=? "+
                "AND ARTICLES.CreationDate=? AND STAFF.StaffName=?;";
        ResultSet rs=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,title);
            ps.setDate(2,value);
            ps.setString(3, staffName);
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }



}
