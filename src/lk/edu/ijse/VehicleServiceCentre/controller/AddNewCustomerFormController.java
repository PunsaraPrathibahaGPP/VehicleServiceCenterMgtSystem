/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.controller.MakeAnAppointmentController;
import static net.sf.jasperreports.engine.util.DeepPrintElementCounter.count;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AddNewCustomerFormController implements Initializable {

    @FXML
    private AnchorPane pnl;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtCustAddress;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private JFXTextField txtCustId;
    @FXML
    private JFXTextField txtVehicleID;
    private JFXTextField txtVehicleType;
    private JFXTextField txtVehicleBrand;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private TableView<CustomerDTO> customerTableView;
    @FXML
    private JFXComboBox<String> cmbVehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;

    private static VehicleBO vehiclebo;
    private static CustomerBO customerBo;
    private static CustomBO customBo;
    private static boolean isCustomerAdded;
    private static boolean isVehicleAdded;
    private static boolean isCustomerDeleted;
    private static boolean isVehicledeleted;
    @FXML
    private JFXButton btnAddAllDetails;
    @FXML
    private JFXButton btnDeleteAllDetails;
    @FXML
    private JFXButton btnMakeanAppointment;
    @FXML
    private JFXButton btnClearAll;

    private static final AtomicInteger customerIdCount = new AtomicInteger(0);
    private static final AtomicInteger vehicleIdCount = new AtomicInteger(0);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCustomerID();
            loadVehicleID();
            loadCustomerBO();
            loadVehicleBO();
            loadCustomerDetail();
            loadVehicleType();
            loadVehicleBrand();
        } catch (Exception ex) {
            Logger.getLogger(AddNewCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerID() {
        int customerId = customerIdCount.incrementAndGet();
        txtCustId.setText("" + customerId);
    }

    private void loadVehicleID() {
        int vehicleId = vehicleIdCount.incrementAndGet();
        txtVehicleID.setText("" + vehicleId);
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

    private void loadCustomerBO() throws Exception {
        customerBo = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    private void loadVehicleBO() throws Exception {
        vehiclebo = (VehicleBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.VEHICLE);
    }

    @FXML
    private void onActionId(ActionEvent event) throws Exception {
    }

    private void loadCustomerDetail() throws Exception {
        customerTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
        customerTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
        customerTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
        customerTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC_NO"));
        customerTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Cust_Address"));
        customerTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Cust_Salary"));
        customerTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Cust_Tel"));
        ArrayList<CustomerDTO> customerDTO = customerBo.getAllCustomer();
        customerTableView.setItems(FXCollections.observableArrayList(customerDTO));
    }

    @FXML
    private void customerTableViewClicked(MouseEvent event) {

    }

    private void addCustomer() throws Exception {
        CustomerDTO custDTO = new CustomerDTO(txtCustId.getText(), txtFName.getText(), txtLName.getText(), txtNic.getText(), txtCustAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtTel.getText()));
        isCustomerAdded = customerBo.addCustomer(custDTO);
        loadCustomerDetail();
    }

    private void addVehicle() throws Exception {
        VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNo.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());
        isVehicleAdded = vehiclebo.addVehicle(vehicleDTO);
        if (isVehicleAdded && isCustomerAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Added Sucefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Added Not Sucefully", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void btnAddAllDetailsClicked(MouseEvent event) {
        try {
            addCustomer();
            addVehicle();
        } catch (Exception ex) {
            Logger.getLogger(AddNewCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteCustomer() throws Exception {
        isCustomerDeleted = customerBo.deleteCustomer(txtNic.getText());
        loadCustomerDetail();
    }

    private void deleteVehicle() throws Exception {
        isVehicledeleted = vehiclebo.deleteVehicle(txtVehicleNo.getText());
        if (isVehicledeleted && isCustomerDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Deleted Unsuccessfully", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void btnDeleteAllDetailsClicked(MouseEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES);
            alert.showAndWait();
            deleteCustomer();
            deleteVehicle();
        } catch (Exception ex) {
            Logger.getLogger(AddNewCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnMakeanAppointmentClicked(MouseEvent event) throws Exception {
        CustomerDTO custDTO = new CustomerDTO(txtCustId.getText(), txtFName.getText(), txtLName.getText(), txtNic.getText(), txtCustAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtTel.getText()));
        VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNo.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());

        MakeAnAppointmentController makeAppointment = new MakeAnAppointmentController();
        makeAppointment.getCustomerDetails(custDTO, vehicleDTO);

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakeAnAppointment.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
    }

    @FXML
    private void btnClearAllcLICKED(MouseEvent event) {
        try {
            clearAll();
        } catch (Exception e) {
            //Alert alert=new Alert(Alert.AlertType.ERROR,""+e, ButtonType.OK);
            //alert.showAndWait();
        }
    }

    private void clearAll() {
        txtCustId.clear();
        txtFName.clear();
        txtLName.clear();
        txtCustAddress.clear();
        txtNic.clear();
        txtSalary.clear();
        txtTel.clear();
        txtVehicleID.clear();
        txtVehicleNo.clear();
        cmbVehicleType.getSelectionModel().clearSelection();
        cmbVehicleBrand.getSelectionModel().clearSelection();
    }
}
