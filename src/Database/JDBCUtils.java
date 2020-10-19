package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static String connectionURL = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/";
    private static String username = "norwich";
    private static String password = "uwyha4amar";


    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionURL, username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // close all the part that occupied the space.
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null)rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            if (stmt != null)stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            if (conn != null)conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}

