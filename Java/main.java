package Java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class main {
    public static void main(String[] args) throws Exception {
        SQLConnect Ahhh = new SQLConnect();
        Ahhh.readDataBase();
    }
}
