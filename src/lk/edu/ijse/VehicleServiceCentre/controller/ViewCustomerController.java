/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerAllDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.other.CustomMethod;

/**
 * FXML Controller class
 *
 * @author Elijah-PC
 */
public class ViewCustomerController implements Initializable {

    @FXML
    private TableView<CustomerAllDTO> tblCustomerView;
    private CustomerBO customerBo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadCustomerDetails();
        } catch (Exception ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ViewCustomerController() throws Exception {
        customerBo = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    private void loadCustomerDetails() throws Exception {
        tblCustomerView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
        tblCustomerView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
        tblCustomerView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
        tblCustomerView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC_NO"));
        tblCustomerView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Cust_Address"));
        tblCustomerView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("cust_Salary"));
        tblCustomerView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("cust_Tel"));
        ArrayList<CustomerAllDTO> customerDTO = customerBo.getAllCustomerField();
        tblCustomerView.setItems(FXCollections.observableArrayList(customerDTO));
    }

    @FXML
    private void tblMouseOnClick(MouseEvent event) {
        CustomMethod.value = tblCustomerView.getSelectionModel().getSelectedItem().getCust_Id();
        CustomMethod.value2 = tblCustomerView.getSelectionModel().getSelectedItem().getF_Name();
        CustomMethod.value3 = tblCustomerView.getSelectionModel().getSelectedItem().getL_Name();
        CustomMethod.value5 = tblCustomerView.getSelectionModel().getSelectedItem().getNIC_NO();
        CustomMethod.value4 = tblCustomerView.getSelectionModel().getSelectedItem().getCust_Address();
        CustomMethod.value6 = tblCustomerView.getSelectionModel().getSelectedItem().getCust_Salary();
        CustomMethod.value7 = tblCustomerView.getSelectionModel().getSelectedItem().getCust_Tel();
    }

}
