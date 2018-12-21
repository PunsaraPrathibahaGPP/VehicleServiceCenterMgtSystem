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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddAppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MakeAnAppointmentController1 implements Initializable {

    @FXML
    private Label lblSave;
    @FXML
    private Label lblDiscard;
    @FXML
    private DatePicker datepickerInquieryDate;
    @FXML
    private DatePicker datepickerAppointedDate;
    @FXML
    private JFXTextField txtInquieryTime;
    @FXML
    private JFXTextField txtAppointmentNo;
    @FXML
    private JFXTextField txtAppointedTime;
    @FXML
    private JFXTextField txtCustomerId;
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
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXComboBox<String> cmbvehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXButton btnVehicleSearch;
    private CustomerDTO customerDTO;
    private VehicleDTO vehicleDTO;

    private CustomerBO customerBo;
    private VehicleBO vehicleBo;
    private AppointmentBO appointmentBO;
    private AddAppointmentBO addAppointmentBO;
    private CustomerDTO addCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadAppointmentBO();
            loadInquieryDate();
            loadInquieryTime();
            loadVehicleType();
            loadVehicleBrand();
            loadCustomerBO();
            loadVehicleBO();
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 
    private void loadAppointmentBO() throws Exception {
        appointmentBO = (AppointmentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.APPOINTMENT);
    }

    private void loadInquieryDate() {
        datepickerInquieryDate.setValue(LocalDate.now());
    }

    private void loadInquieryTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM:ss a");
        String nowDate = dateFormat.format(date);
        txtInquieryTime.setText(nowDate);
    }

    private void loadCustomerBO() throws Exception {
        customerBo = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    private void loadVehicleBO() throws Exception {
        vehicleBo = (VehicleBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.VEHICLE);
    }

    private void loadVehicleType() {
        String[] vehicleType = {"Sedan", "Coupe", "Wagon", "Van/MPV", "SUV", "Cabs", "CrossOver", "Convertiable", "Sports Car"};
        cmbvehicleType.getItems().clear();
        for (String type : vehicleType) {
            cmbvehicleType.getItems().add(type);

        }
    }

    private void loadVehicleBrand() {
        String[] vehicleBrand = {"BENZ", "BMW", "AUDI", "HONDA", "LEXUS", "NISSAN", "TATA", "TOYOTA", "SUZUKI", "RENAULT", "MAZDA", "MITSUBISHI", "LANDROWER", "CHEVROLET", "FORD", "Hyundai", "FIAT", "GEELY", "OTHER"};
        cmbVehicleBrand.getItems().clear();
        for (String brand : vehicleBrand) {
            cmbVehicleBrand.getItems().add(brand);
        }
    }

    @FXML
    private void lblSaveClicked(MouseEvent event) {
    }

    @FXML
    private void lblDiscardClicked(MouseEvent event) {
    }

    @FXML
    private void btnCustomerSearchClicked(MouseEvent event) {
    }

    @FXML
    private void btnVehicleSearch(MouseEvent event) {
    }
    
}
