import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Add {
  private int id;
  private String recipe_name;
  private List<String> ingredients;
  private String instructions;
  private List<String> utensils;
  private int chef_rating;
  private int prep_time;
  private int serve_size;
  

  public Add(int id, String recipe_name, List<String> ingredients, String instructions, List<String> utensils, int chef_rating, int prep_time, int serve_size) {
      this.id = id;
      this.recipe_name = recipe_name;
      this.ingredients = ingredients;
      this.instructions = instructions;
      this.utensils = utensils;
      this.chef_rating = chef_rating;
      this.prep_time = prep_time;
      this.serve_size = serve_size;

      try {
        Connection conn = DriverManager.getConnection("url/databasename", "username", "password");
        String sql = "stuffing";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, recipe_name);
        statement.setString(2, String.join(",", ingredients));
        statement.setString(3, instructions);
        statement.setString(4, String.join(",", utensils));
        statement.setInt(5, prep_time);
        statement.executeUpdate();
        conn.close();
      } catch (SQLException sqle) {
          sqle.printStackTrace();
      }
  }


  public int getId() {
    return id;
  }
  public String getRecipe_name() {
      return recipe_name;
  }
  public List<String> getIngredients() {
    return ingredients;
  }
  public String getInstructions() {
    return instructions;
  }
  public List<String> getUtensils() {
    return utensils;
  } 
  public int getchef_rating(){
    return chef_rating;
  }
  public int getPrep_time() {
    return prep_time;
  }
  public int getServingSize() {
    return serve_size;
  }

  

  public void setId(int id) {
    this.id = id;
  }
  public void setrecipe_name(String recipe_name) {
      this.recipe_name = recipe_name;
  }
  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }
  public void setInstructions(String instructions) {
      this.instructions = instructions;
  }
  public void setutensils(List<String> utensils) {
    this.utensils = utensils;
  }
  public void setchef_rating(int chef_rating){
    this.chef_rating = chef_rating;
  }
  public void setPrep_time(int prep_time) {
    this.prep_time = prep_time;
  }
  public void setServingSize(int servingSize) {
    this.serve_size = servingSize;
}
  
}
