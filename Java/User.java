package Java;

import java.sql.*;
import java.util.*;

public class User {
    Connection connect = null;
    private boolean favorite;
    private String user_name;
    private String user_bio;

    public User(boolean favorite, String user_name, String user_bio) {
        this.favorite = favorite;
        this.user_name = user_name;
        this.user_bio = user_bio;
    }

    public Boolean setFavorite(Boolean favorite) {
        return this.favorite = favorite;
    }

    public String setUserName(String user_name) {
        return this.user_name = user_name;
    }

    public String setUserBio(String user_bio) {
        return this.user_bio = user_bio;
    }
}