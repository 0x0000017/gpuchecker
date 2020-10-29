/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection connection = null;
    public Connection getConnection() {
        
        String addr = "jdbc:mysql://localhost/";
        String dbname = "ItemsDB";
        String uname = "root";
        String pw = ""; 
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(addr + dbname, uname, pw);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return connection;
        
    }
    
    
}
