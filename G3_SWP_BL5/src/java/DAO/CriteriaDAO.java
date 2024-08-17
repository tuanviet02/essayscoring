/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Criteria;
import Model.CriteriaFeedback;
import Model.EvaluatorEssay;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnhTH
 */
public class CriteriaDAO extends DBContext{
    PreparedStatement ps;
    ResultSet rs;
    public int getTotalCriteria() {
        int total = 0;
        String sql = "select count(*) from criteria";
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
 public List<Criteria> getCriteriaInfoByTopicId(int topicId)  {
        List<Criteria> criteriaList = new ArrayList<>();

        String sql = "SELECT c.criteria_id, c.criteria_name, c.description " +
                     "FROM criteria c " +
                     "INNER JOIN topic_criteria tc ON c.criteria_id = tc.criteria_id " +
                     "WHERE tc.topic_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, topicId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int criteriaId = resultSet.getInt("criteria_id");
                String criteriaName = resultSet.getString("criteria_name");
                String description = resultSet.getString("description");

                Criteria criteria = new Criteria(criteriaId, criteriaName, description);
                criteriaList.add(criteria);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

        return criteriaList;
    }
 
 public List<Criteria> getAllCritaria() {
        List<Criteria> list = new ArrayList<>();
        String sql = "select * from criteria";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new Criteria(rs.getInt("criteria_id"), rs.getString("criteria_name"), rs.getString("description")));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return list;
    }


