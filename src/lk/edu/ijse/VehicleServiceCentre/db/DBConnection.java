/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.edu.ijse.VehicleServiceCentre.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private final Connection conn;
    private static DBConnection dbConnection=null;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/VehicleServiceCentreMgtSystem","root","19980728");
    }
    
    public Connection getConnection(){
        return conn;
    }
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;      
    }
   
}
