
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.EvaluatorEssay;
import Model.Topic;
import Model.User;
import Model.WriterEssay;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author AnhTH
 */
public class EvaluatorEssayDAO extends DBContext{
        PreparedStatement ps;
    ResultSet rs;
    public boolean createEvaluatorEssay(EvaluatorEssay evaluator) {
    String sql = "INSERT INTO evaluator_essay (writter_essay_id, feedback_content, score, evaluator_id,created_date) VALUES (?,?,?,?,NOW())";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, evaluator.getWriterEssay().getWriterEsssayId());
        ps.setString(2, evaluator.getFeedbackContent());
        ps.setDouble(3, evaluator.getScore());
        ps.setInt(4, evaluator.getEvaluator().getUserID());
        
        int rowsAffected = ps.executeUpdate();
        return (rowsAffected > 0);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean updateEvaluatorEssay(EvaluatorEssay evaluatorEssay) {
    String sql = "UPDATE evaluator_essay " +
                 "SET feedback_content = ?, score = ? " +
                 "WHERE evaluator_essay_id = ?";
    try {
        ps = connection.prepareStatement(sql);
        ps.setString(1, evaluatorEssay.getFeedbackContent());
        ps.setDouble(2, evaluatorEssay.getScore());
        ps.setInt(3, evaluatorEssay.getEvaluatorEssayID());
        
        int rowsAffected = ps.executeUpdate();
        return (rowsAffected > 0);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

   


 

    public int countAllEvaluatorEssayByWriterID(int id) {
        int total = 0;
        String sql = "select count(*)from evaluator_essay e "
                + "join writter_essay w on w.writter_essay_id = e.writter_essay_id\n"
                + "join user u on u.user_id = w.writer_id "
                + "join topic t on t.topic_id = w.essay_id "
                + "where u.user_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<EvaluatorEssay> getListEvaluatorEssayPagingByWriterID(int id, int index) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();
        String sql = "select e.evaluator_essay_id, t.topic_id, t.title, "
                + "w.contend_essay, w.writter_essay_id, e.feedback_content, "
                + "e.score from evaluator_essay e join writter_essay w "
                + "on w.writter_essay_id = e.writter_essay_id "
                + "join user u on u.user_id = w.writer_id join topic t "
                + "on t.topic_id = w.essay_id where u.user_id = ? "
                + "order by e.evaluator_essay_id limit 5 offset ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topic_id"));
                topic.setTitle(rs.getString("title"));
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                writerEssay.setContentEssay(rs.getString("contend_essay"));
                writerEssay.setEssay(topic);
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getInt("score"));
                evaluatorEssay.setWriterEssay(writerEssay);
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluatorEssayList;
    }
    
    public EvaluatorEssay getEvaluatorEssayByID(int id) {
        EvaluatorEssay evaluatorEssay = null;
        String sql = "select e.evaluator_essay_id, t.topic_id, t.title, t.description, "
                + "w.contend_essay, w.writter_essay_id, e.feedback_content, "
                + "e.score from evaluator_essay e join writter_essay w "
                + "on w.writter_essay_id = e.writter_essay_id "
                + "join user u on u.user_id = w.writer_id join topic t "
                + "on t.topic_id = w.essay_id where e.evaluator_essay_id = ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setDescription(rs.getString("description"));
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                writerEssay.setContentEssay(rs.getString("contend_essay"));
                writerEssay.setEssay(topic);
                evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getInt("score"));
                evaluatorEssay.setWriterEssay(writerEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluatorEssay;
    }

    public double getAverageScoreByUserID(int userID) {
        double averageScore = 0.0;
        int totalScores = 0;
        List<EvaluatorEssay> evaluatorEssayList = getListEvaluatorEssayByUserID(userID);
        for (EvaluatorEssay evaluatorEssay : evaluatorEssayList) {
            totalScores += evaluatorEssay.getScore();
        }
        if (evaluatorEssayList.size() > 0) {
            // Tính điểm trung bình
            averageScore = (double) totalScores / evaluatorEssayList.size();
        }
        return averageScore;
    }

