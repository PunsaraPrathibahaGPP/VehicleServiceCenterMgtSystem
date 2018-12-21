/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddLubricantServiceBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.LubricantDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.entity.Appointment;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Lubricant;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public class AddLubricantServiceBOImpl implements AddLubricantServiceBO{
    private CustomerDAO customerDAO;
    private VehicleDAO vehicleDAO;
    private LubricantDAO lubricantDAO;

    public AddLubricantServiceBOImpl() throws ClassNotFoundException, SQLException {
            customerDAO=(CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CUSTOMER);
            vehicleDAO=(VehicleDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.VEHICLE);
            lubricantDAO=(LubricantDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.LUBRICANT);

    }
    
    @Override
    public boolean addLubricantService(LubricantDTO lubricantDTO, CustomerDTO customerDTO, VehicleDTO vehicleDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Customer customer = new Customer(customerDTO.getCust_Id(),customerDTO.getF_Name(),customerDTO.getL_Name(),customerDTO.getNIC_NO(),customerDTO.getCust_Address(),customerDTO.getCust_Salary(),customerDTO.getCust_Tel());
        boolean isSavedCustomer = customerDAO.save(customer);
        if (isSavedCustomer) {
            Vehicle vehicle=new Vehicle(vehicleDTO.getVeh_ID(),vehicleDTO.getVeh_No(),vehicleDTO.getVeh_Type(),vehicleDTO.getVeh_Brand());
            boolean isSavedVehicle=vehicleDAO.save(vehicle);
            if(isSavedVehicle){
                Lubricant lubricant=new Lubricant(lubricantDTO.getLub_ID(),lubricantDTO.getTechni_ID() ,lubricantDTO.getTechni_Name(),lubricantDTO.getLub_Type(),lubricantDTO.getService_Fee(), lubricantDTO.getLubricantFee(),lubricantDTO.getTotal_Fee());
                boolean isSavedLubricant=lubricantDAO.save(lubricant);
                if(isSavedLubricant){
                    connection.commit();
                    return true;
                }
            }
            return true;
        }else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

    }

}
