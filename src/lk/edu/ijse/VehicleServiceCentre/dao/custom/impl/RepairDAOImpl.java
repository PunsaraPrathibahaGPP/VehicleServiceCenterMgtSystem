/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.RepairDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Repair;

/**
 *
 * @author ACER
 */
public class RepairDAOImpl implements RepairDAO{

    @Override
    public boolean save(Repair repair) throws Exception {
        return CrudUtil.executeUpdate("Insert into Repair Values(?,?,?,?,?,?,?)",repair.getR_ID_PK(),repair.getTechni_ID(),repair.getTechni_Name(),repair.getR_Type(),repair.getR_Fee(),repair.getOther_Fees(),repair.getTotal_Fees());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Repair entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Repair search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Repair> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
