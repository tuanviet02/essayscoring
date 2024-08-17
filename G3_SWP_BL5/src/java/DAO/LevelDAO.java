/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelDAO extends DBContext {

    public List<Level> getAllLevel() {
        List<Level> list = new ArrayList<>();
        String sql = "SELECT * FROM level";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Level(rs.getInt("level_id"), rs.getString("level_name")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        LevelDAO l = new LevelDAO();
        System.out.println(l.getAllLevel());
    }

    // add thêm level
    public void addLevel(Level level) {
        String query = "INSERT INTO level (level_name) VALUES (?)";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, level.getLevelName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lây tât ca level
    public List<Level> getAllLevels() {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                level.setLevelName(resultSet.getString("level_name"));
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    // update tên level
    public void updateLevel(Level level) {
        String query = "UPDATE level SET level_name=? WHERE level_id=?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, level.getLevelName());
            statement.setInt(2, level.getLevelId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // lây level theo id
    public Level getLevelById(int levelId) {
        Level level = null;
        String query = "SELECT * FROM level WHERE level_id = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, levelId);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    level = new Level();
                    level.setLevelId(resultSet.getInt("level_id"));
                    level.setLevelName(resultSet.getString("level_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level;
    }

// tim kiem level theo ten
    public List<Level> searchLevelsByName(String levelName) {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level WHERE level_name LIKE ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + levelName + "%");
            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Level level = new Level();
                    level.setLevelId(resultSet.getInt("level_id"));
                    level.setLevelName(resultSet.getString("level_name"));
                    levels.add(level);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }
    //lây ra tât c? level bang id descending

    public List<Level> getAllLevelsSortedByIdDescending() {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level ORDER BY level_id DESC";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                level.setLevelName(resultSet.getString("level_name"));
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public List<Level> getAllLevelsSortedByNameDescending() {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level ORDER BY level_name DESC";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                level.setLevelName(resultSet.getString("level_name"));
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public List<Level> getAllLevelsSortedByIdAscending() {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level ORDER BY level_id ASC";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                level.setLevelName(resultSet.getString("level_name"));
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public List<Level> getAllLevelsSortedByNameAscending() {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM level ORDER BY level_name ASC";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                level.setLevelName(resultSet.getString("level_name"));
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public boolean isLevelNameExists(String levelName) {
        String query = "SELECT COUNT(*) FROM level WHERE level_name = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, levelName);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLevelHasTopics(int levelId) {
        String sql = "SELECT COUNT(*) FROM topic WHERE level_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, levelId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteLevel(int levelId) {
        String query = "DELETE FROM level WHERE level_id = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, levelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
