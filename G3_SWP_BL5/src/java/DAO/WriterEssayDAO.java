/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.EssayType;
import Model.Level;
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
 * @author admin
 */
public class WriterEssayDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public int getTotalEssay() {
        int total = 0;
        String sql = "select count(*) from writter_essay";
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

   public boolean createWriterEssay(WriterEssay writerEssay) {
    String sql = "INSERT INTO writter_essay (writer_id, essay_id, contend_essay, limited, status, created_date) VALUES (?,?,?,?,?,NOW())";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerEssay.getWriter().getUserID());
        ps.setInt(2, writerEssay.getEssay().getTopicId());
        ps.setString(3, writerEssay.getContentEssay());
        ps.setInt(4, writerEssay.getLimit());
        ps.setString(5, writerEssay.getStatus());
        
        int rowsAffected = ps.executeUpdate();
        return (rowsAffected > 0);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


  public WriterEssay getWriterEssayById(int writerEssayId) {
    String sql = "SELECT we.*, t.*, l.*, et.* FROM writter_essay we " +
                 "INNER JOIN topic t ON we.essay_id = t.topic_id " +
                 "INNER JOIN level l ON t.level_id = l.level_id " +
                 "INNER JOIN essaytype et ON t.type_id = et.type_id " +
                 "WHERE writter_essay_id = ?";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerEssayId);
        rs = ps.executeQuery();
        if (rs.next()) {
            
            WriterEssay writerEssay = new WriterEssay(
                rs.getInt("writter_essay_id"),
                new User(rs.getInt("writer_id")),
                new Topic(
                    rs.getInt("topic_id"),
                    rs.getString("title"),
                    rs.getDate("created_date"),
                    rs.getDate("update_date"),
                    rs.getString("description"),
                    new Level(rs.getInt("level_id"), rs.getString("level_name")),
                    new EssayType(rs.getInt("type_id"), rs.getString("type_name")),
                    rs.getString("status"),
                    null 
                ),
                rs.getString("contend_essay"),
                rs.getInt("limited"),
                rs.getString("we.status")
            );
            
            return writerEssay;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


   public boolean isDuplicateWithinLimit(int writerId, int essayId) {
    String sql = "SELECT COUNT(*) AS duplicate_count " +
                 "FROM writter_essay " +
                 "WHERE writer_id = ? AND essay_id = ?";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerId);
        ps.setInt(2, essayId);
        rs = ps.executeQuery();
        if (rs.next()) {
            int duplicateCount = rs.getInt("duplicate_count");
            return duplicateCount < 3;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
}


    public boolean isPendingStatus(int writerId, int essayId) {
    String sql = "SELECT COUNT(*) AS pending_count " +
                 "FROM writter_essay " +
                 "WHERE writer_id = ? AND essay_id = ? AND status = 'pending'";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerId);
        ps.setInt(2, essayId);
        rs = ps.executeQuery();
        if (rs.next()) {
            int pendingCount = rs.getInt("pending_count");
            return pendingCount > 0; 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
}
public ArrayList<WriterEssay> getAllWriterEssay(int pageNo, int pageSize) {
    ArrayList<WriterEssay> writerEssays = new ArrayList<>();
    int start = (pageNo - 1) * pageSize;
    String sql = "SELECT we.*, t.*, l.*, et.*, u.* FROM writter_essay we " +
                 "INNER JOIN topic t ON we.essay_id = t.topic_id " +
                 "INNER JOIN level l ON t.level_id = l.level_id " +
                 "INNER JOIN essaytype et ON t.type_id = et.type_id " +
                 "INNER JOIN user u ON we.writer_id = u.user_id " +
                 "ORDER BY we.writter_essay_id ASC LIMIT ?, ?";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, start);
        ps.setInt(2, pageSize);
        rs = ps.executeQuery();
        while (rs.next()) {
           WriterEssay writerEssay = new WriterEssay(
                rs.getInt("writter_essay_id"),
                  new User(rs.getInt("writer_id"), rs.getString("fullname")),
                new Topic(
                    rs.getInt("topic_id"),
                    rs.getString("title"),
                    rs.getDate("created_date"),
                    rs.getDate("update_date"),
                    rs.getString("description"),
                    new Level(rs.getInt("level_id"), rs.getString("level_name")),
                    new EssayType(rs.getInt("type_id"), rs.getString("type_name")),
                    rs.getString("status"),
                    null 
                ),
                rs.getString("contend_essay"),
                rs.getInt("limited"),
                rs.getString("we.status")
            );
            writerEssays.add(writerEssay);
        
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return writerEssays;
}
public ArrayList<WriterEssay> searchWriterEssay(String keyword, String status) {
    ArrayList<WriterEssay> writerEssays = new ArrayList<>();
    String sql = "SELECT we.*, t.*, l.*, et.*, u.* FROM writter_essay we " +
            "INNER JOIN topic t ON we.essay_id = t.topic_id " +
            "INNER JOIN level l ON t.level_id = l.level_id " +
            "INNER JOIN essaytype et ON t.type_id = et.type_id " +
            "INNER JOIN user u ON we.writer_id = u.user_id " +
            "WHERE (u.fullname LIKE ? OR t.title LIKE ? OR t.description LIKE ?) " +
            "AND we.status = ? " +
            "ORDER BY we.writter_essay_id ASC";
    try {
        ps = connection.prepareStatement(sql);
        String likeKeyword = "%" + keyword + "%";
        ps.setString(1, likeKeyword);
        ps.setString(2, likeKeyword);
        ps.setString(3, likeKeyword);
        ps.setString(4, status);
        rs = ps.executeQuery();
        while (rs.next()) {
            WriterEssay writerEssay = new WriterEssay(
                    rs.getInt("writter_essay_id"),
                    new User(rs.getInt("writer_id"), rs.getString("fullname")),
                    new Topic(
                            rs.getInt("topic_id"),
                            rs.getString("title"),
                            rs.getDate("created_date"),
                            rs.getDate("update_date"),
                            rs.getString("description"),
                            new Level(rs.getInt("level_id"), rs.getString("level_name")),
                            new EssayType(rs.getInt("type_id"), rs.getString("type_name")),
                            rs.getString("status"),
                            null
                    ),
                    rs.getString("contend_essay"),
                    rs.getInt("limited"),
                    rs.getString("we.status")
            );
            writerEssays.add(writerEssay);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return writerEssays;
}
public boolean updateStatusWritterEssay(int writerEssayId) {
    String sql = "UPDATE writter_essay SET status = 'Done' WHERE writter_essay_id = ?";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, writerEssayId);
        
        int rowsAffected = ps.executeUpdate();
        return (rowsAffected > 0);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public int getTotalEssay(int userId) {
        int total = 0;
        String sql = "select count(*) from writter_essay where writer_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<WriterEssay> getWriterEssay(int userId, int index) {
        List<WriterEssay> list = new ArrayList<>();
        String sql = "SELECT w.*,t.title,et.type_id,et.type_name,l.level_id,l.level_name FROM writter_essay w\n"
                + "join topic t on w.essay_id = t.topic_id\n"
                + "join level l on l.level_id = t.level_id\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "where w.writer_id = ?\n"
                + "order by t.topic_id limit 5 offset ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {

                Level l = new Level();
                l.setLevelId(rs.getInt("level_id"));
                l.setLevelName(rs.getString("level_name"));
                EssayType e = new EssayType();
                e.setTypeId(rs.getInt("type_id"));
                e.setTypeName(rs.getString("type_name"));
                Topic t = new Topic();
                t.setTopicId(rs.getInt("essay_id"));
                t.setTitle(rs.getString("title"));
                t.setLevel(l);
                t.setType(e);
                User u = new User();
                u.setUserID(rs.getInt("writer_id"));

                list.add(new WriterEssay(rs.getInt("writter_essay_id"), u,
                        t, rs.getString("contend_essay"),
                        rs.getInt("limited"), rs.getString("status")));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

    public static void main(String[] args) {
        // Khởi tạo một đối tượng WriterEssayDAO
        WriterEssayDAO writerEssayDAO = new WriterEssayDAO();

        // Thiết lập writer_id và essay_id mong muốn
        int writerId = 25;
        int essayId = 8;

        // Gọi hàm kiểm tra
        if (writerEssayDAO.isPendingStatus(writerId, essayId)) {
            System.out.println("Có bản ghi có status là 'pending' cho writer_id " + writerId + " và essay_id " + essayId);
        } else {
            System.out.println("Không có bản ghi nào có status là 'pending' cho writer_id " + writerId + " và essay_id " + essayId);
        }

    }

    public int getTotalBySearch(String search, int userId) {
        int total = 0;
        String sql = "SELECT count(*) FROM writter_essay w\n"
                + "join topic t on w.essay_id = t.topic_id\n"
                + "join level l on l.level_id = t.level_id\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "where w.writer_id = ? and (t.title like ? or et.type_name like ? or l.level_name like ? or w.status like ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ps.setString(5, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<WriterEssay> searchEssayPaging(int index, String search, int userId) {
        List<WriterEssay> list = new ArrayList<>();
        String sql = "SELECT w.*,t.title,et.type_id,et.type_name,l.level_id,l.level_name FROM writter_essay w\n"
                + "join topic t on w.essay_id = t.topic_id\n"
                + "join level l on l.level_id = t.level_id\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "where w.writer_id = ? and (t.title like ? or et.type_name like ? or l.level_name like ? or w.status like ?)\n"
                + "order by writter_essay_id limit 5 offset ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ps.setString(5, "%" + search + "%");
            ps.setInt(6, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {

                Level l = new Level();
                l.setLevelId(rs.getInt("level_id"));
                l.setLevelName(rs.getString("level_name"));
                EssayType e = new EssayType();
                e.setTypeId(rs.getInt("type_id"));
                e.setTypeName(rs.getString("type_name"));
                Topic t = new Topic();
                t.setTopicId(rs.getInt("essay_id"));
                t.setTitle(rs.getString("title"));
                t.setLevel(l);
                t.setType(e);
                User u = new User();
                u.setUserID(rs.getInt("writer_id"));

                list.add(new WriterEssay(rs.getInt("writter_essay_id"), u,
                        t, rs.getString("contend_essay"),
                        rs.getInt("limited"), rs.getString("status")));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
