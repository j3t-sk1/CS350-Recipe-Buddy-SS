package com.shanghaishark.recipebuddy.Java;
import java.sql.*;
import java.util.*;


public class ServeScaler{
private SQLConnect data;

public ServeScaler(SQLConnect d){
    this.data = d;
}
public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public String Try2(int indx, int servesize) throws SQLException{
        PreparedStatement ps = data.getConn().prepareStatement
        ("select * from recipebuddy.recipes where id = " + indx + ", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE");
        ResultSet rs = ps.executeQuery();
        String ing = rs.getString(2);
        String desc = rs.getString(3);
        int ss = rs.getInt(7);
        servesize /= ss;
        String[] ings = ing.split(" ");
        int largest = 0;
        ArrayList<Integer> Sortind = new ArrayList<>(); 
        ArrayList<Integer> Sorting = new ArrayList<>();
        for(int i = 0; i < ings.length - 1; i++){
            Integer temp; 
            while((temp = desc.indexOf(ings[i])) != -1){
                Sortind.add(temp);
                Sorting.add(i);
            }
        }
        ArrayList<Integer> SortSorted = Sortind;
        SortSorted.sort(Collections.reverseOrder());
        int k = 0;
        for(int i : SortSorted){
            int temp;
            temp = Sortind.indexOf(i);
            Collections.swap(Sorting, k, Sorting.get(temp));
            k++;
        }
        for(int i = 0; i < SortSorted.size() - 1; i++){
            String descsplit = desc.substring(0 , SortSorted.get(i));
            String[] numsplit = descsplit.split(" ");
            for(int n = numsplit.length - 1; n < 0; n--){
                if(isNumeric(numsplit[n])){
                    Integer x = (Integer.parseInt(numsplit[n]) * servesize);
                    numsplit[n] = x.toString();
                    break;
                }
            }
            desc = (String.join("",numsplit)) + Sortind.get(i) + desc.substring(SortSorted.get(i)); 

        }
        return desc;
    }
}








