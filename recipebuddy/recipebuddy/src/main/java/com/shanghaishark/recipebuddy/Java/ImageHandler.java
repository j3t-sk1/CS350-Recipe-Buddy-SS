package com.shanghaishark.recipebuddy.Java;
import java.net.*;
import java.io.*;
public class ImageHandler {

    public String addUrl(String m, String in){
        String imgurl =  "https://www.themealdb.com/api/json/v1/1/search.php?s=" + m;
        String contents = getUrlContents(imgurl);
        contents = parse(contents, in);
        return contents;
    }
    private String parse(String s, String in){
        if(s.equals("{\"meals\":null}")){
            System.out.println("No valid images");
            return null;
        }
        else {
            System.out.println(s + "\n");
            String[] arr1 = s.split(".jpg");
            int i = 0;
            while(i < arr1.length){
                if(arr1[i].contains(in)){break;}
                i++;
            }
            String[] arr2;
            if(i != arr1.length){
                arr2 = arr1[i].split("/");
            }
            else{arr2 = arr1[0].split("/");}
            return "https://www.themealdb.com/images/media/meals/" + arr2[arr2.length - 1] + ".jpg/preview";
        }

    }
    private String getUrlContents(String imgurl){
        StringBuilder content = new StringBuilder();
        try{
            URL url = new URL(imgurl);
            URLConnection urlCon = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader
            (new InputStreamReader(urlCon.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
            bufferedReader.close();
        }
        catch(Exception e){e.printStackTrace();}
        return content.toString();
    }
}

