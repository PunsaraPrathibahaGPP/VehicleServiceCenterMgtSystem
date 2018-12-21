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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.ServiceDetailBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDetailDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class SearchCustomerController implements Initializable {

    @FXML
    private JFXTextField txtNICNO;
    @FXML
    private JFXTextField txtF_Name;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtL_Name;
    private JFXTextField txtVehicleNo;
    private JFXTextField txtVehicleID;
    private JFXTextField txtVehicleType;
    private JFXTextField txtVehicleBrand;

    private CustomerBO customerBo;
    private VehicleBO vehicleBo;
    @FXML
    private JFXTextField txtCustomerID;
    private JFXComboBox<String> cmbVehicleType;
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private TableView<CustomerDTO> customerTableView;
    @FXML
    private JFXButton btnMakeanAppointment;

    private ServiceDetailBO serviceDetailBO;
    @FXML
    private JFXButton btnUpdateCustomer;
    @FXML
    private JFXButton btnDeleteCustomer;
    @FXML
    private JFXButton btnCustomerSearchh;
    @FXML
    private JFXButton btnClearAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCustomerBO();
            loadCustomerDetails();
            loadVehicleBO();
        } catch (Exception ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadVehicleType() {
        String[] vehicleType = {"Sedan", "Coupe", "Wagon", "Van/MPV", "SUV", "Cabs", "CrossOver", "Convertiable", "Sports Car"};
        cmbVehicleType.getItems().clear();
        for (String type : vehicleType) {
            cmbVehicleType.getItems().add(type);

        }
    }

    private void loadVehicleBrand() {
        String[] vehicleBrand = {"BENZ", "BMW", "AUDI", "HONDA", "LEXUS", "NISSAN", "TATA", "TOYOTA", "SUZUKI", "RENAULT", "MAZDA", "MITSUBISHI", "LANDROWER", "CHEVROLET", "FORD", "Hyundai", "FIAT", "GEELY", "OTHER"};
        cmbVehicleBrand.getItems().clear();
        for (String brand : vehicleBrand) {
            cmbVehicleBrand.getItems().add(brand);
        }
    }

    private void loadCustomerBO() {
        try {
            customerBo = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
        } catch (Exception ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadVehicleBO() {
        try {
            vehicleBo = (VehicleBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.VEHICLE);
        } catch (Exception ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerDetails() {
        try {
            customerTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
            customerTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
            customerTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
            customerTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC_NO"));
            customerTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Cust_Address"));
            customerTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Cust_Salary"));
            customerTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Cust_Tel"));
            ArrayList<CustomerDTO> customerDTO = customerBo.getAllCustomer();
            customerTableView.setItems(FXCollections.observableArrayList(customerDTO));
        } catch (Exception ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnVehicleSearchOnAction(ActionEvent event) throws Exception {
        VehicleDTO vehicleDTO = vehicleBo.searchVehicle(txtVehicleNo.getText());
        if (vehicleDTO != null) {
            txtVehicleID.setText(vehicleDTO.getVeh_ID());
            cmbVehicleType.getSelectionModel().select(vehicleDTO.getVeh_Type());
            cmbVehicleBrand.getSelectionModel().select(vehicleDTO.getVeh_Brand());

        }
    }

    @FXML
    private void customerTableViewClicked(MouseEvent event) {
        CustomerDTO customerDTO = customerTableView.getSelectionModel().getSelectedItem();
        if (customerDTO != null) {
            txtCustomerID.setText(customerDTO.getCust_Id());
            txtF_Name.setText(customerDTO.getF_Name());
            txtL_Name.setText(customerDTO.getL_Name());
            txtAddress.setText(customerDTO.getCust_Address());
            txtNICNO.setText(customerDTO.getNIC_NO());
            txtSalary.setText("" + customerDTO.getCust_Salary());
            txtMobileNo.setText("" + customerDTO.getCust_Tel());
        }
    }

    @FXML
    private void btnMakeanAppointmentClicked(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakeAnAppointment.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();

    }

    @FXML
    private void btnUpdateCustomerClicked(MouseEvent event) throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNICNO.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
        boolean isUpdated = customerBo.updateCustomer(customerDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated  Succefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Updated  Unsuccefully", ButtonType.OK);
            alert.showAndWait();
        }
        loadCustomerDetails();
    }

    @FXML
    private void btnDeleteCustomerClicked(MouseEvent event) throws Exception {
        boolean isDeleted = customerBo.deleteCustomer(txtNICNO.getText());
        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted  Sucessfully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Deleted  Unsucessfully", ButtonType.OK);
            alert.showAndWait();
        }
        loadCustomerDetails();
    }

    @FXML
    private void btnCustomerSearchhClicked(ActionEvent event) throws Exception {
        String nicNo = txtNICNO.getText();
        CustomerDTO customerDTO = customerBo.searchCustomer(nicNo);
        if (customerDTO != null) {
            txtCustomerID.setText(customerDTO.getCust_Id());
            txtF_Name.setText(customerDTO.getF_Name());
            txtL_Name.setText(customerDTO.getL_Name());
            txtAddress.setText(customerDTO.getCust_Address());
            txtNICNO.setText(customerDTO.getNIC_NO());
            txtSalary.setText("" + customerDTO.getCust_Salary());
            txtMobileNo.setText("" + customerDTO.getCust_Tel());
        }
    }

    private void clearAll() throws Exception {
        txtCustomerID.clear();
        txtF_Name.clear();
        txtL_Name.clear();
        txtAddress.clear();
        txtNICNO.clear();
        txtSalary.clear();
        txtMobileNo.clear();
    }

    @FXML
    private void btnClearAllClicked(MouseEvent event) {
        try {
            clearAll();
        } catch (Exception ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
