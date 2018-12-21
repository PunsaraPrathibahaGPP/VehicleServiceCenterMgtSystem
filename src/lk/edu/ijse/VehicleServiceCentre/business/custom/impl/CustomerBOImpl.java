/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom.impl;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.dao.DAOFactory;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerAllDTO;

import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;

/**
 *
 * @author ACER
 */
public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO;

    public CustomerBOImpl() throws Exception {
        customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOFactTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        Customer entity = new Customer(customer.getCust_Id(), customer.getF_Name(), customer.getL_Name(), customer.getNIC_NO(), customer.getCust_Address(), customer.getCust_Salary(), customer.getCust_Tel());
        return customerDAO.save(entity);
    }

    @Override
    public boolean deleteCustomer(String nicNO) throws Exception {
        return customerDAO.delete(nicNO);
    }

    @Override
    public CustomerDTO searchCustomer(String nicNo) throws Exception {
        Customer customer = customerDAO.search(nicNo);
        return new CustomerDTO(customer.getCust_Id_PK(), customer.getF_Name(), customer.getL_Name(), customer.getNIC_NO(), customer.getCust_Address(), customer.getCust_Salary(), customer.getCust_Tel());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        ArrayList<Customer> getAll = customerDAO.getAll();
        ArrayList<CustomerDTO> custDTO = new ArrayList<>();
        for (Customer cust : getAll) {
            custDTO.add(new CustomerDTO(cust.getCust_Id_PK(), cust.getF_Name(), cust.getL_Name(), cust.getCust_Address(), cust.getNIC_NO(), cust.getCust_Salary(), cust.getCust_Tel()));
        }
        return custDTO;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customerDAO = new Customer(customerDTO.getCust_Id(), customerDTO.getF_Name(), customerDTO.getL_Name(), customerDTO.getNIC_NO(), customerDTO.getCust_Address(), customerDTO.getCust_Salary(), customerDTO.getCust_Tel());
        return this.customerDAO.update(customerDAO);
    }

    @Override
    public ArrayList<CustomerAllDTO> getAllCustomerField() throws Exception {
        ArrayList<Customer> getAll = customerDAO.getAll();
        ArrayList<CustomerAllDTO> custDTO = new ArrayList<>();
        for (Customer cust : getAll) {
            custDTO.add(new CustomerAllDTO(cust.getCust_Id_PK(), cust.getF_Name(), cust.getL_Name(), cust.getCust_Address(), cust.getNIC_NO(), "" + cust.getCust_Salary(), "" + cust.getCust_Tel()));
        }
        return custDTO;
    }

}
