/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public interface VehicleBO extends SuperBO{
    
    public boolean addVehicle(VehicleDTO itemDTO)throws Exception;
    
    public VehicleDTO searchVehicle(String id)throws Exception;
    
    public boolean deleteVehicle(String id)throws Exception;  
    
    public boolean updateVehicle(VehicleDTO vehicleDTO)throws Exception;
    
    public ArrayList<VehicleDTO> getAllVehile()throws Exception;
}
