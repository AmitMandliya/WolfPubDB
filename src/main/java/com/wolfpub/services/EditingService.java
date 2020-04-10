package com.wolfpub.services;
import com.wolfpub.connection.DBManager;
import com.wolfpub.models.*;
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
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter ISBN. This should be unique and Integer Type:");
                //sc.nextLine(); //throw away the \n not consumed by nextInt()
                long ISBN = sc.nextLong();
                System.out.println("Please enter edition");
                int edition = sc.nextInt();
                System.out.println("Please enter Topic of book");
                sc.nextLine();
                String topic = sc.nextLine();
                Book book = new Book(publicationID,ISBN,edition,topic);
                enterBookInfo(book);
                break;
            case 3:
                //add new periodic publication info
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter Type (Magazine, Journals)");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                String type = sc.nextLine();
                System.out.println("Please enter Periodicity of publication e.g. weekly, monthly");
                //sc.nextLine(); //throw away the \n not consumed by nextInt()
                String periodicity = sc.nextLine();
                System.out.println("Please enter Issue Number");
                int issueNumber = sc.nextInt();
                System.out.println("Please enter Category of publication e.g. politics,...etc");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                String category = sc.nextLine();
                PeriodicPublication pp = new PeriodicPublication(publicationID,periodicity,issueNumber,type,category);
                enterPeriodicPublicationInfo(pp);
                break;
            case 4:
                //update publication info
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID");
                publicationID = sc.nextInt();
                System.out.println("Please enter the Title (press enter if don't want to change):");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                title = sc.nextLine();
                System.out.println("Please enter Publication Date in (\"yyyy-mm-dd\") format (press enter if don't want to change):");
                publicationDate = sc.nextLine();
                // ===== handle unchanged data =====
                String query1 = "SELECT * FROM PUBLICATIONS WHERE PublicationID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, publicationID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        if (title.length() == 0)
                            {
                                title = rs.getString(2);
                        }
                        if (publicationDate.length() == 0)
                        {
                            publicationDate = rs.getString(3).toString();
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                //===============================================
                publication = new Publication(publicationID,title,java.sql.Date.valueOf(publicationDate));
                updatePublicationInfo(publication);
                break;
            case 5:
                //update book info
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter ISBN (Enter -1 if do not want to change)");
                ISBN = sc.nextLong();
                System.out.println("Please enter edition (Enter -1 if do not want to change)");
                edition = sc.nextInt();
                System.out.println("Please enter Topic of book (press enter if do not want to change)");
                sc.nextLine();
                topic = sc.nextLine();
                // ====== handle unchanged data =======
                query1 = "SELECT * FROM BOOKS WHERE PublicationID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, publicationID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        if (ISBN == -1)
                        {
                            ISBN = rs.getLong(2);
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
                //========
                book = new Book(publicationID,ISBN,edition,topic);
                updateBookInfo(book);
                break;
            case 6:
                //update periodic publication info
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();

                System.out.println("Please enter Type (Magazine, Journals) (press enter if do not want to change)");
                sc.nextLine();
                type = sc.nextLine();

                System.out.println("Please enter Periodicity of publication e.g. weekly, monthly (press enter if do not want to change)");
                periodicity = sc.nextLine();

                System.out.println("Please enter Issue Number (press enter -1 if do not want to change)");
                issueNumber = sc.nextInt();

                System.out.println("Please enter Category of publication e.g. politics,...etc (press enter if do not want to change)");
                sc.nextLine(); //throw away the \n not consumed by nextInt()
                category = sc.nextLine();
                // ====== handle unchanged data =======
                query1 = "SELECT * FROM PERIODICPUBLICATIONS WHERE PublicationID= ?;";
                try {
                    PreparedStatement ps1 = connection.prepareStatement(query1);
                    ps1.setInt(1, publicationID);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        if (type.length() == 0)
                        {
                            type = rs.getString(4);
                        }
                        if (periodicity.length() == 0)
                        {
                            periodicity = rs.getString(2);
                        }
                        if (issueNumber == -1)
                        {
                            issueNumber = rs.getInt(3);
                        }
                        if (category.length()==0)
                        {
                            category = rs.getString(5);
                        }

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                //========
                pp = new PeriodicPublication(publicationID,periodicity,issueNumber,type,category);
                updatePeriodicPublicationInfo(pp);
                break;
            case 7:
                //assign editors - publication
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                System.out.println("Please enter the Staff ID.");
                int staffID = sc.nextInt();
                EditedBy editedby = new EditedBy(publicationID,staffID);
                assignEditor(editedby);
                break;
            case 8:
                //Editor can view the information on the publications he/she is responsible for
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Staff ID.");
                staffID = sc.nextInt();
                ResultSet rs = getMyPublicationsInfo(staffID);
                try
                {
                    System.out.println("PublicationID\t\t\t\t\t\tTitle\t\t\t\tPublication Date");
                    while(rs.next()!= false)
                    {
                        String x=String.format("%13d %30s %19s",rs.getInt("publicationID"), rs.getString("title"),rs.getDate("publicationDate").toString());
                        System.out.println(x);
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

                break;
            case 9:
                //view publication info
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID.");
                publicationID = sc.nextInt();
                rs = getPublicationInfo(publicationID);
                try
                {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    for (int i = 1; i <= columnCount; i++ )
                    {
                        String name = rsmd.getColumnName(i);
                        System.out.print(name+"\t");
                    }
                    System.out.println("");
                    if (columnCount==4) {
                        while (rs.next() != false) {
                            String x=String.format("%7d %10d %10d %14s",rs.getInt(1), rs.getLong(2),rs.getInt(3), rs.getString(4));
                            System.out.println(x);
                            //System.out.println( + "\t" +  + "\t"+  + "\t" + );
                        }
                    }
                    else
                    {
                        while (rs.next() != false) {
                            String x=String.format("%7d %12s %10d %14s %14s",rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4),rs.getString(5));
                            System.out.println(x);
                            //System.out.println( + "\t" +  + "\t" + rs.getInt(3) + "\t" +rs.getString(4)+"\t" + );
                        }
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case 10:
                //Add new article
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID ");
                publicationID = sc.nextInt();
                System.out.println("Please enter article ID. This should be unique and not null");
                //sc.nextLine(); //throw away the \n not consumed by nextInt()
                int articleID = sc.nextInt();
                System.out.println("Please enter Publication Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();
                String creationDate = sc.nextLine();
                System.out.println("Please enter text ");
                String text = sc.nextLine();
                System.out.println("Please enter Title");
                title = sc.nextLine();
                Article article = new Article(articleID,java.sql.Date.valueOf(creationDate), text,title,publicationID);

                addArticle(article);
                break;
            case 11:
                //delete article
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Article ID ");
                articleID = sc.nextInt();
                deleteArticle(articleID);
                break;
            case 12:
                //Add new chapter
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Publication ID ");
                publicationID = sc.nextInt();
                System.out.println("Please enter Chapter ID. This should be unique and not null");
                //sc.nextLine(); //throw away the \n not consumed by nextInt()
                int chapterID = sc.nextInt();
                System.out.println("Please enter creation Date in (\"yyyy-mm-dd\") format:");
                sc.nextLine();
                creationDate = sc.nextLine();
                System.out.println("Please enter text ");
                text = sc.nextLine();
                System.out.println("Please enter Title");
                title = sc.nextLine();
                System.out.println("Please enter Category");
                category = sc.nextLine();
                Chapter chapter = new Chapter(chapterID,java.sql.Date.valueOf(creationDate),text,title,category,publicationID);
                addChapters(chapter);
                break;
            case 13:
                //delete chapter
                System.out.println("Please enter following information:");
                System.out.println("Please enter the Chapter ID ");
                chapterID = sc.nextInt();
                deleteChapterInfo(chapterID);
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

    private void enterBookInfo(Book book)
    {
        String query = "INSERT INTO BOOKS(PublicationID,ISBN, edition ,topic) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,book.getPublicationID());
            ps.setLong(2,book.getIsbn());
            ps.setInt(3, book.getEdition());
            ps.setString(4,book.getTopic());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Book Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enterPeriodicPublicationInfo(PeriodicPublication pp)
    {
        String query = "INSERT INTO PERIODICPUBLICATIONS VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,pp.getPublicationID());
            ps.setString(2,pp.getPeriodicity());
            ps.setInt(3, pp.getIssueNumber());
            ps.setString(4, pp.getType());
            ps.setString(5, pp.getCategory());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Periodic Publication Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePublicationInfo(Publication publication) {
        String query = "UPDATE PUBLICATIONS SET Title = ?, PublicationDate= ? WHERE PublicationID= ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,publication.getTitle());
            ps.setDate(2, publication.getPublicationDate());
            ps.setInt(3, publication.getPublicationID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Publication Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBookInfo(Book book)
    {
        String query = "UPDATE BOOKS SET ISBN= ?, EDITION=?,Topic=? WHERE PublicationID=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(4,book.getPublicationID());
            ps.setLong(1,book.getIsbn());
            ps.setInt(2, book.getEdition());
            ps.setString(3,book.getTopic());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Book Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePeriodicPublicationInfo(PeriodicPublication pp)
    {
        String query = "UPDATE PERIODICPUBLICATIONS SET Periodicity= ?, IssueNumber=?, Type=?, Category=? WHERE PublicationID=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(5,pp.getPublicationID());
            ps.setString(1,pp.getPeriodicity());
            ps.setInt(2, pp.getIssueNumber());
            ps.setString(3, pp.getType());
            ps.setString(4, pp.getCategory());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Updated Periodic Publication Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void assignEditor(EditedBy editedBy) {
        String query = "INSERT INTO EDITEDBY VALUES(?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,editedBy.getPublicationID());
            ps.setInt(2,editedBy.getStaffID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Assign Editor to Publication.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getMyPublicationsInfo(int staffID)
    {
        ResultSet rs = null;
        String query = "select PUBLICATIONS.PublicationID, PUBLICATIONS.Title, PUBLICATIONS.PublicationDate from " +
                       "PUBLICATIONS, EDITEDBY where PUBLICATIONS.PublicationID = EDITEDBY.PublicationID and EDITEDBY.StaffID = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,staffID);
            rs = ps.executeQuery();
            return rs;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private ResultSet getPublicationInfo(int publicationID)
    {
        ResultSet rs = null;
        String query1 = "SELECT * FROM BOOKS WHERE PublicationID=?;";
        String query2 = "SELECT * FROM PERIODICPUBLICATIONS WHERE PublicationID=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setInt(1,publicationID);
            rs = ps.executeQuery();
            if(rs.isBeforeFirst())
            {
                return rs;
            }
            else {
                ps = connection.prepareStatement(query2);
                ps.setInt(1, publicationID);
                rs = ps.executeQuery();
                return rs;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void addArticle(Article article) {
        String query = "INSERT INTO ARTICLES VALUES(?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,article.getArticleID());
            ps.setString(4,article.getTitle());
            ps.setString(3,article.getText());
            ps.setDate(2, article.getCreationDate());
            ps.setInt(5,article.getPublicationID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Article Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteArticle(int articleId) {
        String query = "DELETE FROM ARTICLES WHERE ArticleID = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,articleId);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully deleted Article Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addChapters(Chapter chapter) {
        String query = "INSERT INTO CHAPTERS VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,chapter.getChapterID());
            ps.setString(4,chapter.getTitle());
            ps.setString(3,chapter.getText());
            ps.setDate(2, chapter.getCreationDate());
            ps.setString(5,chapter.getCategory());
            ps.setInt(6,chapter.getPublicationID());
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully inserted Chapter Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteChapterInfo(int chapterId) {
        String query = "DELETE FROM CHAPTERS where ChapterID=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,chapterId);
            int result = ps.executeUpdate();
            if(result == 1)
                System.out.println("Successfully Deleted Chapter Details.");
            else
                System.out.println("Unsuccessful.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
