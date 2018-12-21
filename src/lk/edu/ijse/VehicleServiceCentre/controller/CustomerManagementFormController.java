/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.other.CustomMethod;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class CustomerManagementFormController implements Initializable {

    @FXML
    private JFXTextField txtNICNO;
    @FXML
    private JFXTextField txtL_Name;
    @FXML
    private JFXTextField txtF_Name;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXButton btnCustomerSearch;
    @FXML
    private JFXButton btnDeleteCustomer;
    @FXML
    private JFXButton btnUpdateCustomer;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXComboBox<String> cmbvehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXButton btnAddCustomer;
    @FXML
    private JFXTextField txtCustomerID;

    private CustomerBO customerBO;
    private VehicleBO vehicleBo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCustomerBO();
            loadCustomerIds();
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerBO() throws Exception {
        customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    @FXML
    private void btnCustomerSearchClicked(MouseEvent event) {
        try {
            searchVehicle();
            searchCustomer();
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void searchCustomer() throws Exception{
        String nicNo = txtNICNO.getText();
        CustomerDTO customerDTO = customerBO.searchCustomer(nicNo);
        if (customerDTO != null) {
            txtCustomerID.setText(customerDTO.getCust_Id());
            txtF_Name.setText(customerDTO.getF_Name());
            txtL_Name.setText(customerDTO.getL_Name());
            txtAddress.setText(customerDTO.getCust_Address());
            txtNICNO.setText(customerDTO.getNIC_NO());
            txtSalary.setText(Double.toString(customerDTO.getCust_Salary()));
            txtMobileNo.setText(Double.toString(customerDTO.getCust_Tel()));
        }
    
    }

    private void searchVehicle() throws Exception{
        VehicleDTO vehicleDTO = vehicleBo.searchVehicle(txtVehicleNo.getText());
        if (vehicleDTO != null) {
            txtVehicleID.setText(vehicleDTO.getVeh_ID());
            cmbvehicleType.getSelectionModel().select(vehicleDTO.getVeh_Type());
            cmbVehicleBrand.getSelectionModel().select(vehicleDTO.getVeh_Brand());
        }
    }

    private void onCLick(MouseEvent event) {
        CustomMethod.value = txtF_Name.getText();
    }

    @FXML
    private void btnAddCustomerClicked(MouseEvent event) throws Exception {
        String customerID = txtCustomerID.getText();
        String f_Name = txtF_Name.getText();
        String l_Name = txtL_Name.getText();
        String nicNO = txtNICNO.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        int mobileNO = Integer.parseInt(txtMobileNo.getText());
        CustomerDTO customerDTO = new CustomerDTO(customerID, f_Name, l_Name, nicNO, address, salary, mobileNO);
        boolean isAdded = customerBO.addCustomer(customerDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cusomer Details Added Successfully ", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cusomer Details Added Unsuccessfully ", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void mouseOnAction(ActionEvent event) {
    }

    private void loadCustomerIds() {
        try {
            String newID;
            newID = IDGenerator.getNewID("customer", "CID", "C");

            txtCustomerID.setText(newID);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nicAction(ActionEvent event) {
        if(Validation.nicValidation(txtNICNO.getText())){
            txtF_Name.requestFocus();
        }else{
            Alert a=new Alert(Alert.AlertType.INFORMATION, "Invalid Nic..", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void custIdAction(ActionEvent event) {
    }

}
