/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;

/**
 *
 * @author ACER
 */
public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public boolean save(Customer cust) throws Exception {
        return CrudUtil.executeUpdate("Insert into customer values(?,?,?,?,?,?,?)", cust.getCust_Id_PK(),cust.getF_Name(),cust.getL_Name(),cust.getNIC_NO(),cust.getCust_Address(),cust.getCust_Salary(),cust.getCust_Tel());
    }

    @Override
    public boolean delete(String nicNO) throws Exception {
        return CrudUtil.executeUpdate("Delete from Customer where Nic_No = ? ", nicNO);
    }

    @Override
    public boolean update(Customer  customer) throws Exception {
        return CrudUtil.executeUpdate("Update Customer Set Cust_ID = ?, F_Name= ?, L_Name= ?, Cust_Address= ?, Cust_Salary= ?, Cust_Tel= ? where Nic_No= ? ", customer.getCust_Id_PK(),customer.getF_Name(),customer.getL_Name(),customer.getCust_Address(),customer.getCust_Salary(),customer.getCust_Tel(),customer.getNIC_NO());
    }

    @Override
    public Customer search(String nicNo) throws Exception {
        ResultSet rst=CrudUtil.executeQuery("Select * from Customer where Nic_No = ? ", nicNo);
        Customer customer = null;
        if(rst.next()){
            customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDouble(6), rst.getInt(7));
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
       ArrayList<Customer> customer=new ArrayList<>();
       ResultSet rst=CrudUtil.executeQuery("Select * from Customer");
       while(rst.next()){
            customer.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDouble(6), rst.getInt(7)));
       }
       return customer;
    }

}
