/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.SQLException;
import lk.edu.ijse.VehicleServiceCentre.business.custom.LubricantBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.LubricantDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.QueryDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Lubricant;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;

/**
 *
 * @author ACER
 */
public class LubricantBOImpl implements LubricantBO{
     private LubricantDAO lubricantDAO;

    public LubricantBOImpl() throws Exception{
        lubricantDAO=(LubricantDAO)DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.LUBRICANT);
    }
    
    
    @Override
    public boolean addLubricant(LubricantDTO lubricantDTO) throws Exception {
        Lubricant lubricant=new Lubricant(lubricantDTO.getLub_ID(),lubricantDTO.getTechni_ID(),lubricantDTO.getTechni_Name(),lubricantDTO.getLub_Type(),lubricantDTO.getLubricantFee(),lubricantDTO.getService_Fee(),lubricantDTO.getTotal_Fee());
        return lubricantDAO.save(lubricant);
    }

    @Override
    public LubricantDTO searchLubricant(String serviceID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
