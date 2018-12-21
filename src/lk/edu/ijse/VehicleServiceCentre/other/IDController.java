/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.other;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import lk.edu.ijse.VehicleServiceCentre.db.*;


public class IDController {
    public static String getLastID(String tableName, String colName)throws Exception{
        String sql = " SELECT " +colName+ " FROM " +tableName+ " ORDER BY 1 DESC LIMIT 1 ";
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
