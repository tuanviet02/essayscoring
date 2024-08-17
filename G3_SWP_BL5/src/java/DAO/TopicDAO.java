/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Criteria;
import Model.EssayType;
import Model.Level;
import Model.Topic;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TopicDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public int getTotalTopic() {
        int total = 0;
        String sql = "select count(*) from topic";
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

    public List<Topic> listTopicPaging(int index) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "order by t.status desc limit 5 offset ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level();
                l.setLevelId(rs.getInt("level_id"));
                l.setLevelName(rs.getString("level_name"));
                EssayType e = new EssayType();
                e.setTypeId(rs.getInt("type_id"));
                e.setTypeName(rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getTotalTopic(int userId) {
        int total = 0;
        String sql = "select count(*) from topic where user_id = ?";
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

    public List<Topic> listTopicPaging(int index,int userId) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id where user_id = ?\n"
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
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalTopicByFilter(String level, String type,int userId) {
        int total = 0;
        String sql = "select count(*) from topic where user_id = ? and 1=1";
        if (level != null && level != "") {
            sql += " and level_id = " + level;
        }
        if (type != null && type != "") {
            sql += " and type_id = " + type;
        }
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

    public List<Topic> filterTopic(String level, String type,int userId) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where user_id = ? and 1=1 ";
        if (level != null && level != "") {
            sql += " and t.level_id = " + level;
        }
        if (type != null && type != "") {
            sql += " and t.type_id = " + type;
        }
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalTopicBySearch(String search) {
        int total = 0;
        String sql = "SELECT count(*) FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where t.title like ? or et.type_name like ? or l.level_name like ?\n";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<Topic> searchTopicPaging(int index, String search) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where t.title like ? or et.type_name like ? or l.level_name like ?\n"
                + "order by t.topic_id limit 5 offset ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setInt(4, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getTotalTopicBySearch(String search,int userId) {
        int total = 0;
        String sql = "SELECT count(*) FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where t.user_id=? and (t.title like ? or et.type_name like ? or l.level_name like ?)\n";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<Topic> searchTopicPaging(int index, String search,int userId) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where t.user_id=? and (t.title like ? or et.type_name like ? or l.level_name like ?)\n"
                + "order by t.topic_id limit 5 offset ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ps.setInt(5, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Topic> listEssayPaging(int index) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join writter_essay w on t.topic_id = w.essay_id\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where w.writer_id = 4\n" //fix temporarily id of writer 
                + "order by t.topic_id limit 5 offset ?";
        try {
            ps = connection.prepareStatement(sql);
            //ps.setInt(1, writerId);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void createNewTopic(Topic topic) {
        try {
            //insert into table topic
            String sql = "insert into topic(title,created_date,update_date,description, level_id,type_id,status,user_id) \n"
                    + "values(?,?,?,?,?,?,'Pending',?)";
            LocalDate currentDate = java.time.LocalDate.now();
            String date = currentDate.toString();
            ps = connection.prepareStatement(sql);
            ps.setString(1, topic.getTitle());
            ps.setString(2, date);
            ps.setString(3, date);
            ps.setString(4, topic.getDescription());
            ps.setInt(5, topic.getLevel().getLevelId());
            ps.setInt(6, topic.getType().getTypeId());
            ps.setInt(7, topic.getUser().getUserID());
            ps.executeUpdate();

            //get the latest inserted id of topic
            String sql_get_id = "select @@identity as sid";
            PreparedStatement ps_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = ps_get_id.executeQuery();
            if (rs.next()) {
                topic.setTopicId(rs.getInt("sid"));
            }
            //insert into topic_criteria
            String sql2 = "insert into topic_criteria(topic_id,criteria_id) values(?,?)";
            for (Criteria c : topic.getCriteriaList()) {
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, topic.getTopicId());  //id of topic
                ps2.setInt(2, c.getCriteriaID());   //id of criteria
                ps2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Topic getTopicById(int id) {
        Topic topic = new Topic();
        String sql = "select * from topic t\n"
                + "join level l on t.level_id = l.level_id\n"
                + "join essaytype et on et.type_id = t.type_id\n"
                + "join topic_criteria tc on t.topic_id = tc.topic_id\n"
                + "join criteria c on tc.criteria_id = c.criteria_id join user u on u.user_id = t.user_id where t.topic_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                topic.setTopicId(rs.getInt("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setCreatedDate(rs.getDate("created_date"));
                topic.setUpdatedDate(rs.getDate("update_date"));
                topic.setDescription(rs.getString("description"));

                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                topic.setLevel(l);
                topic.setType(e);
                User u = new User();
                u.setUserID(rs.getInt("user_id"));
                u.setFullName(rs.getString("fullname"));
                topic.setUser(u);
                
                int criteriaId = rs.getInt("criteria_id");
                if (criteriaId != 0) {
                    Criteria c = new Criteria();
                    c.setCriteriaID(criteriaId);
                    c.setCriteriaName(rs.getString("criteria_name"));
                    c.setDescription(rs.getString(18));
                    topic.getCriteriaList().add(c);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return topic;
    }

    public void updateTopic(Topic topic) {
        try {
            //insert into table topic
            String sql = "update topic "
                    + "set title = ?,update_date=?,description=?,level_id=?,"
                    + "type_id=?,status='Pending',user_id=?\n"
                    + "where topic_id=?";
            LocalDate currentDate = java.time.LocalDate.now();
            String date = currentDate.toString();

            ps = connection.prepareStatement(sql);
            ps.setString(1, topic.getTitle());
            ps.setString(2, date);
            ps.setString(3, topic.getDescription());
            ps.setInt(4, topic.getLevel().getLevelId());
            ps.setInt(5, topic.getType().getTypeId());
            ps.setInt(6, topic.getUser().getUserID());
            ps.setInt(7, topic.getTopicId());
            ps.executeUpdate();

            //remove the old checked criteria     old cid 1,2 new cid 1,2,3. then remove old 1,2
            String sql_remove = "delete from topic_criteria where topic_id=?";
            PreparedStatement ps1 = connection.prepareStatement(sql_remove);
            ps1.setInt(1, topic.getTopicId());
            ps1.executeUpdate();

            //insert into topic_criteria
            String sql2 = "insert into topic_criteria(topic_id,criteria_id) values(?,?)";
            for (Criteria c : topic.getCriteriaList()) {
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, topic.getTopicId());  //id of topic
                ps2.setInt(2, c.getCriteriaID());   //id of criteria
                ps2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Topic> getAllTopic() {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level();
                l.setLevelId(rs.getInt("level_id"));
                EssayType e = new EssayType();
                e.setTypeId(rs.getInt("type_id"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Level> getAllLevel() {
        ResultSet rs = null;

        List<Level> listC = new ArrayList<>();
        String sql = "select * from level";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new Level(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return listC;
    }

    public List<EssayType> getAllEssay() {
        ResultSet rs = null;

        List<EssayType> listC = new ArrayList<>();
        String sql = "select * from essaytype";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new EssayType(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return listC;
    }

    // Method to retrieve all topics
    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        String query = "SELECT * FROM topic";
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTopicId(resultSet.getInt("topic_id"));
                topic.setTitle(resultSet.getString("title"));
                topic.setCreatedDate(resultSet.getDate("created_date"));
                topic.setUpdatedDate(resultSet.getDate("update_date"));
                topic.setDescription(resultSet.getString("description"));
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                topic.setLevel(level);
                EssayType essayType = new EssayType();
                essayType.setTypeId(resultSet.getInt("type_id"));
                topic.setType(essayType);
                topic.setStatus(resultSet.getString("status"));
                User user = new User();
                user.setUserID(resultSet.getInt("user_id"));
                topic.setUser(user);
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }
   public static void main(String[] args) {
       TopicDAO topicDAO = new TopicDAO();
     List<Topic> topics =  topicDAO.getTopicsByTypeAndLevel(2, 4);
       for (Topic topic : topics) {
           System.out.println(topic);
       }
   }
    public List<Topic> getTopicsByTypeAndLevel(Integer typeId, Integer levelId) {
        List<Topic> topics = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM topic WHERE status = 'Approved' AND 1=1");

        if (typeId != null) {
            queryBuilder.append(" AND topic.type_id = ").append(typeId);
        }

        if (levelId != null) {
            queryBuilder.append(" AND topic.level_id = ").append(levelId);
        }

        String query = queryBuilder.toString();

        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTopicId(resultSet.getInt("topic_id"));
                topic.setTitle(resultSet.getString("title"));
                topic.setCreatedDate(resultSet.getDate("created_date"));
                topic.setUpdatedDate(resultSet.getDate("update_date"));
                topic.setDescription(resultSet.getString("description"));
                Level level = new Level();
                level.setLevelId(resultSet.getInt("level_id"));
                topic.setLevel(level);
                EssayType essayType = new EssayType();
                essayType.setTypeId(resultSet.getInt("type_id"));
                topic.setType(essayType);
                topic.setStatus(resultSet.getString("status"));
                User user = new User();
                user.setUserID(resultSet.getInt("user_id"));
                topic.setUser(user);
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public List<Topic> filter(String level, String type, String status) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id\n"
                + "where 1=1 ";
        if (level != null && level != "") {
            sql += " and t.level_id = " + level;
        }
        if (type != null && type != "") {
            sql += " and t.type_id = " + type;
        }
        if (status != null && status != "") {
            sql += " and t.status = '" + status + "'";
        }

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));

                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Topic> listTopicsSort(int index, String sort) {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topic t\n"
                + "join essaytype et on t.type_id = et.type_id\n"
                + "join level l on t.level_id = l.level_id where 1=1\n";
        
        if (sort.equals("ID")||sort.equals("")) {
            sql += " order by t.topic_id limit 5 offset ?";
        }
        if (sort.equals("Title")) {
            sql += " order by t.title limit 5 offset ?";
        }
        if (sort.equals("Level")) {
            sql += " order by l.level_name limit 5 offset ?";
        }
        if (sort.equals("Type")) {
            sql += " order by et.type_name limit 5 offset ?";
        }
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level l = new Level(rs.getInt("level_id"), rs.getString("level_name"));
                EssayType e = new EssayType(rs.getInt("type_id"), rs.getString("type_name"));
                User u = new User();
                u.setUserID(rs.getInt("user_id"));
                list.add(new Topic(rs.getInt("topic_id"), rs.getString("title"),
                        rs.getDate("created_date"), rs.getDate("update_date"),
                        rs.getString("description"), l, e,
                        rs.getString("status"), u));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateStatusTopic(int topicId, String status) {
        try {

            String sql = "update topic set update_date=?,status=?\n"
                    + "where topic_id=?";
            LocalDate currentDate = java.time.LocalDate.now();
            String date = currentDate.toString();

            ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, status);
            ps.setInt(3, topicId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
