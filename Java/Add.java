package Java;

import java.sql.*;
import java.util.*;

public class Add{
    Connection connect = null;
    //Recipe atributes 
    private SQLConnect database;
    private String recipeName;
    private int size; 
    private ArrayList<String> ingredients;
    private String instructions;
    private ArrayList<String> utensils;
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
    } 
