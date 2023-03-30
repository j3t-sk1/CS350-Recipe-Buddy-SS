package Java;
import java.sql.*;
import java.util.*;


public class SQLConnect {
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Sets up connection
            connect = DriverManager.getConnection
            ("jdbc:mysql://localhost/recipebuddy", "root", "@pingpong");
            //Use this to issue SQL queries
            statement = connect.createStatement();
            //Gets the result of querie
            //resultSet = statement.executeQuery
            //("select * recipebuddy.comments");
            //writeResultSet(resultSet);
            //Preped can use variables 
            //prepStatement = connect.prepareStatement
            //("insert into recipebuddy.comments values (default, null, null, null, null, null, null)");

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 1953);
            cal.set(Calendar.MONTH, Calendar.NOVEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 26);
            
            java.util.Date utilDate = cal.getTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            // myuser, webpage, datum, summary, COMMENTS from feeback.comments"
            prepStatement.setString(1, "a");
            prepStatement.setString(2, "b");
            prepStatement.setString(3, "c");
            prepStatement.setDate(4, sqlDate);
            prepStatement.setString(5, "d");
            prepStatement.setString(6, "e");
            prepStatement.executeUpdate(); 

            prepStatement = connect.prepareStatement
            ("SELECT myuser, webpage, datum, summary, COMMENTS from recipebuddy.comments");
            resultSet = prepStatement.executeQuery();
            writeResultSet(resultSet);

            prepStatement = connect.prepareStatement
            ("delete from recipebuddy.comments where myuser= ? ; ");
            prepStatement.setString(1, "Test");
            prepStatement.executeUpdate();

            resultSet = statement.executeQuery
            ("select * from recipebuddy.comments");
            writeMetaData(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        System.out.println("The colums in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println
            ("Colum " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            java.sql.Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
