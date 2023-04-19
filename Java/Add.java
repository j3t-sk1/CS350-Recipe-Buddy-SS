package Java;

import java.sql.*;
import java.util.*;

public class Add{
    Connection connect = null;
    //Recipe atributes 
    private SQLConnect database;
    private String recipeName;
    private int size; 
    private List<String> ingredients;
    private String instructions;
    private List<String> utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;

    public Add(){}
    public Add(SQLConnect Data, String rN, ArrayList<String> iG, 
    String iN, ArrayList<String> uT, int cR, int pT, int sS, int oT){ 
        this.database = Data;
        connect = database.getConn();
        this.recipeName = rN;
        this.ingredients = iG;
        this.instructions = iN;
        this.utensils = uT;
        this.chefRate = cR;
        this.prepTime = pT;
        this.serveSize = sS;
        this.oTemp = oT;
    }
    
    public Add(SQLConnect Data, String rN, List<String> iG,
     String iN, List<String> uT, int cR, int pT, int sS, int oT) {
        this.database = Data;
        connect = database.getConn();
        this.recipeName = rN;
        this.ingredients = iG;
        this.instructions = iN;
        this.utensils = uT;
        this.chefRate = cR;
        this.prepTime = pT;
        this.serveSize = sS;
        this.oTemp = oT;
    }

    public void Create() throws SQLException {
        try {
            ImageHandler img = new ImageHandler();
            PreparedStatement ps = connect.prepareStatement
            ("insert into recipebuddy.recipes (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp, pic) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"); //modified
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
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
} 
