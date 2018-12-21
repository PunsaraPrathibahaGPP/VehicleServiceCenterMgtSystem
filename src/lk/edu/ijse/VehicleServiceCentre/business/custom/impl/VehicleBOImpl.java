/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public class VehicleBOImpl implements VehicleBO{
    private static VehicleDAO vehicleDAO;

    public VehicleBOImpl()throws Exception{
        vehicleDAO=(VehicleDAO)DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.VEHICLE);
    }
    
    @Override
    public boolean addVehicle(VehicleDTO vehicle) throws Exception {
        return vehicleDAO.save(new Vehicle(vehicle.getVeh_ID(),vehicle.getVeh_No(),vehicle.getVeh_Type(),vehicle.getVeh_Brand()));
    }

    @Override
    public VehicleDTO searchVehicle(String id) throws Exception {
        Vehicle vehicle=vehicleDAO.search(id);
        return new VehicleDTO(vehicle.getVeh_ID_PK(),vehicle.getVeh_No(),vehicle.getVeh_Type(),vehicle.getVeh_Brand());
    }

    @Override
    public boolean deleteVehicle(String Veh_No) throws Exception {
        return vehicleDAO.delete(Veh_No);
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehile() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicleDTO) throws Exception {
        Vehicle vehicle=new Vehicle(vehicleDTO.getVeh_ID(),vehicleDTO.getVeh_No(),vehicleDTO.getVeh_Type(),vehicleDTO.getVeh_Brand());
        return vehicleDAO.update(vehicle);
       
    }

}
