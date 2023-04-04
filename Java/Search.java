package Java;
import java.sql.*;
import java.util.*;

public class Search {
    private String searchInput = null;
    Connection connect = null; 
    
    public Search(Connection c, String input){
      this.connect = c;
      this.searchInput = input; 
    }
    void input() throws Exception{
      try{
      String [] rNames, Usnames, Ing, Ut;
      Integer [] prep;  
      Statement st = connect.createStatement();
      ResultSet rs = st.executeQuery("SELECT * recipebuddy.recipes");
      
      while (rs.next()){
        //rNames.push(rs.getString("recipename")); 
      }
      //Takes input from text bar and splits per word
      String [] words = searchInput.split(" ");
      for (String s : words){
        //Recipe name
        //if (s = ){}
        //User
        //Ingredients
        //Utensils
        //Prep time
      } 
    }catch(Exception e) {
        throw e;
      }
    }
    void fSearch(String s){
        try{
        // String query = "SELECT * FROM recipebuddy.recipe WHERE" + s + "IN (" + searchInput + ")"; 
        // Statement st = connect.createStatement();
        // ResultSet rs = st.executeQuery(query);
        
   //     PreparedStatement ps = connect.prepareStatement
        //("select * from recipebuddy.recipes where ingredients in " + searchInput);
        //("select * from recipebuddy.recipes where ingredients in (" + searchInput + ")");
        PreparedStatement ps = connect.prepareStatement
        //("select * from recipebuddy.recipes where recipeName in (Pizza)");
        ("select * from recipebuddy.recipes where recipeName like 'pizza'");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
      {
        //Needs more getStrings and the like
        String recipeName = rs.getString(1);
        String ingredients = rs.getString(2);
        //Delimiter example
        //info.split("");

        // print the results
        System.out.println("Search results when searching for 'Pizza'");
        System.out.format("%s \n", recipeName);//, ingredients); //
      }
      ps.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
}

}
