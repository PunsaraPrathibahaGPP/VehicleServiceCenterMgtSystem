/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddRepairServiceBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.RepairBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AddRepairServiceBOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.CustomerDAO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.RepairDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class VehicleRepairFormController implements Initializable {

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
    private JFXTextField txtCustomerID;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXTextField txtVehicleNO;
    @FXML
    private TableView<?> tblViewAll;
    @FXML
    private JFXTextField txtRepairID;
    @FXML
    private JFXComboBox<String> cmbRepairType;
    @FXML
    private JFXComboBox<String> cmbVehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXComboBox<String> cmbTechnicianID;
    @FXML
    private JFXTextField txtServiceFee;
    @FXML
    private JFXComboBox<String> cmbTechnicianName;
    @FXML
    private JFXTextField txtOtherFees;
    @FXML
    private JFXTextField txtTotalFee;
    @FXML
    private JFXButton btnSearchCustomer;
    @FXML
    private JFXButton btnMakePayment;
    @FXML
    private JFXTextField txtCust_ID;
    
    private static AddRepairServiceBO addRepairServiceBO;
    private static CustomerBO customerBO;
    private static VehicleBO vehicleBO;
    private RepairBO repairBO;
    private double serviceFee;
    private double otherFees;
    private double totalAmmount=0;
    private boolean btnSearchClicked;
    @FXML
    private JFXButton btnClearAll;
    private static String nicNo;
    private static String enteredNicNO;
    private static String enteredVehicleNo;
    private static String vehicleNo;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            txtNicNo.requestFocus();
            loadVehicleID();
            loadRepairID();
            loadCustomerID();
            loadRepairBO();
            loadReapirType();
            loadVehicleType();
            loadVehicleBrand();
            loadTechnicianID();
            loadTechnicianName();
            loadCustomerBO();
        } catch (Exception ex) {
            Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadCustomerBO() throws Exception{
        customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
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
    private void loadRepairID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("repair", "R_ID", "R");
            txtRepairID.setText(newID);
            txtRepairID.setDisable(true);
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

    private void loadRepairBO() throws Exception {
        repairBO = (RepairBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.REPAIR);
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

    private void loadReapirType() {
        String[] repairTypes = {"EngineRepair", "BreakBindingRepair", "ElectricalRepair", "ClutchPlateRepair", "Other"};
        cmbRepairType.getItems().clear();
        for (String repairType : repairTypes) {
            cmbRepairType.getItems().add(repairType);
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

    @FXML
    private void btnAddClicked(MouseEvent event) throws Exception {
    }

    @FXML
    private void btnUpdateClicked(MouseEvent event) throws Exception {
        String nicNo = txtNicNo.getText();
        if(nicNo!=null){
            CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
            boolean isUpdated = customerBO.updateCustomer(customerDTO);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated  Succefully", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Updated  Unsuccefully", ButtonType.OK);
                alert.showAndWait();
            }
        }
        String vehcicleNO = txtVehicleNO.getText();
        if(vehcicleNO!=null){
            VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNO.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());
            boolean isVehicleUpdated = vehicleBO.updateVehicle(vehicleDTO);
            if(isVehicleUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Updated Sucefully", ButtonType.OK);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vehicle Updated not Sucefully", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnDeleteClicked(MouseEvent event) throws Exception {
        String nicNo = txtNicNo.getText();
        if(nicNo!=null){
            boolean isDeleted = customerBO.deleteCustomer(txtNicNo.getText());
            if (isDeleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer Deleted  Sucefully", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Deleted not Sucefully", ButtonType.OK);
                alert.showAndWait();
            }
        }
        String vehcicleNO = txtVehicleNO.getText();
        if(vehcicleNO!=null){
            boolean isVehicleDeleted = vehicleBO.deleteVehicle(txtVehicleNO.getText());
            if(isVehicleDeleted){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Deleted Sucefully", ButtonType.OK);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vehicle Deleted not Sucefully", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    private RepairDTO getRepairDetails() {
        String repeirID = txtRepairID.getText();
        String technicianID = cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName = cmbTechnicianName.getSelectionModel().getSelectedItem();
        String repairType = cmbRepairType.getSelectionModel().getSelectedItem();
        double serviceFee = Double.parseDouble(txtServiceFee.getText());
        double otherFees = Double.parseDouble(txtOtherFees.getText());
        double totalFees = Double.parseDouble(txtTotalFee.getText());
        return new RepairDTO(repeirID, technicianID, technicianName, repairType, serviceFee, otherFees, totalFees);
    }

    private CustomerDTO getCustomerDetails() {
        String customerID = txtCustomerID.getText();
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

    private void searchAll() throws Exception{
        CustomerDTO searchCustomerDTO = customerBO.searchCustomer(txtNicNo.getText());
        if(searchCustomerDTO!=null){
            txtF_Name.setText(searchCustomerDTO.getF_Name());
            txtL_Name.setText(searchCustomerDTO.getL_Name());
            txtAddress.setText(searchCustomerDTO.getCust_Address());
            txtSalary.setText("" + searchCustomerDTO.getCust_Salary());
            txtMobileNo.setText("" + searchCustomerDTO.getCust_Tel());
            btnSearchClicked=true;  
        }
        VehicleDTO searchVehicleDTO=vehicleBO.searchVehicle(txtVehicleNO.getText());
        if(searchVehicleDTO!=null){
            cmbVehicleBrand.getSelectionModel().select(searchVehicleDTO.getVeh_Brand());
            cmbVehicleType.getSelectionModel().select(searchVehicleDTO.getVeh_Type());
        }
    }

    @FXML
    private void btnSearchCustomerClicked(MouseEvent event){
        try {
            btnSearchClicked=true;
            searchAll();
        } catch (Exception ex) {
            Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateCustomer(){
        String nicNo = txtNicNo.getText();
        if(nicNo!=null){
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
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
    private void addRepair() throws Exception{
        String repairID = txtRepairID.getText();
        String technicianID = cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName = cmbTechnicianName.getSelectionModel().getSelectedItem();
        String repairType = cmbRepairType.getSelectionModel().getSelectedItem();
        double serviceFee = Double.parseDouble(txtServiceFee.getText());
        double otherFees = Double.parseDouble(txtOtherFees.getText());
        double totalFee = Double.parseDouble(txtTotalFee.getText());
        RepairDTO repairDTO = new RepairDTO(repairID, technicianID, technicianName, repairType, totalFee, otherFees, totalFee);
        if (repairDTO != null) {
            boolean isAdded = repairBO.addRepair(repairDTO);
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Repair Details Successfully Added", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Repair Details Added Unsuccessfully", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnMakePaymentClicked(MouseEvent event) {
        if(btnSearchClicked){
            try {
                updateCustomer();
                updateVehicle();
                addRepair();
                loadCustomerID();
                loadVehicleID();
                loadRepairID();
                this.btnSearchClicked=false;
                clearAll();
            } catch (Exception ex) {
                Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNicNo.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
                if(customerDTO!=null){
                    customerBO.addCustomer(customerDTO);
                }
                VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(), txtVehicleNO.getText(), cmbVehicleType.getSelectionModel().getSelectedItem(), cmbVehicleBrand.getSelectionModel().getSelectedItem());
                if(vehicleDTO!=null){
                    vehicleBO.addVehicle(vehicleDTO);
                }
                addRepair();
                loadCustomerID();
                loadVehicleID();
                loadRepairID();
                this.btnSearchClicked=false;
                clearAll();
            } catch (Exception ex) {
                Logger.getLogger(VehicleRepairFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void searchVehicle() throws Exception{
        String vehicleNO = txtVehicleNO.getText();
        if(vehicleNO!=null){
            VehicleDTO searchVehicleDTO=vehicleBO.searchVehicle(txtVehicleNO.getText());
            if(searchVehicleDTO!=null){
                cmbVehicleBrand.getSelectionModel().select(searchVehicleDTO.getVeh_Brand());
                cmbVehicleType.getSelectionModel().select(searchVehicleDTO.getVeh_Type());
            }
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
            this.serviceFee=Double.parseDouble(txtServiceFee.getText());
            this.totalAmmount=this.totalAmmount+this.serviceFee;
            txtOtherFees.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtOtherFeesOnAction(ActionEvent event) {
        boolean otherFeesValidation = Validation.serviceFeeValidation(txtOtherFees.getText());
        if(otherFeesValidation){
            this.otherFees=Double.parseDouble(txtOtherFees.getText());
            this.totalAmmount=this.totalAmmount+this.otherFees;
            txtTotalFee.setText(""+this.totalAmmount);
            txtTotalFee.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtTotalFeeOnAction(ActionEvent event) {
        boolean otherFeesValidation = Validation.serviceFeeValidation(txtTotalFee.getText());
        if(otherFeesValidation){
            Alert alert=new Alert(Alert.AlertType.ERROR, "Sorry! You can not Modify this Field", ButtonType.OK);
            alert.showAndWait();  
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
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

    @FXML
    private void txtNicNoOnAction(ActionEvent event) {
        try {
            nicNo=txtNicNo.getText();
            ResultSet rst = CrudUtil.executeQuery("select * from customer where Nic_No=?",nicNo);
            boolean nicValidation = Validation.nicValidation(txtNicNo.getText());
            if(nicValidation){
                enteredNicNO = txtNicNo.getText();
                txtF_Name.requestFocus();
                while (rst.next()){
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
            cmbRepairType.requestFocus();
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
        cmbRepairType.getSelectionModel().clearSelection();
        cmbTechnicianID.getSelectionModel().clearSelection();
        cmbTechnicianName.getSelectionModel().clearSelection();
        txtServiceFee.clear();
        txtOtherFees.clear();
        txtTotalFee.clear();
    }

    @FXML
    private void btnClearAllOnAction(ActionEvent event) {
        clearAll();
    }

}
