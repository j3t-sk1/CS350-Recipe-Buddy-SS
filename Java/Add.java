package Java;

import java.sql.*;
import java.util.*;

public class Add{
    Connection connect = null;
    //Recipe atributes 
    //private int id; //new
    private SQLConnect database;
    private String recipeName;
    private List<String> ingredients;
    private String instructions;
    private List<String> utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;
    private String pic; //new

    public Add(SQLConnect Data, String rN, List<String> iG, 
    String iN, List<String> uT, int cR, int pT, int sS, int oT, String pC){ //added pC (pic)
        this.database = Data;
        this.recipeName = rN;
        this.ingredients = iG;
        this.instructions = iN;
        this.utensils = uT;
        this.chefRate = cR;
        this.prepTime = pT;
        this.serveSize = sS;
        this.oTemp = oT;
        this.pic = pC;
    }

    void Create() throws SQLException {
        try {
            PreparedStatement ps = connect.prepareStatement
            //("insert into recipebuddy.recipes (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, ovent) values (?, ?, ?, ?, ?, ?, ?, ?)");
            ("insert into recipebuddy.recipes (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp, pic) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"); //modified
            ///ps.setInt(1, id); //new, set this pI to 1
            ps.setString(1, recipeName);
            ps.setString(2, String.join(" ", ingredients));
            ps.setString(3, instructions);
            ps.setString(4, String.join(" ", utensils));
            ps.setInt(5, chefRate);
            ps.setInt(6, prepTime);
            ps.setInt(7, serveSize);
            ps.setInt(8, oTemp);
            ps.setString(9, pic); //new
            ps.executeUpdate();

            //Print
            ResultSet rs = ps.getResultSet();
            database.writeResultSet(rs);
            ps.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

}