/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;


/**
 *
 * @author chamindu
 */

public class IDgenaratorController {
   public static String getLastID(String tableName, String colName) throws SQLException, ClassNotFoundException {
       try {
           String sql = "select " + colName + " from " + tableName + " order by 1 desc limit 1";
           Connection conn = DBConnection.getInstance().getConnection();
           
           Statement stm = conn.createStatement();
           ResultSet rst=stm.executeQuery(sql);
           if (rst.next()) {
               return  rst.getString(1);
           }  
           
       } catch (Exception ex) {
           Logger.getLogger(IDgenaratorController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
 
}
