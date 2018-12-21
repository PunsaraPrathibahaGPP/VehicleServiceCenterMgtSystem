/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business.custom;

import java.util.ArrayList;
import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerAllDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;

/**
 *
 * @author ACER
 */
public interface CustomerBO extends SuperBO {

    public boolean addCustomer(CustomerDTO custDTO) throws Exception;

    public CustomerDTO searchCustomer(String id) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public ArrayList<CustomerAllDTO> getAllCustomerField() throws Exception;

}