    public void addCriteria(Criteria criteria) {
        String sql = "INSERT INTO criteria (criteria_name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, criteria.getCriteriaName());
            statement.setString(2, criteria.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCriteria(Criteria criteria) {
        String sql = "UPDATE criteria SET criteria_name = ?, description = ? WHERE criteria_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, criteria.getCriteriaName());
            statement.setString(2, criteria.getDescription());
            statement.setInt(3, criteria.getCriteriaID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 public int deleteCriteria(int criteriaId) {
    String sql = "DELETE FROM criteria WHERE criteria_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, criteriaId);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
public List<Criteria> listCriteria(int pageNo, int pageSize) {
    List<Criteria> criteriaList = new ArrayList<>();
      int start = (pageNo - 1) * pageSize;
    String sql = "SELECT * FROM criteria LIMIT ?, ?";
    
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, start);
        statement.setInt(2, pageSize);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int criteriaId = resultSet.getInt("criteria_id");
            String criteriaName = resultSet.getString("criteria_name");
            String description = resultSet.getString("description");

            Criteria criteria = new Criteria(criteriaId, criteriaName, description);
            criteriaList.add(criteria);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return criteriaList;
}
public boolean isCriteriaNameExists(String criteriaName) {
    String sql = "SELECT COUNT(*) FROM criteria WHERE criteria_name = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, criteriaName);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public Criteria getCriteriaById(int criteriaId) {
    String sql = "SELECT criteria_id, criteria_name, description FROM criteria WHERE criteria_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, criteriaId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("criteria_id");
            String criteriaName = resultSet.getString("criteria_name");
            String description = resultSet.getString("description");
            return new Criteria(id, criteriaName, description);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

//evaluator_essay_id int 
//criteria_id int 
//feedback_content varchar(255)
   public void createFeedbackCriteria(CriteriaFeedback criteriaFeedback) {
        String sql = "INSERT INTO criteria_feedback (evaluator_essay_id, criteria_id,feedback_content) VALUES (?,?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, criteriaFeedback.getEvaluatorEssay().getEvaluatorEssayID());
            statement.setInt(2, criteriaFeedback.getCriteria().getCriteriaID());
            statement.setString(3, criteriaFeedback.getFeedbackContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void editFeedbackCriteria(int feedbackId, String newFeedbackContent) {
    String sql = "UPDATE criteria_feedback SET feedback_content = ? WHERE criteria_feedback_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, newFeedbackContent);
        statement.setInt(2, feedbackId);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
      CriteriaDAO criteriaDAO = new CriteriaDAO();

 criteriaDAO.deleteCriteria(14);
    }
    public List<CriteriaFeedback> getFeedbackCriteriabyEvaluatorEssay(int evaluatorEssayId) {
        List<CriteriaFeedback> criteriaFeedbackList = new ArrayList<>();
        String sql = "SELECT cf.criteria_feedback_id, cf.evaluator_essay_id, cf.criteria_id, cf.feedback_content, c.criteria_name, c.description " +
                     "FROM criteria_feedback cf " +
                     "INNER JOIN criteria c ON cf.criteria_id = c.criteria_id " +
                     "WHERE cf.evaluator_essay_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, evaluatorEssayId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int feedbackId = resultSet.getInt("criteria_feedback_id");
                int criteriaId = resultSet.getInt("criteria_id");
                String feedbackContent = resultSet.getString("feedback_content");
                String criteriaName = resultSet.getString("criteria_name");
                String criteriaDescription = resultSet.getString("description");
                Criteria criteria = new Criteria(criteriaId, criteriaName, criteriaDescription);
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay(); 
                CriteriaFeedback criteriaFeedback = new CriteriaFeedback(feedbackId, criteria, evaluatorEssay, feedbackContent);
                criteriaFeedbackList.add(criteriaFeedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criteriaFeedbackList;
    }

public boolean checkUniqueFeedback(int evaluatorEssayId, int criteriaId) {
    String sql = "SELECT COUNT(*) FROM criteria_feedback WHERE evaluator_essay_id = ? AND criteria_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, evaluatorEssayId);
        statement.setInt(2, criteriaId);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count == 0; 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
 public void deleteCriteriaFeedback(int criteriaFeedbackId) {
        String sql = "DELETE FROM criteria_feedback WHERE criteria_feedback_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, criteriaFeedbackId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  public List<Criteria> getCriteriaByWriterEssayID(int writerEssayId) {
        List<Criteria> criteriaList = new ArrayList<>();

        String sql = "SELECT criteria.criteria_id, criteria.criteria_name, criteria.description " +
                     "FROM writter_essay " +
                     "JOIN topic_criteria ON writter_essay.essay_id = topic_criteria.topic_id " +
                     "JOIN criteria ON topic_criteria.criteria_id = criteria.criteria_id " +
                     "WHERE writter_essay.writter_essay_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, writerEssayId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int criteriaId = resultSet.getInt("criteria_id");
                String criteriaName = resultSet.getString("criteria_name");
                String description = resultSet.getString("description");

                Criteria criteria = new Criteria(criteriaId, criteriaName, description);
                criteriaList.add(criteria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return criteriaList;
    }
public List<CriteriaFeedback> getFeedbackCriteriabywritterEssayID(int writterEssayID) {
        List<CriteriaFeedback> criteriaFeedbackList = new ArrayList<>();
        String sql = "SELECT cf.criteria_feedback_id, cf.evaluator_essay_id, cf.criteria_id, cf.feedback_content, c.criteria_name, c.description " +
                     "FROM criteria_feedback cf " +
                 "INNER JOIN evaluator_essay e ON cf.evaluator_essay_id = e.evaluator_essay_id " +
                 "INNER JOIN writter_essay w ON e.writter_essay_id = w.writter_essay_id " +
                     "INNER JOIN criteria c ON cf.criteria_id = c.criteria_id " +
                   
                     "WHERE w.writter_essay_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, writterEssayID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int feedbackId = resultSet.getInt("criteria_feedback_id");
                int criteriaId = resultSet.getInt("criteria_id");
                String feedbackContent = resultSet.getString("feedback_content");
                String criteriaName = resultSet.getString("criteria_name");
                String criteriaDescription = resultSet.getString("description");
                Criteria criteria = new Criteria(criteriaId, criteriaName, criteriaDescription);
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay(); 
                CriteriaFeedback criteriaFeedback = new CriteriaFeedback(feedbackId, criteria, evaluatorEssay, feedbackContent);
                criteriaFeedbackList.add(criteriaFeedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criteriaFeedbackList;
    }
}
