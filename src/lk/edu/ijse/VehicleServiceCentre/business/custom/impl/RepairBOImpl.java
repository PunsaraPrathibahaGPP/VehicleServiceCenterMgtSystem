/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.business.custom.RepairBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.RepairDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Repair;
import lk.edu.ijse.VehicleServiceCentre.model.RepairDTO;

/**
 *
 * @author ACER
 */
public class RepairBOImpl implements RepairBO{
    private RepairDAO repairDAO;

    public RepairBOImpl() {
        try {
            repairDAO=(RepairDAO)DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.REPAIR);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RepairBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean addRepair(RepairDTO repairDTO) throws Exception {
        Repair repair=new Repair(repairDTO.getR_ID(),repairDTO.getTechni_ID(),repairDTO.getTechni_Name(),repairDTO.getR_Type(),repairDTO.getR_Fee(),repairDTO.getOther_Fees(),repairDTO.getTotal_Fees());
        return repairDAO.save(repair);
    }
    
}
