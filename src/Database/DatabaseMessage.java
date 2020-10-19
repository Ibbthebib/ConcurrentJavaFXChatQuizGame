package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseMessage {
	// -----------------------Insert-----------------------

	// insert the message
	public static void insert(Integer myId, Integer friendId, String message) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "insert into message(my_id, friend_id, message) values (?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, myId);
			stmt.setInt(2, friendId);
			stmt.setString(3, message);

			int result = stmt.executeUpdate();
			if (result > 0) {
				System.out.println("successfull insert!");
			} else {
				System.out.println("fail to insert");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}
	
	
	// -----------------------Search-----------------------
	// Search for message through my id and friend id
	public static void searchMessage(Integer myId, Integer friendId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select message from message where my_id = ? and friend_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, myId);
			stmt.setInt(2, friendId);
			
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				System.out.println(rs.getInt("message"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
