/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;

/**
 *
 * @author ACER
 */
public class VehicleDAOImpl implements VehicleDAO{
    @Override
    public boolean save(Vehicle vehicle) throws Exception {
       return CrudUtil.executeUpdate("Insert Into Vehicle values(?,?,?,?)",vehicle.getVeh_ID_PK(),vehicle.getVeh_No(),vehicle.getVeh_Type(),vehicle.getVeh_Brand());
    }

    @Override
    public boolean delete(String veh_NO) throws Exception {
        return CrudUtil.executeUpdate("Delete from Vehicle where Veh_No = ?", veh_NO);
    }

    @Override
    public boolean update(Vehicle vehicle) throws Exception {
        return CrudUtil.executeUpdate("Update Vehicle set Veh_ID=? ,Veh_Type=?,Veh_Brand=? where Veh_No= ? ",vehicle.getVeh_ID_PK(),vehicle.getVeh_Type(),vehicle.getVeh_Brand(),vehicle.getVeh_No());
    }

    @Override
    public Vehicle search(String Veh_No) throws Exception {
        ResultSet rst=CrudUtil.executeQuery("Select * from Vehicle where Veh_No= ? ", Veh_No);
        Vehicle vehicle=null;
        if(rst.next()){
            vehicle = new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return vehicle;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateVehicleInServiceDetail(Vehicle vehicle) throws Exception {
        return CrudUtil.executeUpdate("Update Vehicle set Veh_ID=? where Veh_ID=? ", vehicle.getVeh_ID_PK(),vehicle.getVeh_ID_PK());
    }

}
