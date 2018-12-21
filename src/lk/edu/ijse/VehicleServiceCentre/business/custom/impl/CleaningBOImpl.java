/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CleaningBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CleaningDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Cleaning;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDTO;

/**
 *
 * @author ACER
 */
public class CleaningBOImpl implements CleaningBO{

    private CleaningDAO cleaningDAO;

    public CleaningBOImpl() {
        try {
            cleaningDAO=(CleaningDAO)DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CLEANING);
        } catch (Exception ex) {
            Logger.getLogger(CleaningBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public boolean addCleaning(CleaningDTO cleaningDTO) throws Exception {
        Cleaning cleaning=new Cleaning(cleaningDTO.getCl_ID(),cleaningDTO.getTechni_ID(),cleaningDTO.getTechni_Name(),cleaningDTO.getCl_Types(),cleaningDTO.getCl_Fee());
        return cleaningDAO.save(cleaning);
    }
    
}
