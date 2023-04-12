package Java;
import java.sql.*;
import java.util.*;

import com.mysql.cj.util.StringUtils;

public class Search {
    private String searchInput = null;
    Connection connect = null; 
    
    public Search(Connection c, String input){
      this.connect = c;
      this.searchInput = input; 
    }
    
    ArrayList<Integer> getindx() throws Exception{
      try{
      ArrayList<String> rNames = new ArrayList<>(); 
      ArrayList<Integer> prepT = new ArrayList<>(); 
      //Usnames, Ing, Ut; 
      Statement st = connect.createStatement();
      ResultSet rs = st.executeQuery("SELECT * recipebuddy.recipes");
      
      while (rs.next()){
        rNames.add(rs.getString("recipename")); 
        prepT.add(rs.getInt("preptime")); 
      }
      Integer databaseSize = rNames.size();
      //Takes input from text bar and splits per word
      String [] words = searchInput.split(" ");
      ArrayList<Integer> indx = new ArrayList<>();
      for (String s : words){
        for(Integer i = 0; i < databaseSize; i++){
          if(isInteger(s)){ 
          int temp = Integer.parseInt(s);
          if(temp == prepT.get(i)){indx.add(i);}
        }
          else if(s == rNames.get(i)){indx.add(i);}
        }
      } 
      return indx;
    }catch(Exception e) {
        throw e;
      }
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        Set<T> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
  
    void fSearch(String s){
        try{
        ArrayList<Integer> indx = removeDuplicates(getindx());
        PreparedStatement ps = connect.prepareStatement
        ("select * from recipebuddy.recipes where id like " + indx);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
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
