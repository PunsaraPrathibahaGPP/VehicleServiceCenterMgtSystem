/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom;

import lk.edu.ijse.VehicleServiceCentre.dao.CrudDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;

/**
 *
 * @author ACER
 */
public interface VehicleDAO extends CrudDAO<Vehicle , String>{
    public boolean updateVehicleInServiceDetail(Vehicle entity)throws Exception;
   
}
