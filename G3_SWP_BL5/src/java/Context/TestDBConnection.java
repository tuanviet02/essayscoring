/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author VIETHUNG
 */


public class TestDBConnection {
    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        Connection connection = dbContext.connection;
        
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Kết nối CSDL thành công!");
            } else {
                System.out.println("Kết nối CSDL thất bại hoặc null!");
            }
        } catch (Exception ex) {
            System.out.println("Lỗi khi kiểm tra trạng thái kết nối: " + ex.getMessage());
        }
    }
}
