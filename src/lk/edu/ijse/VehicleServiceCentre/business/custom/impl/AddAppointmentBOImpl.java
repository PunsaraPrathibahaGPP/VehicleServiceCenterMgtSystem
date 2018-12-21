/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddAppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.AppointmentDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.entity.Appointment;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public class AddAppointmentBOImpl implements AddAppointmentBO {

    private CustomerDAO customerDAO;
    private AppointmentDAO appointmentDAO;
    private VehicleDAO vehicleDAO;

    public AddAppointmentBOImpl() throws ClassNotFoundException, SQLException {
        customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CUSTOMER);
        appointmentDAO = (AppointmentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.APPOINTMENT);
        vehicleDAO=(VehicleDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.VEHICLE);
    }

    @Override
    public boolean addAppointment(CustomerDTO customerDTO, AppointmentDTO appointmentDTO, VehicleDTO vehicleDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Customer customer = new Customer(customerDTO.getCust_Id(),customerDTO.getF_Name(),customerDTO.getL_Name(),customerDTO.getNIC_NO(),customerDTO.getCust_Address(),customerDTO.getCust_Salary(),customerDTO.getCust_Tel());
        boolean isSavedCustomer = customerDAO.save(customer);
        if (isSavedCustomer){
            Vehicle vehicle=new Vehicle(vehicleDTO.getVeh_ID(),vehicleDTO.getVeh_No(),vehicleDTO.getVeh_Type(),vehicleDTO.getVeh_Brand());
            boolean isSavedVehicle = vehicleDAO.save(vehicle);
            if(isSavedVehicle){
                Appointment appointment = new Appointment(appointmentDTO.getCust_ID(),appointmentDTO.getVeh_ID(),appointmentDTO.getApp_no(), appointmentDTO.getCurr_date(), appointmentDTO.getCurr_time(), appointmentDTO.getApp_date(), appointmentDTO.getApp_time());
                boolean isSavedAppointment = appointmentDAO.save(appointment);
                if(isSavedAppointment){
                    connection.commit();
                    return true;
                }
            }
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }



}
