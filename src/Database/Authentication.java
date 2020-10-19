package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public static int AuthenticateUser(String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/",
                    "norwich", "uwyha4amar");

            String sql = "SELECT * from users WHERE username='" + username + "' and password='" + password + "'";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(AuthenticateUser("Ibby", "123123"));
    }
}