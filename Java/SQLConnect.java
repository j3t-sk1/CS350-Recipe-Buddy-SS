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
        this.statement = connect.createStatement();
        this.resultSet = statement.executeQuery
        ("select * from recipebuddy.comments");
        } catch (Exception e) {
            throw e;
        }
    }
    public void readDataBase() throws Exception {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery
            ("select * from recipebuddy.comments");
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
//AddVVVVVV
    public boolean addRecipe(Add recipe) {
        String query = "INSERT INTO recipes (name, serving size, prep time, ingredients, instructions, utensils, rating, oven temp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, recipe.getRecipeName());
            statement.setString(2, String.join(",", recipe.getIngredients()));
            statement.setString(3, String.join(",", recipe.getInstructions()));
            statement.setString(4, String.join(",", recipe.getUtensils()));
            statement.setInt(5, recipe.getChefRate());
            statement.setInt(6, recipe.getPrepTime());
            statement.setInt(7, recipe.getServingSize());
            statement.setInt(8,recipe.getOTemp());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding recipe to database: " + e.getMessage());
            return false;
        }
    }
    //Add^^^^^^^

    
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
