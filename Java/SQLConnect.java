package Java;
import java.sql.*;
import java.util.*;


public class SQLConnect {
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    public void ConnectDB() throws Exception {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        //Sets up connection
        connect = DriverManager.getConnection
        ("jdbc:mysql://localhost/recipebuddy", "root", "@pingpong");
        } catch (Exception e) {
            throw e;
        }
    }
    public void readDataBase() throws Exception {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery
            ("select * from recipebuddy.recipes");
            writeResultSet(resultSet);
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
        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println
            ("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    public void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String rName = resultSet.getString(1);
            String iG = resultSet.getString(2);
            String iN = resultSet.getString(3);
            String uT = resultSet.getString(4);
            Integer cR = resultSet.getInt(5);
            Integer pT = resultSet.getInt(6);
            Integer sS = resultSet.getInt(7);
            Integer oT = resultSet.getInt(8);
            String pic = resultSet.getString(9);
            System.out.println("RecipeName: " + rName);
            System.out.println("Ingredients: " + iG);
            System.out.println("Instructions: " + iN);
            System.out.println("Utensils " + uT);
            System.out.println("Rating: " + cR);
            System.out.println("PrepTime: " + pT);
            System.out.println("ServingSize: " + sS);
            System.out.println("OvenTemp: " + oT);
            System.out.println("PictureUrl: " + pic);
        }
    }
    public void testIn() throws SQLException{
        prepStatement = connect.prepareStatement
        ("insert into recipebuddy.comments (recipename, ingredients, datum, summary) values (?, ?, ?, ?)");
        java.sql.Date sqlDate = java.sql.Date.valueOf("2022-03-31");
        prepStatement.setString(1, "a"); //1st arg.
        prepStatement.setString(2, "b"); //2nd arg.
        prepStatement.setDate(3, sqlDate); //3rd arg.
        prepStatement.setString(4, "d"); //4th arg.
        prepStatement.executeUpdate(); 

        prepStatement = connect.prepareStatement
        ("SELECT myuser, webpage, datum, summary, COMMENTS from recipebuddy.comments");
        resultSet = prepStatement.executeQuery();
    }
    
    public Connection getConn() {
        return connect;
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
