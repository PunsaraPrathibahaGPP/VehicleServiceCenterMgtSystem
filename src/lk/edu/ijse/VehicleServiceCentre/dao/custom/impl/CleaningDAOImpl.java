/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CleaningDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Cleaning;

/**
 *
 * @author ACER
 */
public class CleaningDAOImpl implements CleaningDAO{

    @Override
    public boolean save(Cleaning cleaning) throws Exception {
        return CrudUtil.executeUpdate("Insert Into Cleaning Values(?,?,?,?,?)",cleaning.getCl_ID_PK(),cleaning.getTechni_ID(),cleaning.getTechni_Name(),cleaning.getCl_Types(),cleaning.getCl_Fee());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cleaning entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cleaning search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cleaning> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