// Phương thức để lấy ra danh sách các điểm của một người dùng (user) dựa trên userID
    public List<EvaluatorEssay> getListEvaluatorEssayByUserID(int userID) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();
        String sql = "SELECT * FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng EvaluatorEssay từ kết quả truy vấn và thêm vào danh sách
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
               
                evaluatorEssay.setScore(rs.getDouble("score"));
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluatorEssayList;
    }

    public List<EvaluatorEssay> get20RecentWriterEssays(int userID) {
        List<EvaluatorEssay> recentEvaluatorEssays = new ArrayList<>();

        String sql = "SELECT e.*, w.*, u.* FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "JOIN user u ON u.user_id = e.evaluator_id "
                + "WHERE w.writer_id = ? "
                + "ORDER BY e.evaluator_essay_id DESC "
                + "LIMIT 20";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                // Điền các trường thông tin khác của evaluatorEssay
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getDouble("score"));
                // Thiết lập thông tin của người đánh giá (evaluator)
                User evaluator = new User();
                evaluator.setUserID(rs.getInt("evaluator_id"));
                // Thiết lập thông tin của bài luận
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                // Điền các thông tin khác của bài luận nếu cần thiết
                evaluatorEssay.setWriterEssay(writerEssay);
                recentEvaluatorEssays.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recentEvaluatorEssays;
    }

    public double calculateAverage20Score(int userID) {
        double averageScore = 0.0;
        int totalCount = 0;
        double totalScore = 0.0;

        List<EvaluatorEssay> recentEvaluatorEssays = get20RecentWriterEssays(userID);

        // Tính tổng số điểm
        for (EvaluatorEssay evaluatorEssay : recentEvaluatorEssays) {
            totalScore += evaluatorEssay.getScore();
            totalCount++;
        }
        if (totalCount > 0) {
            averageScore = totalScore / totalCount;
        }

        return averageScore;
    }

    public List<EvaluatorEssay> getEvaluatorEssaysInRangeByUserID(int userID, int minEssays, int maxEssays) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();

        if (minEssays > maxEssays) {
            int temp = minEssays;
            minEssays = maxEssays;
            maxEssays = temp;
        }

        String sql = "SELECT e.* FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ? "
                + "ORDER BY e.evaluator_essay_id DESC "
                + "LIMIT ? OFFSET ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, maxEssays - minEssays + 1);
            ps.setInt(3, minEssays - 1);

            rs = ps.executeQuery();

            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getDouble("score"));
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evaluatorEssayList;
    }

    public List<EvaluatorEssay> getEvaluatorEssaysByScoreRange(int userID, double minScore, double maxScore) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();

        String sql = "SELECT e.* FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ? AND e.score BETWEEN ? AND ? "
                + "ORDER BY e.evaluator_essay_id DESC";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setDouble(2, minScore);
            ps.setDouble(3, maxScore);

            rs = ps.executeQuery();

            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getDouble("score"));
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evaluatorEssayList;
    }

    public List<EvaluatorEssay> get20EvaluatorEssaysByScoreRange(int userID, double minScore, double maxScore) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();

        String sql = "SELECT e.*, w.writter_essay_id AS writer_essay_id FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ? AND e.score BETWEEN ? AND ? "
                + "ORDER BY e.evaluator_essay_id DESC LIMIT 20";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setDouble(2, minScore);
            ps.setDouble(3, maxScore);

            rs = ps.executeQuery();

            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getDouble("score"));
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evaluatorEssayList;
    }

    public int countAllEvaluatorEssayByScoreRange(int userID, double minScore, double maxScore) {
        int totalCount = 0;
        String sql = "SELECT COUNT(*) FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ? AND e.score BETWEEN ? AND ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setDouble(2, minScore);
            ps.setDouble(3, maxScore);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    public int count20EvaluatorEssayByScoreRange(int userID, double minScore, double maxScore) {
        int totalCount = 0;
        String sql = "SELECT COUNT(*) FROM (SELECT e.evaluator_essay_id, e.feedback_content, e.score FROM evaluator_essay e "
                + "JOIN writter_essay w ON w.writter_essay_id = e.writter_essay_id "
                + "WHERE w.writer_id = ? AND e.score BETWEEN ? AND ? "
                + "ORDER BY e.evaluator_essay_id DESC LIMIT 20) AS subquery";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setDouble(2, minScore);
            ps.setDouble(3, maxScore);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    public List<EvaluatorEssay> getListNRecentEvaluatorEssays(int n) {
        List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();
        String sql = "SELECT DISTINCT e.evaluator_essay_id, t.topic_id, t.title, "
                + "w.contend_essay, w.writter_essay_id, e.feedback_content, "
                + "e.score FROM evaluator_essay e JOIN writter_essay w "
                + "ON w.writter_essay_id = e.writter_essay_id "
                + "JOIN topic t ON t.topic_id = w.essay_id "
                + "ORDER BY e.evaluator_essay_id DESC LIMIT ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, n);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topic_id"));
                topic.setTitle(rs.getString("title"));
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                writerEssay.setContentEssay(rs.getString("contend_essay"));
                writerEssay.setEssay(topic);
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getInt("score"));
                evaluatorEssay.setWriterEssay(writerEssay);
                evaluatorEssayList.add(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluatorEssayList;
    }

    public List<WriterEssay> getListTopNEssaysByNumberOfWriters(int n) {
        List<WriterEssay> topNEssays = new ArrayList<>();
        String sql = "SELECT DISTINCT w.writter_essay_id, w.contend_essay, COUNT(w.writer_id) AS writer_count "
                + "FROM writter_essay w "
                + "GROUP BY w.writter_essay_id "
                + "ORDER BY writer_count DESC "
                + "LIMIT ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, n);
            rs = ps.executeQuery();
            while (rs.next()) {
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                writerEssay.setContentEssay(rs.getString("contend_essay"));
                topNEssays.add(writerEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topNEssays;
    }

public int getEvaluatorEssayIDByWriterEssayID(int writerEssayID) {
    int evaluatorEssayID = -1; 
    
    String sql = "SELECT evaluator_essay_id FROM evaluator_essay WHERE writter_essay_id = ?";
    
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerEssayID);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            evaluatorEssayID = rs.getInt("evaluator_essay_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return evaluatorEssayID;
}
public List<EvaluatorEssay> listEvaluatorEssay(int pageNo, int pageSize) {
    List<EvaluatorEssay> evaluatorEssayList = new ArrayList<>();
    int start = (pageNo - 1) * pageSize;
    String sql = "SELECT e.evaluator_essay_id, t.topic_id, t.title, " +
                 "w.contend_essay, w.writter_essay_id, e.feedback_content, " +
                 "e.score FROM evaluator_essay e JOIN writter_essay w " +
                 "ON w.writter_essay_id = e.writter_essay_id " +
                 "JOIN topic t ON t.topic_id = w.essay_id " +
                 "ORDER BY e.evaluator_essay_id LIMIT ? OFFSET ?";
    
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, pageSize);
        ps.setInt(2, start); 
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Topic topic = new Topic();
            topic.setTopicId(rs.getInt("topic_id"));
            topic.setTitle(rs.getString("title"));
            
            WriterEssay writerEssay = new WriterEssay();
            writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
            writerEssay.setContentEssay(rs.getString("contend_essay"));
            writerEssay.setEssay(topic);
            
            EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
            evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
            evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
            evaluatorEssay.setScore(rs.getInt("score"));
            evaluatorEssay.setWriterEssay(writerEssay);
            
            evaluatorEssayList.add(evaluatorEssay);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return evaluatorEssayList;
}
public int getTotalEvaluatorEssay() {
        int total = 0;
        String sql = "select count(*) from evaluator_essay";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }
public boolean isWriterEssayAlreadyEvaluated(int writerEssayID) {
   
    String sql = "SELECT COUNT(*) AS count FROM evaluator_essay WHERE writter_essay_id = ?";
    
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerEssayID);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false;
}
  public EvaluatorEssay getEvaluatorEssayByWitterEssayID(int id) {
        EvaluatorEssay evaluatorEssay = null;
        String sql = "select e.evaluator_essay_id, t.topic_id, t.title, t.description, "
                + "w.contend_essay, w.writter_essay_id, e.feedback_content, "
                + "e.score from evaluator_essay e join writter_essay w "
                + "on w.writter_essay_id = e.writter_essay_id "
                + "join user u on u.user_id = w.writer_id join topic t "
                + "on t.topic_id = w.essay_id where w.writter_essay_id = ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setDescription(rs.getString("description"));
                WriterEssay writerEssay = new WriterEssay();
                writerEssay.setWriterEsssayId(rs.getInt("writter_essay_id"));
                writerEssay.setContentEssay(rs.getString("contend_essay"));
                writerEssay.setEssay(topic);
                evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                evaluatorEssay.setFeedbackContent(rs.getString("feedback_content"));
                evaluatorEssay.setScore(rs.getInt("score"));
                evaluatorEssay.setWriterEssay(writerEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluatorEssay;
    }
 public static void main(String[] args) {
    EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
    
           EvaluatorEssay eval=   evaluatorEssayDAO.getEvaluatorEssayByID(2);
     System.out.println(eval);
  
     
 }
}

