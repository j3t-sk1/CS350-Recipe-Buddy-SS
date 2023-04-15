package Java;
import java.sql.*;
import java.util.*;

import com.mysql.cj.util.StringUtils;

public class Search {
    private String searchInput = null;
    private ArrayList<String> allergens = new ArrayList<>();
    private ArrayList<String> utensils = new ArrayList<>();
    static Connection connect = null; 
    
    public Search(Connection c, String input, ArrayList<String> a, ArrayList<String> u){
      this.connect = c;
      this.searchInput = input; 
      this.allergens = a;
      this.utensils = u;
    }
    
    ArrayList<Integer> getindx() throws Exception{
      try{
      ArrayList<String> rNames = new ArrayList<>(); 
      ArrayList<Integer> prepT = new ArrayList<>(); 
      //Usnames, Ing, Ut; 
      Statement st = connect.createStatement();
      ResultSet rs = st.executeQuery("SELECT * from recipebuddy.recipes");
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
    public static String ListIntToString(ArrayList<Integer> list)
    {
      String result = null;
      for(Integer i : list){
        result += ',' + i.toString();
      }
      return result;
    }
    public static ArrayList<Integer> IndxScale(ArrayList<Integer> list) throws SQLException{
      PreparedStatement ps = connect.prepareStatement
      ("select * from recipebuddy.recipes limit 1");
      ResultSet rs = ps.executeQuery();
      rs.next();
      int lowestid = rs.getInt(10);
      for(int i : list){
        i += lowestid - 1;
      }
      return list;
    } 
    void fSearch(String s, Boolean allerg, Boolean uten){
        try{
        //Get indexes of all recipies with matching strings/ints
        System.out.println("Here 1");
        ArrayList<Integer> indx = removeDuplicates(getindx()); 
        System.out.println("Here 2");
        indx = IndxScale(indx);
        System.out.println("Here 3");
        PreparedStatement ps = connect.prepareStatement
        ("select * from recipebuddy.recipes where id in (" + ListIntToString(indx) + ")");
        ResultSet rs = ps.executeQuery();
        System.out.println("Here 4");
        //Filter Allergens and Utensils
        if(allerg || uten){
        while (rs.next()){
          if(allerg){
          for(String a :allergens){
            if((rs.getString("ingredients")).contains(a)){rs.deleteRow();}
          }
        }
          if(uten){
            for(String u : utensils){
              if(!(rs.getString("utensils")).contains(u)){rs.deleteRow();}
          }
        }
      }
    }
      //End Filter

      //Run through and output
        while (rs.next()){
        //Needs more getStrings and the like
        String recipeName = rs.getString(1);
        Integer prepTime = rs.getInt(6);

        // print the results
        System.out.println("Search results");
        System.out.format("%s %d \n", recipeName, prepTime);
      }
      ps.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
  }
}

