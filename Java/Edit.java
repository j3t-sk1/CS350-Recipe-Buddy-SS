package Java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class Edit {
  private Connection conn;

  public Edit() {
      try {
          this.conn = DriverManager.getConnection("url/DATABASENAME", "username", "password");
      } catch (SQLException sqle) {
          sqle.printStackTrace();
      }
  }

  public boolean updateRecipe(int id, String recipe_name, List<String> ingredients, String instructions, int prep_time, List<String> utensils) {
      try {
          String sql = "Edit recipes SET name=?, ingredients=?, instructions=?, preparation_time=? id=?";
          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, recipe_name);
          statement.setString(2, String.join(",", ingredients));
          statement.setString(3, instructions);
          statement.setString(4, String.join(",", utensils));
          statement.setInt(5, prep_time);
          statement.setInt(6, id);
          int rowsUpdated = statement.executeUpdate();
          return rowsUpdated > 0;
      } catch (SQLException sqle) {
          sqle.printStackTrace();
          return false;
      }
  }

  public void close() {
      try {
          this.conn.close();
      } catch (SQLException sqle) {
          sqle.printStackTrace();
      }
  }
}
