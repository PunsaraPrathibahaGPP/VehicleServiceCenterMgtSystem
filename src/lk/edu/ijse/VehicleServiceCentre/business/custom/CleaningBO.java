/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.CleaningDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDTO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;

/**
 *
 * @author ACER
 */
public interface CleaningBO extends SuperBO{
    public boolean addCleaning(CleaningDTO cleaningDTO)throws Exception;
}
