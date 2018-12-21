/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.business.custom.MakePaymentBO;
import lk.edu.ijse.VehicleServiceCentre.controller.MakePaymentController;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.LubricantDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.PaymentDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.ServiceDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.ServiceDetailDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.VehicleDAO;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.entity.Cleaning;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Lubricant;
import lk.edu.ijse.VehicleServiceCentre.entity.Payment;
import lk.edu.ijse.VehicleServiceCentre.entity.Service;
import lk.edu.ijse.VehicleServiceCentre.entity.ServiceDetail;
import lk.edu.ijse.VehicleServiceCentre.entity.Vehicle;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.model.PaymentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDetailDTO;

/**
 *
 * @author ACER
 */
public class MakePaymentBOImpl implements MakePaymentBO{
    private PaymentDAO paymentDAO;
    private ServiceDAO serviceDAO;
    private static LubricantDAO lubricantDAO;
    private ServiceDetailDAO serviceDetailDAO;
    private VehicleDAO vehicleDAO;
    private LubricantDTO getlubricantDTO;
    
    public MakePaymentBOImpl() throws Exception {
        paymentDAO=(PaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.PAYMENT);
        serviceDAO=(ServiceDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.SERVICE);
        serviceDetailDAO=(ServiceDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.SERVICEDETAIL);
    }

    @Override
    public boolean addNewPayment(PaymentDTO paymentDTO,ServiceDTO serviceDTO)throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Payment payment = new Payment(paymentDTO.getPay_ID(), paymentDTO.getPay_Date(), paymentDTO.getPay_Time(), paymentDTO.getTot_Ammountl());
        boolean isSavedPayment = paymentDAO.save(payment);
        if(isSavedPayment){
            Service service = new Service(serviceDTO.getPay_ID(), serviceDTO.getSID(), serviceDTO.getTime_Duration(), serviceDTO.getS_Fee());
            boolean isSavedService = serviceDAO.save(service);
            if(isSavedService){
                 connection.commit();
            }         
        }
        return true;
    }
    
    private void setLubricantDetails() throws  Exception {
        Connection connection=DBConnection.getInstance().getConnection();
                Lubricant lubricant=new Lubricant(getlubricantDTO.getLub_ID(),getlubricantDTO.getTechni_ID(),getlubricantDTO.getTechni_Name(),
                        getlubricantDTO.getLub_Type(),getlubricantDTO.getService_Fee(),getlubricantDTO.getLubricantFee(),getlubricantDTO.getTotal_Fee());
                boolean  isSavedLubricantService= lubricantDAO.save(lubricant);
                if(isSavedLubricantService){
                    connection.commit();
                }
    }
    
    @Override
    public void getLubricantPopUpDetails(LubricantDTO lubricantDTo) {
        try {
            this.getlubricantDTO=lubricantDTo;
            setLubricantDetails();
  
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    @Override
    public void getLubricantPrice(double totalLubricantPrice) throws Exception {
        MakePaymentController makePayment=new MakePaymentController();
        makePayment.getLubricantPrice(totalLubricantPrice);
    }
 
    @Override
    public void getRepairPrice(double totalRepairPrice) throws Exception {
        MakePaymentController makePayment=new MakePaymentController();
        makePayment.getRepairPrice(totalRepairPrice);
    }
    @Override
    public void getCleaningPrice(double totalCleaningPrice) {
        MakePaymentController makePayment=new MakePaymentController();
        makePayment.getCleaningPrice(totalCleaningPrice);
    }
}
