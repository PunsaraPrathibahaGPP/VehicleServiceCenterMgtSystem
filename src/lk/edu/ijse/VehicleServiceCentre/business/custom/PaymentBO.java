/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.PaymentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDTO;

/**
 *
 * @author ACER
 */
public interface PaymentBO extends SuperBO{
    
    public boolean makePayment(PaymentDTO appDTO) throws Exception;

    public CustomerDTO searchPayment(String id) throws Exception;

    public boolean deletePayment(String id) throws Exception;
    
    public boolean updatePayment(PaymentDTO appDTO)throws Exception;
    
    public ArrayList<PaymentDTO> getAllPayment()throws Exception;
}
