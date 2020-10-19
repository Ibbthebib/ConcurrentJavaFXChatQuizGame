package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseGame {
	
	
	// create game id
		public static void createGameId(Integer leaderboardId, String gameId) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "insert into leaderboard_game(leaderboard_id, quiz_id) values (?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, leaderboardId);
				stmt.setString(2, gameId);

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
	
	// update the score of game
		public static void updateGamescore(Integer score, Integer id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				String sql = "update leaderboard_game set score = ? where leaderboard_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, score);
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

		// rank Game Score
		public static void rankGamescore() {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				conn = JDBCUtils.getConnection();

				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from leaderboard_game order by score desc");

				while (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
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
