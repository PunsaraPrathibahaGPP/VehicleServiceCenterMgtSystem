/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.RepairDTO;

/**
 *
 * @author ACER
 */
public interface RepairBO extends SuperBO{
    public boolean addRepair(RepairDTO repairDTO)throws Exception;
}
