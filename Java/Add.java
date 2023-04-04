package Java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Add {
  private int id;
  private String recipeName;
  private List<String> ingredients;
  private String instructions;
  private List<String> utensils;
  private int chefRate;
  private int prepTime;
  private int serveSize;
  private int oTemp;
  private String pic;

  public Add(int id, String recipeName, List<String> ingredients, String instructions, List<String> utensils, int chefRate, int prepTime, int serveSize, int oTemp, String pic) {
      this.id = id;
      this.recipeName = recipeName;
      this.ingredients = ingredients;
      this.instructions = instructions;
      this.utensils = utensils;
      this.chefRate = chefRate;
      this.prepTime = prepTime;
      this.serveSize = serveSize;
      this.oTemp = oTemp;
      this.pic = pic;


      try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/recipebuddy.recipes", "root", "@pingpong");
        String sql = "RecipeBuddy"; 
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, id);
        statement.setString(2, recipeName);
        statement.setString(3, String.join(",", ingredients));
        statement.setString(4, instructions);
        statement.setString(5, String.join(",", utensils));
        statement.setInt(6, prepTime);
        statement.setInt(7,oTemp);
        statement.setString(8,pic);

        statement.executeUpdate();
        conn.close();
      } catch (SQLException sqle) {
          sqle.printStackTrace();
        }
  }

  public Add(String recipeName, List<String> ingredients, String instructions, List<String> utensils, int chefRate, int prepTime, int serveSize, int oTemp) {
    this.recipeName = recipeName;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.utensils = utensils;
    this.chefRate = chefRate;
    this.prepTime = prepTime;
    this.serveSize = serveSize;
    this.oTemp = oTemp;

    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/recipebuddy.recipes", "root", "@pingpong");
      String sql = "RecipeBuddy"; 
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(2, recipeName);
      statement.setString(3, String.join(",", ingredients));
      statement.setString(4, instructions);
      statement.setString(5, String.join(",", utensils));
      statement.setInt(6, prepTime);
      statement.setInt(7,oTemp);
      //statement.setString(8,pic);

      statement.executeUpdate();
      conn.close();
    } catch (SQLException sqle) {
        sqle.printStackTrace();
      }
    }

//Recipe.recipes

  public int getId() {
    return id;
  }
  public String getRecipeName() {
      return recipeName;
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
  public int getChefRate(){
    return chefRate;
  }
  public int getPrepTime() {
    return prepTime;
  }
  public int getServingSize() {
    return serveSize;
  }
  public int getOTemp() {
    return oTemp;
  }
  public String getPicture() {
    return pic;
  }
  

  public void setId(int id) {
    this.id = id;
  }
  public void setRecipeName(String recipeName) {
      this.recipeName = recipeName;
  }
  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }
  public void setInstructions(String instructions) {
      this.instructions = instructions;
  }
  public void setUtensils(List<String> utensils) {
    this.utensils = utensils;
  }
  public void setChefRate(int chefRate){
    this.chefRate = chefRate;
  }
  public void setPrepTime(int prepTime) {
    this.prepTime = prepTime;
  }
  public void setServingSize(int serveSize) {
    this.serveSize = serveSize;
  }
  public void setOTemp(int oTemp) {
    this.oTemp = oTemp;
  }


  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Recipe ID: ").append(this.id).append("\n");
    sb.append("Name: ").append(this.recipeName).append("\n");
    sb.append("Ingredients: ").append(String.join(", ", this.ingredients)).append("\n");
    sb.append("Instructions: ").append(String.join(this.instructions)).append("\n");
    sb.append("Utensils: ").append(String.join("\n", this.utensils)).append("\n");
    sb.append("Rating: ").append(this.chefRate).append("/10\n");
    sb.append("Preparation Time: ").append(this.prepTime).append(" minutes\n");
    sb.append("Serves: ").append(this.serveSize).append(" plates\n");
    sb.append("Oven Temp: ").append(this.oTemp).append("\n");
    return sb.toString();
}
  //NICEEEEEEEEE

} 

