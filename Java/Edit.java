package Java;

import java.sql.*;
import java.util.*;

public class Edit {
    private Connection connect = null;
    private SQLConnect database;
    private String recipeName;
    private List<String> ingredients;
    private String instructions;
    private List<String> utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;
    private String pic;
    private int recipeID;

    public Edit(SQLConnect Data, int rID,String rN, List<String> iG, String iN, List<String> uT, int cR, int pT, int sS, int oT) {
        this.database = Data;
        this.connect = database.getConn();
        this.recipeName = rN;
        this.ingredients = iG;
        this.instructions = iN;
        this.utensils = uT;
        this.chefRate = cR;
        this.prepTime = pT;
        this.serveSize = sS;
        this.oTemp = oT;
        this.recipeID = rID;
    }

    public void Update() throws SQLException {
        try {
            ImageHandler img = new ImageHandler();
            PreparedStatement ps = connect.prepareStatement("UPDATE recipebuddy.recipes SET recipeName = ?, ingredients = ?, instructions = ?, utensils = ?, chefRate = ?, prepTime = ?, serveSize = ?, oTemp = ?, pic =? WHERE id = ?");
            ps.setString(1, recipeName);
            ps.setString(2, String.join(" ", ingredients));
            ps.setString(3, instructions);
            ps.setString(4, String.join(" ", utensils));
            ps.setInt(5, chefRate);
            ps.setInt(6, prepTime);
            ps.setInt(7, serveSize);
            ps.setInt(8, oTemp);
            ps.setString(9, img.addUrl(recipeName, ingredients.get(1)));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}