package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabasePersonal {

	// -----------------------Search-----------------------
	public static void selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			System.out.println("Whether successfully connect to the databases" + conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users");

			// print out all the data in the table
			while (rs.next()) {
				System.out.println(rs.getString(1) + "," + rs.getString(2) + rs.getString(3) + "," + rs.getString(4)
						+ "," + rs.getString(5));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}

	}

	// check whether the input username and password successully match the username
	// and password in the databases.
	public static boolean selectByUsernamePassword(String username, String password) {
		// in order to catch exception, the variables have to be local variables.
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();

			stmt = conn.createStatement();

			String sql = "select * from users where username = '" + username + "' and password = '" + password + "'";
			rs = stmt.executeQuery(sql);

			// as search based on the username and password, there are only two result: get
			// one set of data, or nothing.
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return false;
	}

// Search Id
	public static int searchID(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select id from users where username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				return(rs.getInt("id"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return 0;
	}

// Search the name 
	public static String searchName(Integer Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select last_name, first_name from users where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Id);
			
			
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				return (rs.getString("last_name") + " " + rs.getString("first_name"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return "";
	}

    // Search the username
	public static String searchUsername(Integer Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select username from users where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Id);
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				return (rs.getString("username"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return "";
	}
	
	// Search Email
	public static String searchEmail(Integer Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select email from users where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Id);
			
			
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				return (rs.getString("email"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return "";
	}
	
	
	// Search Program
	public static String searchProgram(Integer Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "select program from users where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Id);
			
			
			
			rs = stmt.executeQuery();
			// print out all the data in the table
			while (rs.next()) {
				return (rs.getString("program"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return "";
	}
	

	// -----------------------Insert-----------------------

	// Sign up
	public static void insert(String username, String password, String Lastname, String Firstname, String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "insert into users(username, password, last_name, first_name, email) values (?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, Lastname);
			stmt.setString(4, Firstname);
			stmt.setString(5, email);

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
	
	// -----------------------Update-----------------------
	// update the username
	public static void updatePersonalUsername(String newUsername, Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update users set username = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newUsername);
			stmt.setInt(2, id);

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

	// update the last name
	public static void updatePersonalLastname(String newLastname, Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update users set last_name = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newLastname);
			stmt.setInt(2, id);

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

	// update the first name
	public static void updatePersonalFirstname(String newFirstname, Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update users set first_name = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newFirstname);
			stmt.setInt(2, id);

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

	// update the email;
	public static void updatePersonalEmail(String newEmail, Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update users set email = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newEmail);
			stmt.setInt(2, id);

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

	// update the program
	public static void updatePersonalProgram(String newProgram, Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getConnection();

			String sql = "update users set program = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newProgram);
			stmt.setInt(2, id);

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

	// -----------------------Delete-----------------------
		public static void delete(Integer id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "delete from users where id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(searchUsername(searchID("Test")));
		System.out.println(searchEmail(searchID("Test")));
		System.out.println(searchName(searchID("Test")));
		System.out.println(searchProgram(searchID("Test")));
		System.out.println(searchID("Test"));

//		insert("Test","123123","Testing","Purposes","Testing@hotmail.co.uk");


	}

}
