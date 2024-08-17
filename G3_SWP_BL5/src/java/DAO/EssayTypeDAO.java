/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.EssayType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class EssayTypeDAO extends DBContext{
    
    public List<EssayType> getAllType() {
        List<EssayType> list = new ArrayList<>();
        String sql = "select * from essaytype";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new EssayType(rs.getInt("type_id"), rs.getString("type_name")));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public int getTotalType() {
        int total = 0;
        String sql = "select count(*) from essaytype";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }
    
    public List<EssayType> listTypePaging(int index) {
        List<EssayType> list = new ArrayList<>();
        String sql = "select * from essaytype order by type_id limit 5 offset ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index-1)*5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new EssayType(rs.getInt("type_id"), rs.getString("type_name")));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public int getTotalTypeBySearch(String search) {
        int total = 0;
        String sql = "select count(*) from essaytype where type_name like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<EssayType> searchEssayType(String search, int index) {
        List<EssayType> list = new ArrayList<>();
        String sql = "select * from essaytype where type_name like ? order by type_id limit 5 offset ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, (index-1)*5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new EssayType(rs.getInt("type_id"), rs.getString("type_name")));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public List<EssayType> sortEssayType(String sort, int index) {
        List<EssayType> list = new ArrayList<>();
        String sql = "select * from essaytype where 1=1";
        if(sort.equals("ID")){
            sql += " order by type_id limit 5 offset ?";
        }
        if(sort.equals("Name")){
            sql += " order by type_name limit 5 offset ?";
        }
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index-1)*5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new EssayType(rs.getInt("type_id"), rs.getString("type_name")));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public void createNewType(EssayType essayType) {
        try {
            String sql = "insert into essaytype(type_name) values(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,essayType.getTypeName());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public EssayType getTypeById(int id) {
        EssayType essayType = new EssayType();
        String sql="select * from essaytype where type_id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {            
                essayType.setTypeId(rs.getInt("type_id"));
                essayType.setTypeName(rs.getString("type_name"));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return essayType;
    }

    public void updateType(EssayType essayType) {
        try {
            String sql = "update essaytype set type_name=? where type_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, essayType.getTypeName());
            ps.setInt(2, essayType.getTypeId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
