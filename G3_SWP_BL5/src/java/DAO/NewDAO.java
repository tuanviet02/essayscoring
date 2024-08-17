/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.New;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class NewDAO extends DBContext {

    private final UserDAO userDAO = new UserDAO();

    public ArrayList<New> getAllNew(String title, int page, String status, String userId) {
        String sql = "select * from news  where ( title like ? or content like ? ) ";
        String orderBy = " order by news_id DESC limit ? offset ?";
        if (!"".equals(status)) {
            sql += " and status =  " + "'" + status + "'";
        }
        if (!"".equals(userId)) {
            sql += " and user_id = " + "'" + userId + "'";
        }
        sql += orderBy;
        ArrayList<New> news = new ArrayList<>();
        int PER_PAGE = 5;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setString(2, "%" + title + "%");
            ps.setInt(3, PER_PAGE);
            ps.setInt(4, (page - 1) * PER_PAGE);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                news.add(setFormBD(resultSet));
            }
            return news;
        } catch (SQLException e) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public int countGetAllNew(String title, int page, String status, String userId) {
        String sql = "select count(*) from news where ( title like ? or content like ? ) ";
        if (!"".equals(status)) {
            sql += " and status =  " + "'" + status + "'";
        }
        if (!"".equals(userId)) {
            sql += " and user_id = " + "'" + userId + "'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setString(2, "%" + title + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    private New setFormBD(ResultSet resultSet) throws SQLException {
        New newdetail = new New(
                resultSet.getInt("news_id"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getDate("created_date"),
                resultSet.getDate("updated_date"),
                resultSet.getInt("user_id"),
                resultSet.getString("status"),
                userDAO.findById(resultSet.getInt("user_id"))
        );
        System.out.println(newdetail.getStatus());
        return newdetail;
    }

    public New findById(int id) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM news WHERE news_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                New newDetail = this.setFormBD(rs);
                return newDetail;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void create(String title, String content, String userId, String status) {
        String sql = "INSERT INto News (title, content, user_id, status, created_date) values (?,?,?,?,?)";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, userId);
            statement.setString(4, status);
            statement.setDate(5, getCurrentDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

    public void udpate(String title, String content, String userId, String status, String id) {
        String sql = "UPDATE News SET  title=?, content=?, user_id=?, status=?, updated_date =? WHERE news_id=?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, userId);
            statement.setString(4, status);
            statement.setDate(5, getCurrentDate());
            statement.setString(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private Date getCurrentDate() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        return date;
    }

    public void delete(String id) {
        String sql = "DELETE From News WHERE news_id=?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
        public ArrayList<New> getAllHide() {

        String sql = "select * from news  where created_date  <= ? and status = 'Active' order by news_id DESC";
        ArrayList<New> news = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, getCurrentDate());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                news.add(setFormBD(resultSet));
            }
            return news;
        } catch (SQLException e) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }


}
