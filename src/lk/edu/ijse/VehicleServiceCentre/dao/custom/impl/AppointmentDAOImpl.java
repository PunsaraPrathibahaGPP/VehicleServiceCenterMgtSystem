/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.AppointmentDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Appointment;

/**
 *
 * @author ACER
 */
public class AppointmentDAOImpl implements AppointmentDAO{

    @Override
    public boolean save(Appointment entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into Appointment values(?,?,?,?,?,?,?)",entity.getCust_ID_FK(),entity.getVeh_ID_FK(),entity.getApp_no_PK(),entity.getCurr_date(), entity.getCurr_time(), entity.getApp_date(), entity.getApp_time());
    }

    @Override
    public boolean delete(String appointmentNO) throws Exception {
        return CrudUtil.executeUpdate("Delete from Appointment where App_no= ?",appointmentNO);
    }

    @Override
    public boolean update(Appointment entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Appointment search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Appointment> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Appointment");
        ArrayList<Appointment> appointmentList=new ArrayList<>();
        while(resultSet.next()){
            appointmentList.add(new Appointment(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        return appointmentList;
    }

    @Override
    public Appointment searchAppointmentDate(String appointmentDate) throws Exception {
        ResultSet result = CrudUtil.executeQuery("Select * from appointment where App_time = ?", appointmentDate);
        Appointment appointment=null;
        if(result.next()){
            appointment=new Appointment(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
        }
        return appointment;
    }

    @Override
    public Appointment searchCustomerAppointment(String customerID) throws Exception {
        ResultSet result = CrudUtil.executeQuery("Select * from appointment where Cust_ID = ?", customerID);
        Appointment appointment=null;
        if(result.next()){
            appointment=new Appointment(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
        }
        return appointment;
    }
    
}
