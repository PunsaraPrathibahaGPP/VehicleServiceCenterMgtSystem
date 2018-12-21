/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.LubricantDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Lubricant;

/**
 *
 * @author ACER
 */
public class LubricantDAOImpl implements LubricantDAO{

    @Override
    public boolean save(Lubricant lubricant) throws Exception {
        return CrudUtil.executeUpdate("Insert into Lubricant Values(?,?,?,?,?,?,?)",lubricant.getLub_ID_PK(),lubricant.getTechni_ID(),lubricant.getTechni_Name(),lubricant.getLub_Type(),lubricant.getService_Fee(),lubricant.getLubricantFee(),lubricant.getTotal_Fee());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Lubricant entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lubricant search(String lubricantId) throws Exception {
        ResultSet rst=CrudUtil.executeQuery("Select * from Lubricant Where Lub_ID = ? ", lubricantId);
        Lubricant lubricant=null;
        while(rst.next()){
            lubricant=new Lubricant(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getDouble(6),rst.getDouble(7));
        }
        return lubricant;
    }

    @Override
    public ArrayList<Lubricant> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
