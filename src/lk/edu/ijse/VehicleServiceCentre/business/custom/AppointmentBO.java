/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;

/**
 *
 * @author ACER
 */
public interface AppointmentBO extends SuperBO{
    
    public boolean addAppointment(AppointmentDTO appDTO) throws Exception;

    public AppointmentDTO searchAppointment(String id) throws Exception;

    public boolean deleteAppointment(String id) throws Exception;
    
    public boolean updateAppointment(AppointmentDTO appDTO)throws Exception;
    
    public ArrayList<AppointmentDTO> getAllAppointment()throws Exception;
    
    public AppointmentDTO searchAppointmentDate(String appointmentDate) throws Exception;
    
    public AppointmentDTO searchCustomerAppointment(String customerID)throws Exception;
}
