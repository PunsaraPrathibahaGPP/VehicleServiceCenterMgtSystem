/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.Connection;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddRepairServiceBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.RepairDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Repair;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.RepairDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public class AddRepairServiceBOImpl implements AddRepairServiceBO{
    private CustomerDAO customerDAO;
    private VehicleDAO vehicleDAO;
    private RepairDAO repairDAO;

    public AddRepairServiceBOImpl() throws Exception{
        customerDAO=(CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CUSTOMER);
        vehicleDAO=(VehicleDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.VEHICLE);
        repairDAO= (RepairDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.REPAIR);
    }
    
    
    
    @Override
    public boolean addRepairService(RepairDTO repairDTO, CustomerDTO customerDTO, VehicleDTO vehicleDTO) throws Exception {
        Connection connection=DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Customer customer=new Customer(customerDTO.getCust_Id(),customerDTO.getF_Name(),customerDTO.getL_Name(),customerDTO.getNIC_NO(),customerDTO.getCust_Address(),customerDTO.getCust_Salary(),customerDTO.getCust_Tel());
        boolean isCustomerSaved = customerDAO.save(customer);
        System.out.println(""+isCustomerSaved);
        if(isCustomerSaved){
            Vehicle vehicle=new Vehicle(vehicleDTO.getVeh_ID(),vehicleDTO.getVeh_No(), vehicleDTO.getVeh_Type(),vehicleDTO.getVeh_Brand());
            boolean isVehicleSaved=vehicleDAO.save(vehicle);
            if(isVehicleSaved){
                Repair repair=new Repair(repairDTO.getR_ID(),repairDTO.getTechni_ID(),repairDTO.getTechni_Name(),repairDTO.getR_Type(),repairDTO.getR_Fee(),repairDTO.getOther_Fees(),repairDTO.getTotal_Fees());
                boolean isRepairSaved=repairDAO.save(repair);
                if(isRepairSaved){
                    connection.commit(); 
                    return true;
                }
            }
            return true;
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        
    }
    
}
