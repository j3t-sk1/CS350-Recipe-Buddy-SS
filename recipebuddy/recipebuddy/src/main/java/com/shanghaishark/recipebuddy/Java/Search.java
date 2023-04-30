package com.shanghaishark.recipebuddy.Java;
import java.sql.*;
import java.util.*;

import org.aspectj.weaver.bcel.FakeAnnotation;



public class Search {
    private String searchInput = null;
    private ArrayList<String> allergens = new ArrayList<>();
    private ArrayList<String> utensils = new ArrayList<>();
    static Connection connect = null; 
    
    public Search(Connection c, String input, ArrayList<String> a, ArrayList<String> u){
      Search.connect = c;
      this.searchInput = input; 
      this.allergens = a;
      this.utensils = u;
    }
    
    ArrayList<Integer> getindx() throws Exception{
      try{
      ArrayList<String> rNames = new ArrayList<>(); 
      ArrayList<String> inG = new ArrayList<>(); 
      ArrayList<String> inS = new ArrayList<>(); 
      ArrayList<String> uT = new ArrayList<>(); 
      ArrayList<Integer> chefR = new ArrayList<>(); 
      ArrayList<Integer> prepT = new ArrayList<>(); 
      ArrayList<Integer> servS = new ArrayList<>(); 
      ArrayList<Integer> ovT = new ArrayList<>(); 
      ArrayList<String> url = new ArrayList<>(); 
      //Usnames, Ing, Ut; 
      Statement st = connect.createStatement();
      ResultSet rs = st.executeQuery("SELECT * from recipebuddy.recipes");
      while (rs.next()){
        rNames.add(rs.getString(1)); 
        inG.add(rs.getString(2));
        uT.add(rs.getString(4)); 
        chefR.add(rs.getInt(5)); 
        prepT.add(rs.getInt(6)); 
        servS.add(rs.getInt(7)); 
      }
      Integer databaseSize = rNames.size();
      //Takes input from text bar and splits per word
      String [] words = searchInput.split(" ");
      ArrayList<Integer> indx = new ArrayList<>();
      for (String s : words){
        for(Integer i = 0; i < databaseSize; i++){
          if(isInteger(s)){ 
          int temp = Integer.parseInt(s);
          if((temp == chefR.get(i)) || (temp == prepT.get(i)) || (temp == servS.get(i))){indx.add(i);}
        }
          else if((s.equals(rNames.get(i))) || (s.equals(inG.get(i))) || (s.equals(uT.get(i)))){
            indx.add(i);}
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
      ArrayList<Integer> temp = new ArrayList<>();
      PreparedStatement ps = connect.prepareStatement
      ("select * from recipebuddy.recipes limit 1");
      ResultSet rs = ps.executeQuery();
      rs.next();
      int lowestid = rs.getInt(10);
      for(int i = 0; i < list.size(); i++){
        temp.add(list.get(i) + lowestid);
      }
      return temp;
    } 
    String RecipeToString(ResultSet rs) throws SQLException{
      String s;
      s = "Recipe: ";
      for(int i = 1; i <= 7; i++) {
          s += rs.getString(i);    
      }
      return s;
    }
    boolean MyContains(String a, String b){
      boolean temp = true;
      String[] words = b.split(" ");
      for(String s : words){
        if(a.contains(s) == false){
          temp = false;
          break;
        }
      }
      return temp;
    }

    void fSearch(String s, Boolean allerg, Boolean uten){
        try{
        //Get indexes of all recipies with matching strings/intsc
        ArrayList<Integer> indx = removeDuplicates(getindx()); 
        indx = IndxScale(indx);
        PreparedStatement ps = connect.prepareStatement
        ("select * from recipebuddy.recipes where id in (" + ListIntToString(indx) + ")", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery();
        //Filter Allergens and Utensils
        if(allerg || uten){
        while (rs.next()){
          if(allerg){
          for(String a :allergens){
            if(((rs.getString("ingredients")).contains(a)) == false){
              rs.deleteRow();
              continue;
            }
          }
        }
          if(uten){
            for(String u : utensils){
              if(!(rs.getString(4).contains(u))){
                rs.deleteRow();
                continue;
            }
          }
        }
          if(MyContains(RecipeToString(rs), s) == false){
            rs.deleteRow();
            continue;
        }
      }
    }
      //End Filter

      //Run through and output
        while (rs.next()){
        String recipeName = rs.getString(1);
        Integer prepTime = rs.getInt(6);

        // print the results
        System.out.format("%s %d \n", recipeName, prepTime);
      }
      ps.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
  }
}

