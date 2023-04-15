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

    public User(boolean fav, String userName, String userBio, String userPic, List<String> userRecipes, List<String> p) {
        this.favorite = fav;
        this.user_name = userName;
        this.user_bio = userBio;
        this.user_picture = userPic;
        this.user_recipes = userRecipes;
        this.pantry = p;
    }

    public void Create(int size) throws SQLException {
        try {
            PreparedStatement ps = connect.prepareStatement
            ("insert into recipebuddy.users (favorite, user_name, user_bio, user_picture, user_recipes, pantry) values (?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, favorite);
            ps.setString(2, user_name);
            ps.setString(3, user_bio);
            ps.setString(4, user_picture);
            ps.setString(5, String.join(" ", user_recipes));
            ps.setString(6, String.join(" ", pantry));
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

}