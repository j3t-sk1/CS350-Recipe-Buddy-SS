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
        
        PreparedStatement ps = connect.prepareStatement
        ("select * from recipebuddy.recipes where" + s + "in" + searchInput);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
      {
        //Needs more getStrings and the like
        String info = rs.getString(3);
        
        //Delimiter example
        //info.split("");

        // print the results
        System.out.format("%s\n", info);
      }
      ps.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
}

}
