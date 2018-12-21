/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.model.PaymentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDetailDTO;

/**
 *
 * @author ACER
 */
public interface MakePaymentBO extends SuperBO{
    public boolean addNewPayment(PaymentDTO paymentDTO,ServiceDTO serviceDTO)throws Exception;
    public void getLubricantPopUpDetails(LubricantDTO lubricantDTo)throws Exception;
    public void getLubricantPrice(double totalLubricantPrice)throws Exception;
    public void getRepairPrice(double totalRepairPrice)throws Exception;
    public void getCleaningPrice(double totalCleaningPrice)throws Exception;
}
