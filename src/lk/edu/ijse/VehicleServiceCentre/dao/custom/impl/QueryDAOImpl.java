/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.QueryDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.SaveNewCustomer;
import lk.edu.ijse.VehicleServiceCentre.model.SaveNewCustomerDTO;

/**
 *
 * @author ACER
 */
public class QueryDAOImpl implements QueryDAO{

    @Override
    public SaveNewCustomer getAllAppointment() throws Exception {
        ArrayList<SaveNewCustomerDTO>customer=new ArrayList<>();
        String SQL="select * from customer c,Vehicle v,appointment a where c.Cust_ID=a.Cust_ID ;";
        ResultSet result=CrudUtil.executeQuery(SQL);
        if (result.next()) {
            return new SaveNewCustomer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6),
                    result.getInt(7),
                    result.getString(8),
                    result.getString(9),
                    result.getString(10),
                    result.getString(11),
                    result.getString(12),
                    result.getString(13),
                    result.getString(14),
                    result.getString(15),
                    result.getString(16)
            );
        }
        return null;
    }
    
}
