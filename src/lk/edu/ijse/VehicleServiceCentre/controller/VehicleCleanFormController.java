/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddCleaningServiceBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CleaningBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AddCleaningServiceBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AddLubricantServiceBOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class VehicleCleanFormController implements Initializable {

    @FXML
    private JFXTextField txtF_Name;
    @FXML
    private JFXTextField txtNicNo;
    @FXML
    private JFXTextField txtL_Name;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField txtCust_ID;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXComboBox<String> cmbCleaningType;
    @FXML
    private JFXComboBox<String> cmbVehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXTextField txtVehicleNO;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXTextField txtCleaningID;
    @FXML
    private JFXComboBox<String> cmbTechnicianID;
    @FXML
    private JFXComboBox<String> cmbTechnicianName;
    @FXML
    private JFXTextField txtServiceFee;
    @FXML
    private JFXButton btnSearchCustomer;
    @FXML
    private JFXButton btnSaveAllDetails;
    @FXML
    private JFXButton btnMakePayment;
    
    private AddCleaningServiceBO addCleaningServiceBO;
    private static CustomerBO customerBO;
    private CleaningBO cleaningBO;
    @FXML
    private JFXButton btnClearAll;
    private boolean btnSearchClicked;
    private VehicleBO vehicleBO;
    private static String nicNo;
    private static String enteredNicNO;
    private static String enteredVehicleNo;
    private static String vehicleNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            txtNicNo.requestFocus();
            loadCustomerID();
            loadVehicleID();
            loadCleaningID();
            loadCleaningBO();
            loadVehicleBO();
            loadcustomerBO();
            loadVehicleType();
            loadVehicleBrand();
            loadTechnicianID();
            loadTechnicianName();
            loadCleanType();
        } catch (Exception ex) {
            Logger.getLogger(VehicleCleanFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadVehicleBO() throws Exception{
        vehicleBO=(VehicleBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.VEHICLE);
    }
    private void loadVehicleID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("vehicle", "Veh_ID", "V");
            txtVehicleID.setText(newID);
            txtVehicleID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadCleaningID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("Cleaning", "Cl_ID", "Cl");
            txtCleaningID.setText(newID);
            txtCleaningID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(LubricantDetailsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void loadCustomerID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("customer", "Cust_ID", "C");
            txtCust_ID.setText(newID);
            txtCust_ID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCleaningBO() throws Exception {
        cleaningBO = (CleaningBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CLEANING);
    }

    private void loadTechnicianID() {
        String[] technicianIDs = {"T001", "T002", "T003", "T004", "T005", "T006", "T007", "T008", "T009", "T0010"};
        cmbTechnicianID.getItems().clear();
        for (String technicianID : technicianIDs) {
            cmbTechnicianID.getItems().add(technicianID);
        }
    }

    private void loadTechnicianName() {
        String[] technicianNames = {"Saman", "Kalum", "Priyantha", "Raveen", "Dasun", "Janith", "Lakmal", "Wipula", "Indika", "Pawan"};
        cmbTechnicianName.getItems().clear();
        for (String technicianName : technicianNames) {
            cmbTechnicianName.getItems().add(technicianName);
        }
    }

    private void loadCleanType() {
        String[] cleanTypes = {"InteriorCleaning", "FullBodyCleaning", "Other"};
        cmbCleaningType.getItems().clear();
        for (String cleantype : cleanTypes) {
            cmbCleaningType.getItems().add(cleantype);
        }
    }

    private void loadVehicleType() {
        String[] vehicleType = {"Sedan", "Coupe", "Wagon", "Van/MPV", "SUV", "Cabs", "CrossOver", "Convertiable", "Sports Car"};
        cmbVehicleType.getItems().clear();
        for (String type : vehicleType) {
            cmbVehicleType.getItems().add(type);
        }
    }

    private void loadVehicleBrand() throws FileNotFoundException {
        String[] vehicleBrand = {"BENZ", "BMW", "AUDI", "HONDA", "LEXUS", "NISSAN", "TATA", "TOYOTA", "SUZUKI", "RENAULT", "MAZDA", "MITSUBISHI", "LANDROWER", "CHEVROLET", "FORD", "Hyundai", "FIAT", "GEELY", "OTHER"};
        cmbVehicleBrand.getItems().clear();
        for (String brand : vehicleBrand) {
            cmbVehicleBrand.getItems().add(brand);
        }
    }

    private void loadcustomerBO() throws Exception {
        customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    @FXML
    private void btnUpdateClicked(MouseEvent event) throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(txtCust_ID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
        boolean isUpdated = customerBO.updateCustomer(customerDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated  Succefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Updated  Unsuccefully", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void btnDeleteClicked(MouseEvent event) throws Exception {
        boolean isDeleted = customerBO.deleteCustomer(txtNicNo.getText());
        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted  Sucefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Deleted not Sucefully", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void btnAddClicked(MouseEvent event) throws Exception {
        CustomerDTO custDTO = new CustomerDTO(txtCust_ID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
        boolean isAdded = customerBO.addCustomer(custDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Added Sucefully", ButtonType.OK);
            alert.showAndWait();
            txtNicNo.setEditable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Added not Sucefully", ButtonType.OK);
            alert.showAndWait();
            txtNicNo.setEditable(true);
        }
    }

    private CustomerDTO getCustomerDetails() {
        String customerID = txtCust_ID.getText();
        String f_Name = txtF_Name.getText();
        String l_Name = txtL_Name.getText();
        String nicNO = txtNicNo.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        int mobileNO = Integer.parseInt(txtMobileNo.getText());
        CustomerDTO customerDTO = new CustomerDTO(customerID, f_Name, l_Name, nicNO, address, salary, mobileNO);
        return customerDTO;
    }

    private VehicleDTO getVehicleDetails() {
        String vehicleID = txtVehicleID.getText();
        String vehicleNO = txtVehicleNO.getText();
        String vehicleType = cmbVehicleType.getSelectionModel().getSelectedItem();
        String vehicleBrand = cmbVehicleBrand.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNO, vehicleType, vehicleBrand);
        return vehicleDTO;
    }

    private CleaningDTO getCleaningDetails() {
        String cleaningID = txtCleaningID.getText();
        String cleaningType = cmbCleaningType.getSelectionModel().getSelectedItem();
        String technicianID = cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName = cmbTechnicianName.getSelectionModel().getSelectedItem();
        double serviceFee = Double.parseDouble(txtServiceFee.getText());
        CleaningDTO cleaningDTO = new CleaningDTO(cleaningID, technicianID, technicianName, cleaningType, serviceFee);
        return cleaningDTO;
    }

    @FXML
    private void btnSaveAllDetailsClicked(MouseEvent event) throws Exception {
        addCleaningServiceBO = new AddCleaningServiceBOImpl();
        boolean isAddedCleaning = addCleaningServiceBO.addCleaningService(getCleaningDetails(), getCustomerDetails(), getVehicleDetails());
        if (isAddedCleaning) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cleaning Details Saved Successfully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cleaning Details Saved Unsuccessfully", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSearchCustomerClicked(MouseEvent event) throws Exception {
        btnSearchClicked=true;
        CustomerDTO searchCustomerDTO = customerBO.searchCustomer(txtNicNo.getText());
        txtCust_ID.setText(searchCustomerDTO.getCust_Id());
        txtF_Name.setText(searchCustomerDTO.getF_Name());
        txtL_Name.setText(searchCustomerDTO.getL_Name());
        txtAddress.setText(searchCustomerDTO.getCust_Address());
        txtSalary.setText("" + searchCustomerDTO.getCust_Salary());
        txtMobileNo.setText("" + searchCustomerDTO.getCust_Tel());
    }
     private void clearAll(){
        txtF_Name.clear();
        txtL_Name.clear();
        txtAddress.clear();
        txtSalary.clear();
        txtMobileNo.clear();
        txtNicNo.clear();
        txtVehicleNO.clear();
        cmbVehicleBrand.getSelectionModel().clearSelection();
        cmbVehicleType.getSelectionModel().clearSelection();
        cmbCleaningType.getSelectionModel().clearSelection();
        cmbTechnicianID.getSelectionModel().clearSelection();
        cmbTechnicianName.getSelectionModel().clearSelection();
        txtServiceFee.clear();
    }
    private void updateCustomer(){
        String nicNo = txtNicNo.getText();
        if(nicNo!=null){
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCust_ID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
                boolean isUpdated = customerBO.updateCustomer(customerDTO);
                if (isUpdated) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer Updated  Succefully", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Updated  Unsuccefully", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void updateVehicle(){
        String vehcicleNO = txtVehicleNO.getText();
        if(vehcicleNO!=null){
            try {
                VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNO.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());
                boolean isVehicleUpdated = vehicleBO.updateVehicle(vehicleDTO);
                if(isVehicleUpdated){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Updated Sucefully", ButtonType.OK);
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vehicle Updated not Sucefully", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void addClean() throws Exception{
        String cleaningID = txtCleaningID.getText();
        String technicianID = cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName = cmbTechnicianName.getSelectionModel().getSelectedItem();
        String cleaningType = cmbCleaningType.getSelectionModel().getSelectedItem();
        double serviceFee = Double.parseDouble(txtServiceFee.getText());
        CleaningDTO cleaningDTO = new CleaningDTO(cleaningID, technicianID, technicianName, cleaningType, serviceFee);
        boolean isAdded = cleaningBO.addCleaning(cleaningDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cleaning Details Added Successfully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cleaning Details Added Unsuccessfully", ButtonType.OK);
            alert.showAndWait();
        }
     }

    @FXML
    private void btnMakePaymentClicked(MouseEvent event){
        if(btnSearchClicked){
            try {
                updateCustomer();
                updateVehicle();
                addClean();
                loadCustomerID();
                loadVehicleID();
                loadCleaningID();
                clearAll();
            } catch (Exception ex) {
                Logger.getLogger(LubricentServiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCust_ID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
                if(customerDTO!=null){
                    customerBO.addCustomer(customerDTO);
                }
                VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNO.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());
                if(vehicleDTO!=null){
                    vehicleBO.addVehicle(vehicleDTO);
                }
                addClean();
                loadCustomerID();
                loadVehicleID();
                loadCleaningID();
            } catch (Exception ex) {
                Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

    @FXML
    private void txtF_NameOnAction(ActionEvent event) {
        boolean nameValidation = Validation.nameValidation(txtF_Name.getText());
        if(nameValidation){
            txtL_Name.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid First Name", ButtonType.OK);
            alert.showAndWait();
        }
    }
    private void searchCustomer() throws Exception{
        String nicNO = txtNicNo.getText();
        if(nicNO!=null){
            CustomerDTO searchCustomerDTO = customerBO.searchCustomer(txtNicNo.getText());
            if(searchCustomerDTO!=null){
                txtF_Name.setText(searchCustomerDTO.getF_Name());
                txtL_Name.setText(searchCustomerDTO.getL_Name());
                txtAddress.setText(searchCustomerDTO.getCust_Address());
                txtSalary.setText("" + searchCustomerDTO.getCust_Salary());
                txtMobileNo.setText("" + searchCustomerDTO.getCust_Tel());
                btnSearchClicked=true;  
            }
        }
    }

    @FXML
    private void txtNicNoOnAction(ActionEvent event) {
        try {
            nicNo=txtNicNo.getText();
            ResultSet rst = CrudUtil.executeQuery("select * from customer where Nic_No=?",nicNo);
            boolean nicValidation = Validation.nicValidation(txtNicNo.getText());
            if(nicValidation){
                enteredNicNO = txtNicNo.getText();
                txtF_Name.requestFocus();
                while (rst.next()) {
                    String compareTo = rst.getString("Nic_No");
                    if(enteredNicNO.equals(compareTo )){
                        btnSearchClicked=true;
                        searchCustomer();
                        txtF_Name.requestFocus();
                        break;
                    }else {
                        txtF_Name.requestFocus();
                    }
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid NIC Number", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(LubricentServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void txtL_NameOnAction(ActionEvent event) {
        boolean nameValidation = Validation.nameValidation(txtL_Name.getText());
        if(nameValidation){
            txtAddress.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid Last Name", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtAddressOnAction(ActionEvent event) {
        boolean addressValidation = Validation.addressValidation(txtAddress.getText());
        if(addressValidation){
            txtSalary.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid Customer Address", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtMobileNoOnAction(ActionEvent event) {
        boolean mobileNoValidation = Validation.contactNoValidation(txtMobileNo.getText());
        if(mobileNoValidation){
            cmbCleaningType.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Mobile Number", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtSalaryOnAction(ActionEvent event) {
        boolean salaryValidation = Validation.doubleValueValidation(txtSalary.getText());
        if(salaryValidation){
            txtMobileNo.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Customer Salary", ButtonType.OK);
            alert.showAndWait();
        }
    }
    private void searchVehicle() throws Exception{
        VehicleDTO searchVehicleDTO=vehicleBO.searchVehicle(txtVehicleNO.getText());
        if(searchVehicleDTO!=null){
            cmbVehicleBrand.getSelectionModel().select(searchVehicleDTO.getVeh_Brand());
            cmbVehicleType.getSelectionModel().select(searchVehicleDTO.getVeh_Type());
        }
    }

    @FXML
    private void txtVehicleNOOnAction(ActionEvent event) {
        try {
            vehicleNo=txtVehicleNO.getText();
            ResultSet rst = CrudUtil.executeQuery("select * from vehicle where Veh_No=?",vehicleNo);
            boolean vehNOValidation = Validation.vehicleNoValidation(vehicleNo);
            if(vehNOValidation){
                enteredVehicleNo = txtVehicleNO.getText();
                cmbVehicleType.requestFocus();
                while (rst.next()) {
                    String compareTo = rst.getString("Veh_No");
                    if(enteredVehicleNo.equals(compareTo )){
                        btnSearchClicked=true;
                        searchVehicle();
                        break;
                    }else {
                        cmbVehicleType.requestFocus();
                    }
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid Vehicle Number", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(LubricentServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtServiceFeeOnAction(ActionEvent event) {
        boolean serviceValidation = Validation.serviceFeeValidation(txtServiceFee.getText());
        if(serviceValidation){
            txtVehicleNO.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void cmbCleaningTypeOnAction(ActionEvent event) {
            cmbTechnicianID.requestFocus();
    }

    @FXML
    private void cmbVehicleTypeOnAction(ActionEvent event) {
        cmbVehicleBrand.requestFocus();    }

    @FXML
    private void cmbVehicleBrandOnAction(ActionEvent event) {
        btnMakePayment.requestFocus();
    }

    @FXML
    private void cmbTechnicianIDOnAction(ActionEvent event) {
        String T001,T002,T003,T004,T005,T006,T007,T008,T009,T0010;
        String selectedItem = cmbTechnicianID.getSelectionModel().getSelectedItem();
        switch(selectedItem){
            case "T001":cmbTechnicianName.getSelectionModel().select("Saman");break;
            case "T002":cmbTechnicianName.getSelectionModel().select("Kalum");break;
            case "T003":cmbTechnicianName.getSelectionModel().select("Priyantha");break;
            case "T004":cmbTechnicianName.getSelectionModel().select("Raveen");break;
            case "T005":cmbTechnicianName.getSelectionModel().select("Dasun");break;
            case "T006":cmbTechnicianName.getSelectionModel().select("Janith");break;
            case "T007":cmbTechnicianName.getSelectionModel().select("Lakmal");break;
            case "T008":cmbTechnicianName.getSelectionModel().select("Wipula");break;
            case "T009":cmbTechnicianName.getSelectionModel().select("Indika");break;
            case "T0010":cmbTechnicianName.getSelectionModel().select("Pawan");break;
            default://;
        }
        cmbTechnicianName.requestFocus();
    }

    @FXML
    private void cmbTechnicianNameOnAction(ActionEvent event) {
        String Saman, Kalum, Priyantha, Raveen, Dasun, Janith, Lakmal, Wipula, Indika, Pawan;
        String selectedItem = cmbTechnicianName.getSelectionModel().getSelectedItem();
        switch(selectedItem){
            case "Saman":cmbTechnicianID.getSelectionModel().select("T001");break;
            case "Kalum":cmbTechnicianID.getSelectionModel().select("T002");break;
            case "Priyantha":cmbTechnicianID.getSelectionModel().select("T003");break;
            case "Raveen":cmbTechnicianID.getSelectionModel().select("T004");break;
            case "Dasun":cmbTechnicianID.getSelectionModel().select("T005");break;
            case "Janith":cmbTechnicianID.getSelectionModel().select("T006");break;
            case "Lakmal":cmbTechnicianID.getSelectionModel().select("T007");break;
            case "Wipula":cmbTechnicianID.getSelectionModel().select("T008");break;
            case "Indika":cmbTechnicianID.getSelectionModel().select("T009");break;
            case "Pawan":cmbTechnicianID.getSelectionModel().select("T0010");break;
            default://;
        }
        txtServiceFee.requestFocus();
    }

    @FXML
    private void btnClearAllOnAction(ActionEvent event) {
        clearAll();
    }
}
