/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.PaymentDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Payment;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.PaymentDTO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.PaymentBO;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerAllDTO;

/**
 *
 * @author ACER
 */
public class PaymentBOImpl implements PaymentBO{
    PaymentDAO paymentDAO;
    public PaymentBOImpl() {
        try {
            paymentDAO=(PaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.PAYMENT);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PaymentBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public boolean makePayment(PaymentDTO appDTO) throws Exception {
        Payment payment=new Payment(appDTO.getPay_ID(),appDTO.getPay_Date(),appDTO.getPay_Time(),appDTO.getTot_Ammountl());
        return paymentDAO.save(payment);
    }

    @Override
    public CustomerDTO searchPayment(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePayment(PaymentDTO appDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws Exception {
       ArrayList<Payment> getAll = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTO = new ArrayList<>();
        for (Payment payment : getAll) {
            paymentDTO.add(new PaymentDTO(payment.getPay_ID_PK(),payment.getPay_Date(),payment.getPay_Time(),payment.getTot_Ammount()));
        }
        return paymentDTO;
    }

 

  
}
