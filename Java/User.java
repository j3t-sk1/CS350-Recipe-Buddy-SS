package Java;

import java.sql.*;
import java.util.*;

public class User {
    Connection connect = null;
    private boolean favorite;
    private String user_name;
    private String user_bio;
    private String user_picture;
    private List<String> user_recipes;
    private List<String> pantry;
    private String comments;

    public User(SQLConnect db, boolean fav, String userName, String userBio, String userPic, List<String> userRecipes, List<String> p, String c) {
        this.connect = db.getConn();
        this.favorite = fav;
        this.user_name = userName;
        this.user_bio = userBio;
        this.user_picture = userPic;
        this.user_recipes = userRecipes;
        this.pantry = p;
        this.comments = c;
    }

    public void Create() throws SQLException {
        try {
            PreparedStatement ps = connect.prepareStatement
            ("insert into recipebuddy.users (fav, userName, userBio, userPic, userRecipes, p, c) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, favorite);
            ps.setString(2, user_name);
            ps.setString(3, user_bio);
            ps.setString(4, user_picture);
            ps.setString(5, String.join(" ", user_recipes));
            ps.setString(6, String.join(" ", pantry));
            ps.setString(7, comments);
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

}