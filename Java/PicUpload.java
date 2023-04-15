package Java;
import java.io.*;
import java.sql.*;

public class PicUpload {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/mydatabase";
        String filePath = "/path/to/image.jpg"; // replace with the path to your image file

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                File imageFile = new File(filePath);
                String imageName = imageFile.getName();
                InputStream inputStream = new FileInputStream(imageFile);

                statement.setString(1, imageName);
                statement.setBlob(2, inputStream);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Image uploaded successfully.");
                } else {
                    System.out.println("Image upload failed.");
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
