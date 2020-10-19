package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseGroup {
	// ------------------user_friend------------------------
	// search : show the friends users have
	public static void selectAllfriend(Integer myId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select * from user_friend where my_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, myId);
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				System.out.println(rs.getString("my_id") + "," + rs.getString("friend_id"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}

	// insert: add friends
	public static void insertFriend(Integer myId, Integer friendId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "insert into user_friend(my_id, friend_id) values (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, friendId);
			stmt.setInt(2, myId);

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

	// delete friend
	public static void deleteFriend(Integer myId, Integer friendId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "delete from user_friend where my_id = ? and friend_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, myId);
			stmt.setInt(2, friendId);

			int result = stmt.executeUpdate();
			// if the username exists, delete one row, otherwise, fail to delete
			if (result > 0) {
				System.out.println("successfull delete!");
			} else {
				System.out.println("fail to delete");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}

	// ------------------user_group_info------------------------
	// insert: create group
	public static void createGroup(Integer ownerId, String groupName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "insert into user_group_info(owner, group_name) values (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ownerId);
			stmt.setString(2, groupName);

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

	// update : update the group name
	public static void updateGroupname(String groupName, Integer groupId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update user_group_info set group_name = ? where group_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			stmt.setInt(2, groupId);

			int result = stmt.executeUpdate();
			if (result > 0) {
				System.out.println("successfull update!");
			} else {
				System.out.println("fail to update");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}
	
	// delete : the owner delete the entire group
	public static void deleteGroup(Integer groupId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "delete from user_group_info where group_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);

			int result = stmt.executeUpdate();
			if (result > 0) {
				System.out.println("successfull delete!");
			} else {
				System.out.println("fail to delete");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
	}

	
	// ------------------user_group------------------------
		// insert : the group that users join
		public static void joinGroup(Integer Id, Integer groupId) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "insert into user_group(id, group_id) values (?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, Id);
				stmt.setInt(2, groupId);

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
		
		// search : show the friends users have
		public static void selectGroup(Integer myId) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "select * from user_group where id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, myId);
				rs = stmt.executeQuery();
				// print out all the data in the table
				while (rs.next()) {
					System.out.println(rs.getString("id") + "," + rs.getString("group_id"));

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				JDBCUtils.close(rs, stmt, conn);
			}
		}
		

		// delete : the users withdraw the group they do not want in anymore
		public static void deleteMyGroup(Integer Id, Integer groupId) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "delete from user_group where id = ? and group_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, Id);
				stmt.setInt(2, groupId);

				int result = stmt.executeUpdate();
				if (result > 0) {
					System.out.println("successfull delete!");
				} else {
					System.out.println("fail to delete");
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
		//  joinGroup(2, 6);
		// selectGroup(2);
		deleteMyGroup(2,6);
	}

}
