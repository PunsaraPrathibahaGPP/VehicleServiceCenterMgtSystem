/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.ServiceDetailDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.ServiceDetail;

/**
 *
 * @author ACER
 */
public class ServiceDetailDAOImpl implements ServiceDetailDAO{

    @Override
    public boolean save(ServiceDetail entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into ServiceDetail values(?,?)",entity.getVeh_ID_FK(),entity.getSID_PK());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ServiceDetail entity) throws Exception {
        return CrudUtil.executeUpdate("Update ServiceDetail set Veh_ID = ? where SID = ?",entity.getVeh_ID_FK(),entity.getSID_PK());
    }

    @Override
    public ServiceDetail search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ServiceDetail> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
