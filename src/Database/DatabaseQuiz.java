package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQuiz {

    // check whether the leaderboard_id exists, if the leaderboard_id exists, print 1
    public static int selectByLeaderboardid(Integer leaderboard_id) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from leaderboard_quizzes where leaderboard_id = '" + leaderboard_id + "'");

            // if the id is exists, print 1, if not, nothing
            if (rs.next()) {
                return 1;
            } else{
                return 0;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
        return 0;
    }


    // create quiz_id
    public static void creatQuizId(Integer leaderboardId, String quizId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();

            String sql = "insert into leaderboard_quizzes(leaderboard_id, quiz_id) values (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, leaderboardId);
            stmt.setString(2, quizId);

            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("successfull leaderboard insert!");
            } else {
                System.out.println("fail to insert in leaderboard");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
    }


    // update the score of quiz
    public static void updateQuizscore(Integer score, Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();

            String sql = "update leaderboard_quizzes set score = ? where leaderboard_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, score);
            stmt.setInt(2, id);

            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("successfull Quiz update!");
            } else {
                System.out.println("fail to update quiz");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
    }

    // rank Quiz Score
    public static String rankQuizscore() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuffer temp = new StringBuffer();

        try {
            conn = JDBCUtils.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from leaderboard_quizzes order by score desc");

            while (rs.next()) {
                temp.append(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "\n");


            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
        return temp.toString();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        creatQuizId(1,"leaderboard");
//        creatQuizId(DatabasePersonal.searchID("Boris"),DatabasePersonal.searchName(DatabasePersonal.searchID("Boris")));
//        updateQuizscore(10,3);
//        updateQuizscore(20,18);
//        updateQuizscore(20,19);
        System.out.println( rankQuizscore());

//        System.out.println(selectByLeaderboardid(DatabasePersonal.searchID("OriginalIbby")));

    }

}
