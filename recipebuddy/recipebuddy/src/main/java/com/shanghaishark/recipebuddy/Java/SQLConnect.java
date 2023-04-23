package com.shanghaishark.recipebuddy.Java;
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
    
    public Connection getConn() {
        return connect;
    }
    public int getSize() throws SQLException{
        PreparedStatement ps = this.getConn().prepareStatement
        ("select count(*) from recipebuddy.recipes");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
    public void close() {
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
