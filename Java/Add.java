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
    private String pic; //new

    public Add(){}
    public Add(SQLConnect Data, String rN, ArrayList<String> iG, 
    String iN, ArrayList<String> uT, int cR, int pT, int sS, int oT, String pC){ //added pC (pic)
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
        this.pic = pC;
    }
    
    public Add(SQLConnect Data, String rN, List<String> iG,
     String iN, List<String> uT, int cR, int pT, int sS, int oT, String pC) {
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
        this.pic = pC;
    }

    public void Create(int size) throws SQLException {
        try {
            PreparedStatement ps = connect.prepareStatement
            ("insert into recipebuddy.recipes (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp, pic, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); //modified
            ps.setString(1, recipeName);
            ps.setString(2, String.join(" ", ingredients));
            ps.setString(3, instructions);
            ps.setString(4, String.join(" ", utensils));
            ps.setInt(5, chefRate);
            ps.setInt(6, prepTime);
            ps.setInt(7, serveSize);
            ps.setInt(8, oTemp);
            ps.setString(9, pic); 
            ps.setInt(10, size + 1); 
            ps.executeUpdate();            
            ps.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public int Create() throws SQLException {
        String query = "INSERT INTO recipebuddy.recipes (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp, pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, recipeName);
        pstmt.setString(2, String.join(",", ingredients));
        pstmt.setString(3, instructions);
        pstmt.setString(4, String.join(",", utensils));
        pstmt.setInt(5, chefRate);
        pstmt.setInt(6, prepTime);
        pstmt.setInt(7, serveSize);
        pstmt.setInt(8, oTemp);
        pstmt.setString(9, pic);
        pstmt.executeUpdate();
        ResultSet keys = pstmt.getGeneratedKeys();
        int newRecipeID = 0;
        if (keys.next()) {
            newRecipeID = keys.getInt(1);
        }
        return newRecipeID;
    }
    
} 
