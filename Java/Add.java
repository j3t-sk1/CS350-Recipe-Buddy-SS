package Java;

import java.sql.*;
import java.util.*;

public class Add{
    Connection connect = null;
    //Recipe atributes 
    private String recipeName;
    private List<String> ingredients;
    private List<String> instructions;
    private List<String> utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;
    //private String pic;

    public Add(Connection c) {
        this.connect = c;
        st = connect.creatatemteSent();
    }
    public Add(Connection c, String rN, List<String> iG, 
    String iN, List<String> uT, int cR, int pT, int sS, int oT){
        this.connection = c;
        this.recipeName = rN;
        this.ingredients = iG;
        this.instructions = iN;
        this.utensils = uT;
        this.chefRate = cR;
        this.prepTime = pT;
        this.serveSize = sT;
        this.oTemp = oT;
    }

    void Create() throws SQLException {
        try {
            PreparedStatement ps = connect.prepareStatement
            ("instert into recipebuddy.recipies 
            (recipename, ingredients, instructions, utensils, chefrate, preptime, servesize, ovent) 
            values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, recipeName);
            ps.setString(2, String.join(" ", ingredients));
            ps.setString(3, instructions);
            ps.setString(4, String.join(" ", utensils));
            ps.setInt(5, chefRate);
            ps.setInt(6, prepTime);
            ps.setInt(7, serveSize);
            ps.setInt(8, oTemp);
            ps.executeUpdate();

            ps.close();
        } catch (Exception e){
            System.out.println(e.toString(););
        }
    }

}