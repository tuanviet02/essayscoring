/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Role;
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
public class RoleDAO extends DBContext {

    public Role get(int id) {
        String sql = "SELECT * FROM role where role_id =?";
        Role role = null;
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = setRoleFormBD(resultSet);
            }
        } catch (SQLException e) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return role;
    }

    public ArrayList<Role> getAll() {
        String sql = "SELECT * FROM role";
        ArrayList<Role> roles = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = setRoleFormBD(resultSet);
                roles.add(role);
            }
        } catch (SQLException e) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return roles;
    }

    private Role setRoleFormBD(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setRole_id(resultSet.getInt("role_id"));
        role.setRole_name(resultSet.getString("role_name"));
        return role;
    }

}
