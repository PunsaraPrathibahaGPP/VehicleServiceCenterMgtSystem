/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.PaymentDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.entity.Payment;

/**
 *
 * @author ACER
 */
public class PaymentDAOImpl implements PaymentDAO{

    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.executeUpdate("Insert into Payment values(?,?,?,?)",payment.getPay_ID_PK(),payment.getPay_Date(),payment.getPay_Time(),payment.getTot_Ammount());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Payment search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        ArrayList<Payment> payment=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("Select * from Payment");
        while(rst.next()){
            payment.add(new Payment(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return payment;
    }
    
}
