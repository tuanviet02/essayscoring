/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Complaint;
import Model.EvaluatorEssay;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sang
 */
public class ComplaintDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public boolean insertComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaint (complaint_title, content,"
                + " created_date, evaluator_essay_id, status) "
                + "VALUES (?, ?, curdate(),?,?);";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, complaint.getComplaintTitle());
            ps.setString(2, complaint.getContent());
            ps.setInt(3, complaint.getEvaluatorEssay().getEvaluatorEssayID());
            ps.setString(4, complaint.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Complaint getComplaintByID(int id) {
        Complaint complaint = null;
        String sql = "SELECT * FROM complaint c join evaluator_essay e"
                + " on c.evaluator_essay_id = e.evaluator_essay_id "
                + "where c.complaint_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                complaint = new Complaint();
                complaint.setComplaintID(id);
                complaint.setComplaintTitle(rs.getString("complaint_title"));
                complaint.setContent(rs.getString("content"));
                complaint.setCreatedDate(rs.getDate("created_date"));
                complaint.setStatus(rs.getString("status"));
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                complaint.setEvaluatorEssay(evaluatorEssay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaint;
    }

    public int countAllComplaints() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM complaint";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public int countAllComplaintsByWriterID(int id) {
        int total = 0;
        String sql = "select count(*) from complaint c join evaluator_essay e o"
                + "n c.evaluator_essay_id = e.evaluator_essay_id\n"
                + "join writter_essay w on w.writter_essay_id = e.writter_essay_id "
                + "where w.writer_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Complaint> getListComplaintPaging(int index) {
        List<Complaint> complaintList = new ArrayList<>();
        String sql = "SELECT * FROM complaint c JOIN evaluator_essay e "
                + "ON c.evaluator_essay_id = e.evaluator_essay_id "
                + "ORDER BY c.complaint_id LIMIT 5 OFFSET ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                Complaint complaint = new Complaint();
                complaint.setComplaintID(rs.getInt("complaint_id"));
                complaint.setComplaintTitle(rs.getString("complaint_title"));
                complaint.setContent(rs.getString("content"));
                complaint.setCreatedDate(rs.getDate("created_date"));
                complaint.setStatus(rs.getString("status"));
                complaint.setEvaluatorEssay(evaluatorEssay);
                complaintList.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaintList;
    }
    
    public List<Complaint> getListComplaintPagingByWriterID(int id, int index) {
        List<Complaint> complaintList = new ArrayList<>();
        String sql = "select * from complaint c join evaluator_essay e on c.evaluator_essay_id = e.evaluator_essay_id\n" +
"join writter_essay w on w.writter_essay_id = e.writter_essay_id where w.writer_id = ? order by c.complaint_id limit 5 offset ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
                evaluatorEssay.setEvaluatorEssayID(rs.getInt("evaluator_essay_id"));
                Complaint complaint = new Complaint();
                complaint.setComplaintID(rs.getInt("complaint_id"));
                complaint.setComplaintTitle(rs.getString("complaint_title"));
                complaint.setContent(rs.getString("content"));
                complaint.setCreatedDate(rs.getDate("created_date"));
                complaint.setStatus(rs.getString("status"));
                complaint.setEvaluatorEssay(evaluatorEssay);
                complaintList.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaintList;
    }

    public static void main(String[] args) {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        System.out.println(complaintDAO.getComplaintByID(1).getComplaintTitle());
    }
}
