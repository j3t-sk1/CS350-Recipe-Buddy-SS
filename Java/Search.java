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
      ArrayList<String> rNames = new ArrayList<String>(); 
      //Usnames, Ing, Ut;
      Integer [] prep;  
      Statement st = connect.createStatement();
      ResultSet rs = st.executeQuery("SELECT * recipebuddy.recipes");
      
      while (rs.next()){
        rNames.add(rs.getString("recipename")); 
      }
      //Takes input from text bar and splits per word
      String [] words = searchInput.split(" ");
      for (String s : words){
        //Recipe name
        int temp = rNames.indexOf(s);
        if (temp >= 0){}
        //User
        //Ingredients
        //Utensils
        //Prep time
      } 
    }catch(Exception e) {
        throw e;
      }
    }
    private <T> Indexes(T search){
      return search;
    } 
    void fSearch(String s){
        try{
        PreparedStatement ps = connect.prepareStatement
        ("select * from recipebuddy.recipes where recipeName like 'pizza'");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
      {
        //Needs more getStrings and the like
        String recipeName = rs.getString(1);
        String ingredients = rs.getString(2);

        // print the results
        System.out.println("Search results when searching for 'Pizza':");
        System.out.format("%s \n", recipeName);//, ingredients); //
      }
      ps.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
}

}
