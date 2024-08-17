/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Role;

import Model.User;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class UserDAO extends DBContext {
    private RoleDAO roleDAO = new RoleDAO();
 public boolean createUser(User user) {
        String sql = "INSERT INTO User (Email, Password, FullName, Phone, Status, Role_ID, Gender, is_verified, verification_code) VALUES (?, ?, ?, ?, ?, ?, ?, 0, 123456)";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPhone());
            statement.setBoolean(5, user.isStatus());
            statement.setInt(6, user.getRoleId());
            statement.setBoolean(7, user.getGender());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }

    }

public boolean createUser(User user, String otp) {
    String sql = "INSERT INTO User (Email, Password, FullName, Phone, Status, Role_ID, Gender, is_verified, verification_code) VALUES (?, ?, ?, ?, 1, 4, ?, 0, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFullName());
        statement.setString(4, user.getPhone());
        statement.setBoolean(5, user.getGender());
        statement.setString(6, otp); 
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace(); 
        return false;
    }
}
public boolean verifyOTP(String otp) {
    String sql = "SELECT 1 FROM User WHERE verification_code = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, otp);
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next(); 
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
        return false;
    }
}

    public boolean updateUser(User user) {
        String sql = "UPDATE User SET  FullName=?, Phone=?, Gender=?, Avatar=? WHERE Email=?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhone());
            statement.setBoolean(3, user.getGender());
            statement.setString(4, user.getAvatar());
            statement.setString(5, user.getEmail());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

    public boolean updatePasswordUser(String email, String newPassword) {
        String sql = "UPDATE User SET  Password=? WHERE Email=?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserByID(User user) {
        String sql = "UPDATE user SET fullname=?, phone=?, gender=?, role_id = ?, status=? WHERE user_id=?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhone());
            statement.setBoolean(3, user.getGender());
            statement.setInt(4, user.getRole().getRole_id());
            statement.setBoolean(5, user.isStatus());
            statement.setInt(6, user.getUserID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

public boolean checkEmail(String email) {
    String sql = "SELECT 1 FROM User WHERE Email=?";
    boolean emailExists = false;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, email);
        try (ResultSet resultSet = statement.executeQuery()) {
            emailExists = resultSet.next();
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

    return emailExists;
}

    public User login(String username, String password) {
        String sql = "SELECT * FROM User WHERE Email=? AND Password= ?";
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = this.setUserFormBD(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.err.println(user);
        return user;
    }



    public User findByEmail(String email) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM User WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = this.setUserFormBD(rs);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User findById(int id) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM User WHERE user_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = this.setUserFormBD(rs);
                return user;
            }
        } catch (SQLException ex) {
          System.out.println(ex);
        }
        return null;
    }

    public boolean updateUserPassword(String email, String password) {
        try {
            String sql = "UPDATE `User` SET password = ? WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
          System.out.println(ex);
        }
        return false;
    }

  public boolean updateUserVerified(String otp) {
    try {
        String sql = "UPDATE User SET is_verified = 1 WHERE verification_code = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, otp);
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
      System.out.println(ex);
        return false;
    }
}


    public boolean updateStatusAndRole(User user) {
        try {
            PreparedStatement ps;
            String sql = "UPDATE user SET status = ?, role_id = ?  where user_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, user.isStatus());
            ps.setInt(2, user.getRoleId());
            ps.setInt(3, user.getUserID());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
          System.out.println(ex);
        }
        return false;
    }

    public ArrayList<User> getAllUsers(String email, int page, String status, String roleId) {
        String sql = "select * from user where email like ? ";
        String orderBy = " order by user_id DESC limit ? offset ?";
        if (!"".equals(status)) {
            sql += " and status =  " + "'" + status + "'";
        }
        if (!"".equals(roleId)) {
            sql += " and role_id = " + "'" + roleId + "'";
        }
        sql += orderBy;
        ArrayList<User> users = new ArrayList<>();
        int PER_PAGE = 5;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + email + "%");
            ps.setInt(2, PER_PAGE);
            ps.setInt(3, (page - 1) * PER_PAGE);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = setUserFormBD(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public int countGetAllUsers(String email, String status, String roleId) {
        String sql = "select Count(*) from user where email like ? ";
        if (!"".equals(status)) {
            sql += " and status =  " + "'" + status + "'";
        }
        if (!"".equals(roleId)) {
            sql += " and role_id = " + "'" + roleId + "'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + email + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;

    }

   private User setUserFormBD(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(this.roleDAO.get(resultSet.getInt("role_id")));
        user.setRoleId((resultSet.getInt("role_id")));

        user.setCreateDate(resultSet.getDate("create_date"));
        user.setFullName(resultSet.getString("fullname"));
        user.setPhone(resultSet.getString("phone"));
        user.setAvatar(resultSet.getString("avatar"));
        user.setStatus(resultSet.getBoolean("status"));
        user.setGender(resultSet.getBoolean("gender"));
        user.setIs_verified(resultSet.getBoolean("is_verified"));
        user.setVerification_code(resultSet.getString("verification_code"));
        return user;
    }



    public ArrayList<User> getAllAdmin() {
        String sql = "select * from user where role_id = 1";
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = setUserFormBD(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
