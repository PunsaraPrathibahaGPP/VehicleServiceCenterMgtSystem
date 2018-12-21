/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.SuperDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.SaveNewCustomer;
import lk.edu.ijse.VehicleServiceCentre.model.SaveNewCustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 *
 * @author ACER
 */
public interface QueryDAO extends SuperDAO{
    public SaveNewCustomer getAllAppointment()throws Exception;
}
