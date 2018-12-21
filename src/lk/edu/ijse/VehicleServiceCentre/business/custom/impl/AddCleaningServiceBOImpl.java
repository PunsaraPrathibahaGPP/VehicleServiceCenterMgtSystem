/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddCleaningServiceBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CleaningDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.LubricantDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.entity.Cleaning;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Lubricant;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public class AddCleaningServiceBOImpl implements AddCleaningServiceBO{
    private CustomerDAO customerDAO;
    private VehicleDAO vehicleDAO;
    private CleaningDAO cleaningDAO;

 
    public AddCleaningServiceBOImpl() throws ClassNotFoundException, SQLException {
            customerDAO=(CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CUSTOMER);
            vehicleDAO=(VehicleDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.VEHICLE);
            cleaningDAO=(CleaningDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CLEANING);

    }
    
    @Override
    public boolean addCleaningService(CleaningDTO cleaningDTO, CustomerDTO customerDTO, VehicleDTO vehicleDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Cleaning cleaning=new Cleaning(cleaningDTO.getCl_ID(),cleaningDTO.getTechni_ID() ,cleaningDTO.getTechni_Name(),cleaningDTO.getCl_Types(),cleaningDTO.getCl_Fee());
        boolean isSavedCleaning=cleaningDAO.save(cleaning);
        if(isSavedCleaning){
            Customer customer = new Customer(customerDTO.getCust_Id(),customerDTO.getF_Name(),customerDTO.getL_Name(),customerDTO.getNIC_NO(),customerDTO.getCust_Address(),customerDTO.getCust_Salary(),customerDTO.getCust_Tel());
            boolean isSavedCustomer = customerDAO.save(customer);
            if (isSavedCustomer) {
                Vehicle vehicle=new Vehicle(vehicleDTO.getVeh_ID(),vehicleDTO.getVeh_No(),vehicleDTO.getVeh_Type(),vehicleDTO.getVeh_Brand());
                boolean isSavedVehicle=vehicleDAO.save(vehicle);
                if(isSavedVehicle){
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
