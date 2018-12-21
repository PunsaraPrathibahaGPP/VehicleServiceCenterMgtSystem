/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public interface AddLubricantServiceBO extends SuperBO{
    public boolean addLubricantService(LubricantDTO lubricantDTO,CustomerDTO customerDTO,VehicleDTO vehicleDTO) throws Exception;
}
