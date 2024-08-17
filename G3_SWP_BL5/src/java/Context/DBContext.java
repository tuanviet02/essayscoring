/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * 
 */
public class DBContext {

    public Connection connection;

    public DBContext() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";

            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";

            String password = "123456";
            String dbName = "swpbl5";
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName, username, password);

        } catch (Exception ex) {
         
        }
    }
}
