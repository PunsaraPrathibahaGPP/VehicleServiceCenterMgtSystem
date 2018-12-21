/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom;

import lk.edu.ijse.VehicleServiceCentre.dao.CrudDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.SuperDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Appointment;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;

/**
 *
 * @author ACER
 */
public interface AppointmentDAO extends CrudDAO<Appointment, String>{
    public Appointment searchAppointmentDate(String appointmentDate) throws Exception;
    
    public Appointment searchCustomerAppointment(String customerID) throws Exception;
}
