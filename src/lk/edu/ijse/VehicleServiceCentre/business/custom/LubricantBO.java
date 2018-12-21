/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;

/**
 *
 * @author ACER
 */
public interface LubricantBO extends SuperBO{
    
    public boolean addLubricant(LubricantDTO lubricantDTO)throws Exception;
    
    public LubricantDTO searchLubricant(String lubricantID)throws Exception;
}
