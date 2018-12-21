/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.business.custom.ServiceDetailBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.ServiceDetailDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.ServiceDetail;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDetailDTO;

/**
 *
 * @author ACER
 */
public class ServiceDetailBOImpl implements ServiceDetailBO{
ServiceDetailDAO serviceDetailDAO;
    public ServiceDetailBOImpl() {
        try {
            serviceDetailDAO=(ServiceDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.SERVICEDETAIL);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServiceDetailBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public boolean addService(ServiceDetailDTO seviceDetailDTO) throws Exception {
        ServiceDetail servicedetail=new ServiceDetail(seviceDetailDTO.getVeh_ID(),seviceDetailDTO.getSID());
        return serviceDetailDAO.save(servicedetail);
    }

}
