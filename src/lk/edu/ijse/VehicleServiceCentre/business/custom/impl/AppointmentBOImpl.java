/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.AppointmentDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.AppointmentDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.entity.Appointment;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;

/**
 *
 * @author ACER
 */
public class AppointmentBOImpl implements AppointmentBO{
    AppointmentDAO appointmentDAO;

    public AppointmentBOImpl() {
        try {
            appointmentDAO=(AppointmentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.APPOINTMENT);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AppointmentBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO) throws Exception {
        Appointment appointment=new Appointment(appointmentDTO.getCust_ID(),appointmentDTO.getVeh_ID(),appointmentDTO.getApp_no(),appointmentDTO.getCurr_date(), appointmentDTO.getCurr_time(), appointmentDTO.getApp_date(), appointmentDTO.getApp_time());
        return appointmentDAO.save(appointment);
    }

    @Override
    public boolean deleteAppointment(String appointmentID) throws Exception {
        return appointmentDAO.delete(appointmentID);
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AppointmentDTO> getAllAppointment() throws Exception {
        ArrayList<Appointment> getAll = appointmentDAO.getAll();
        ArrayList<AppointmentDTO> appointmentDTO = new ArrayList<>();
        for (Appointment appointment : getAll) {
            appointmentDTO.add(new AppointmentDTO(appointment.getCust_ID_FK(), appointment.getVeh_ID_FK(),appointment.getApp_no_PK(),appointment.getCurr_date(),appointment.getCurr_time(),appointment.getApp_date(),appointment.getApp_time()));
        }
        return appointmentDTO;
    }

    @Override
    public AppointmentDTO searchAppointmentDate(String appointmentDate) throws Exception {
        Appointment appointment = appointmentDAO.search(appointmentDate);
        return new AppointmentDTO(appointment.getCust_ID_FK(),appointment.getVeh_ID_FK(),appointment.getApp_no_PK(),appointment.getCurr_date(),appointment.getCurr_time(),appointment.getApp_date(),appointment.getApp_time());
    }

    @Override
    public AppointmentDTO searchAppointment(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppointmentDTO searchCustomerAppointment(String customerID) throws Exception {
        Appointment searchCustomerAppointment = appointmentDAO.searchCustomerAppointment(customerID);
         return new AppointmentDTO(searchCustomerAppointment.getCust_ID_FK(),searchCustomerAppointment.getVeh_ID_FK() , searchCustomerAppointment.getApp_no_PK(), searchCustomerAppointment.getCurr_date(), searchCustomerAppointment.getCurr_time(),searchCustomerAppointment.getApp_date(), searchCustomerAppointment.getApp_time());
    }
}
