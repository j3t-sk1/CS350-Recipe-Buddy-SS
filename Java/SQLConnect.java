package Java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
            ("jdbc:mysql://localhost/feedback?" + "user=root&password=@pingpong");
            //Use this to issue SQL queries
            statement = connect.createStatement();
            //Gets the result of querie
            resultSet = statement.executeQuery
            ("select * from feedback.comments");
            writeResultSet(resultSet);
            //Preped can use variables 
            prepStatement = connect.prepareStatement
            ("insert into feedback.comments values default, ?, ?, ?, ?, ?, ?)");
            // myuser, webpage, datum, summary, COMMENTS from feeback.comments"
            prepStatement.setString(1, null);
            prepStatement.setString(2, null);
            prepStatement.setString(3, null);
            prepStatement.setString(4, null);
            prepStatement.setString(5, null);
            prepStatement.setString(6, null);
            prepStatement.executeUpdate();

            prepStatement = connect.prepareStatement
            ("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            resultSet = prepStatement.executeQuery();
            writeResultSet(resultSet);

            prepStatement = connect.prepareStatement
            ("delete from feedback.comments where myuser= ? ; ");
            prepStatement.setString(1, "Test");
            prepStatement.executeUpdate();

            resultSet = statement.executeQuery
            ("select * from feedback.comments");
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
            Date date = resultSet.getDate("datum");
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
