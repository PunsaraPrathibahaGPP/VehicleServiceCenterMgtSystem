/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.xml.ws.handler.MessageContext;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddAppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.entity.Customer;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MakeAnAppointmentFormController implements Initializable {
    @FXML
    private DatePicker datepickerAppointedDate;
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
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXComboBox<String> cmbvehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXButton btnMakePayment;
    @FXML
    private AnchorPane makeAnappointmentPane;
    @FXML
    private DatePicker inquieryNowDate;
    @FXML
    private JFXTextField inquieryNowTime;
    @FXML
    private JFXButton btnClearAll;
    private static String nicNo;
    private static String enteredNicNO;
    private static String enteredVehicleNo;
    private static String vehicleNo;
    private boolean btnSearchClicked;
    private boolean searchAll;
    private boolean customerSearch;
    private boolean vehicleSearch;
    private CustomerBO customerBo;
    private VehicleBO vehicleBo;
    private AddAppointmentBO addAppointmentBO;
    private AppointmentBO appointmentBo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadVehicleType();
            loadVehicleBrand();
            loadInquieryDate();
            loadInquieryTime();
            loadAddAppointmentBO();
            loadAppointmentID();
            loadCustomerIDS();
            loadVehicleIDS();
            disableIDS();
            setFocus();
            loadCustomerBo();
            loadvehicleBo();
            loadAppointmentBo();
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private void loadAppointmentBo() throws Exception{
        appointmentBo=(AppointmentBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.APPOINTMENT);
    }
    private void loadCustomerBo() throws Exception{
        customerBo=(CustomerBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }
    private void loadvehicleBo() throws Exception{
        vehicleBo=(VehicleBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.VEHICLE);
    }
    private void loadAddAppointmentBO() throws Exception{
        addAppointmentBO=(AddAppointmentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.ADDAPOINTMENT);
    }
    private void loadAppointmentID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("appointment", "App_no", "A");
            txtAppointmentNo.setText(newID);
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadVehicleIDS(){
        try {
            String newID;
            newID = IDGenerator.getNewID("vehicle", "Veh_ID", "V");
            txtVehicleID.setText(newID);
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadCustomerIDS(){
        try {
            String newID;
            newID = IDGenerator.getNewID("customer", "Cust_ID", "C");
            txtCustomerId.setText(newID);
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    private void disableIDS(){
        txtAppointmentNo.setDisable(true);
        txtCustomerId.setDisable(true);
        txtVehicleID.setDisable(true);
    }
    private void setFocus(){
        txtAppointedTime.requestFocus();
    }
    private void loadVehicleType() {
        String[] vehicleType = {"Sedan", "Coupe", "Wagon", "Van/MPV", "SUV", "Cabs", "CrossOver", "Convertiable", "Sports Car"};
        cmbvehicleType.getItems().clear();
        for (String type : vehicleType) {
            cmbvehicleType.getItems().add(type);
        }
    }
    private void loadInquieryDate() {
        inquieryNowDate.setValue(LocalDate.now());
        inquieryNowDate.setDisable(true);
    }
    private void loadInquiryTime(){
        try {
            Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    inquieryNowTime.setText(new SimpleDateFormat("hh:mm:ss a" ).format(new Date()));
                    inquieryNowTime.setFocusTraversable(false);
                    inquieryNowTime.setDisable(true);
                }
                
            }),new KeyFrame(Duration.seconds(1)));
            newTimeLine.setCycleCount(Animation.INDEFINITE);
            newTimeLine.play();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadInquieryTime() {
        loadInquiryTime();
    }

    private void loadVehicleBrand() {
        String[] vehicleBrand = {"BENZ", "BMW", "AUDI", "HONDA", "LEXUS", "NISSAN", "TATA", "TOYOTA", "SUZUKI", "RENAULT", "MAZDA", "MITSUBISHI", "LANDROWER", "CHEVROLET", "FORD", "Hyundai", "FIAT", "GEELY", "OTHER"};
        cmbVehicleBrand.getItems().clear();
        for (String brand : vehicleBrand) {
            cmbVehicleBrand.getItems().add(brand);
        }
    }
    
    private void updateCustomer() throws Exception {
        loadCustomerIDS();
        CustomerDTO customerDTO = new CustomerDTO(txtCustomerId.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNICNO.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
        boolean isUpdated = customerBo.updateCustomer(customerDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer Updated  Succefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Updated  Unsuccefully", ButtonType.OK);
            alert.showAndWait();
        }
    }
     private void searchCustomer() throws Exception {
        loadCustomerIDS();
        CustomerDTO customerDTO = customerBo.searchCustomer(txtNICNO.getText());
        if (customerDTO != null) {
            loadCustomerIDS();
            txtF_Name.setText(customerDTO.getF_Name());
            txtL_Name.setText(customerDTO.getL_Name());
            txtAddress.setText(customerDTO.getCust_Address());
            txtNICNO.setText(customerDTO.getNIC_NO());
            txtSalary.setText("" + customerDTO.getCust_Salary());
            txtMobileNo.setText("" + customerDTO.getCust_Tel());
        }
    }

    private CustomerDTO saveNewCustomer() {
        loadCustomerIDS();
        String customerID = txtCustomerId.getText();
        String f_Name = txtF_Name.getText();
        String l_Name = txtL_Name.getText();
        String nicNo = txtNICNO.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        int tel = Integer.parseInt(txtMobileNo.getText());
        CustomerDTO customerDTO = new CustomerDTO(customerID, f_Name, l_Name, nicNo, address, salary, tel);
        return customerDTO;
    }

    private AppointmentDTO saveAppointment() {
        loadAppointmentID();
        String customerID = txtCustomerId.getText();
        String vehicleID=txtVehicleID.getText();
        String appointmentNo = txtAppointmentNo.getText();
        String appointedDate = datepickerAppointedDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String inquiryTime = inquieryNowTime.getText();
        String inquiryDate = inquieryNowDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String appointedTime = txtAppointedTime.getText();
        AppointmentDTO appointmentDTO = new AppointmentDTO(customerID,vehicleID,appointmentNo, inquiryDate, inquiryTime, appointedDate, appointedTime);
        return appointmentDTO;
    }
    
    private void UpdateVehicle() throws Exception{
        loadVehicleIDS();
        VehicleDTO vehicleDTO = new VehicleDTO(txtVehicleID.getText(),txtVehicleNo.getText(),cmbvehicleType.getSelectionModel().getSelectedItem(),cmbVehicleBrand.getSelectionModel().getSelectedItem());
        boolean isUpdated = vehicleBo.updateVehicle(vehicleDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Updated  Succefully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vehicle Updated  Unsuccefully", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private VehicleDTO saveVehicle()throws Exception{
        loadVehicleIDS();
        String vehicleID = txtVehicleID.getText();
        String vehicleNO = txtVehicleNo.getText();
        String vehicleType = cmbvehicleType.getSelectionModel().getSelectedItem();
        String vehicleBrand = cmbVehicleBrand.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNO, vehicleType, vehicleBrand);
        return vehicleDTO;
    }
    private void makeConfirmSearch() throws Exception{
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation for Save/Delete Appointment");
        confirmationAlert.setContentText("Select OK to Keep Appointment.Select Cancel to Delete Appointment");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean isAdded = appointmentBo.addAppointment(saveAppointment());
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Added", ButtonType.OK);
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene((Pane) loader.load()));
                MakePaymentController controller = loader.<MakePaymentController>getController();
                controller.loadData(saveNewCustomer(),saveAppointment());
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment was Not Successfully Added", ButtonType.OK);
                alert.showAndWait();
            }
            loadAppointmentID();
            loadCustomerIDS();
            loadVehicleIDS();

        }if (result.get() == ButtonType.CANCEL) {
            Alert elseConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            elseConfirmationAlert.setTitle("Are you Sure to Delete Appointment?");
            elseConfirmationAlert.setContentText("press OK to Delete Appointment.");
            Optional<ButtonType> elseResult = elseConfirmationAlert.showAndWait();
            if (elseResult.get() == ButtonType.OK){
                boolean isDeleted = appointmentBo.deleteAppointment(txtAppointmentNo.getText());
                if (isDeleted) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                }
            }
	}
    }
    private void makeConfirmAdd() throws Exception{
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation for Save/Delete Appointment");
        confirmationAlert.setContentText("Do you wish to Make Payment?\nSelect OK to Keep Appointment.\nSelect Cancel to Delete Appointment");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean isAppointmentAdded = addAppointmentBO.addAppointment(saveNewCustomer(),saveAppointment(),saveVehicle());
            if (isAppointmentAdded){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Added", ButtonType.OK);
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene((Pane) loader.load()));
                MakePaymentController controller = loader.<MakePaymentController>getController();
                controller.loadData(saveNewCustomer(),saveAppointment());
                stage.show();
                //===================
//                Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
//                this.makeAnappointmentPane.getChildren().clear();
//                this.makeAnappointmentPane.getChildren().addAll(parent);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Not Successfully Added", ButtonType.OK);
                alert.showAndWait();
            }
            loadAppointmentID();
            loadVehicleIDS();
            loadCustomerIDS();
            clearAll();
        }if (result.get() == ButtonType.CANCEL) {
            Alert elseConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            elseConfirmationAlert.setTitle("Are you Sure to Delete Appointment?");
            elseConfirmationAlert.setContentText("press OK to Delete Appointment.");
            Optional<ButtonType> elseResult = elseConfirmationAlert.showAndWait();
            if (elseResult.get() == ButtonType.OK){
                boolean isCustomerDeleted=customerBo.deleteCustomer(txtCustomerId.getText());
                boolean isVehicleDeleted=vehicleBo.deleteVehicle(txtVehicleID.getText());
                boolean isAppointmentDeleted = appointmentBo.deleteAppointment(txtAppointmentNo.getText());
                if (isCustomerDeleted && isVehicleDeleted && isAppointmentDeleted) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                }
                loadAppointmentID();
                loadVehicleIDS();
                loadCustomerIDS();
            }
            
	}

    }
    private void makeConfirmCustomerSearch()throws Exception{
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation for Save/Delete Appointment");
        confirmationAlert.setContentText("Select OK to Keep Appointment.Select Cancel to Delete Appointment");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean isAdded = appointmentBo.addAppointment(saveAppointment());
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Added", ButtonType.OK);
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene((Pane) loader.load()));
                MakePaymentController controller = loader.<MakePaymentController>getController();
                controller.loadData(saveNewCustomer(),saveAppointment());
                stage.show();
//                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
//                MakePaymentController controller = loader.<MakePaymentController>getController();
//                controller.loadData(saveNewCustomer(),saveAppointment());
//                Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
//                this.makeAnappointmentPane.getChildren().clear();
//                this.makeAnappointmentPane.getChildren().addAll(parent);
            } else { 
                Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment was Successfully Added", ButtonType.OK);
                alert.showAndWait();
            }
            loadAppointmentID();
            loadCustomerIDS();
            loadVehicleIDS();
            
        }if (result.get() == ButtonType.CANCEL) {
            Alert elseConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            elseConfirmationAlert.setTitle("Are you Sure to Delete Appointment?");
            elseConfirmationAlert.setContentText("press OK to Delete Appointment.");
            Optional<ButtonType> elseResult = elseConfirmationAlert.showAndWait();
            if (elseResult.get() == ButtonType.OK){
                boolean isDeleteVehicle = vehicleBo.deleteVehicle(txtVehicleNo.getText());
                boolean isDeleteAppointment = appointmentBo.deleteAppointment(txtAppointmentNo.getText());
                if (isDeleteVehicle && isDeleteAppointment){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment was Successfully Deleted", ButtonType.OK);
                    alert.show();
                }
            }
	}
    }
    private void makeConfirmAllFieldsFill(){
        boolean nicNONotEmpty = Validation.textFieldNotEmpty(txtNICNO);
        boolean f_NameNotEmpty = Validation.textFieldNotEmpty(txtF_Name);
        boolean l_NameNotEmpty = Validation.textFieldNotEmpty(txtL_Name);
        boolean addressNotEmpty = Validation.textFieldNotEmpty(txtAddress);
        boolean salaryNotEmpty = Validation.textFieldNotEmpty(txtSalary);
        boolean telNotEmpty = Validation.textFieldNotEmpty(txtMobileNo);
        boolean veh_NoNONotEmpty = Validation.textFieldNotEmpty(txtVehicleNo);
        boolean apptimeNotEmpty = Validation.textFieldNotEmpty(txtAppointedTime);
        boolean appointedDate = datepickerAppointedDate.getValue() == null;
        boolean vehicleBrandSelected = cmbVehicleBrand.getSelectionModel().isSelected(0);
        boolean vehicleTypeSelected = cmbvehicleType.getSelectionModel().isSelected(0);
        if(nicNONotEmpty && f_NameNotEmpty && l_NameNotEmpty && addressNotEmpty && salaryNotEmpty && telNotEmpty && veh_NoNONotEmpty && apptimeNotEmpty && appointedDate && vehicleBrandSelected && vehicleTypeSelected){
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"All Fields Should be Filled", ButtonType.OK); 
            alert.setTitle("Every Field Mentioned Above Should be Filled");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnMakePaymentClicked(MouseEvent event) throws Exception {
        if(customerSearch || vehicleSearch || searchAll){ 
           makeConfirmSearch();
           clearAll();
        }
        if(customerSearch || searchAll){
            makeConfirmCustomerSearch();
            saveVehicle();
            clearAll();
        }
        else{
            makeConfirmAdd();
            loadAppointmentID();
            clearAll();
        }
    }

    @FXML
    private void btnSearchClicked(MouseEvent event) {
    }
    
    private void confirmCustomerToSearch(){
        String nicNo = txtNICNO.getText();
        if(nicNo!=null){
            try {
                CustomerDTO searchCustomer = customerBo.searchCustomer(txtNICNO.getText());
                if(searchCustomer != null){
                    txtCustomerId.setText(searchCustomer.getCust_Id());
                    txtF_Name.setText(searchCustomer.getF_Name());
                    txtL_Name.setText(searchCustomer.getL_Name());
                    txtNICNO.setText(searchCustomer.getNIC_NO());
                    txtAddress.setText(searchCustomer.getCust_Address());
                    txtSalary.setText(Double.toString(searchCustomer.getCust_Salary()));
                    txtMobileNo.setText(Integer.toString(searchCustomer.getCust_Tel()));
                    updateCustomer();
                }else{
                    txtL_Name.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please Enter At Least NICNO or VehicleID\nto Access this Function",ButtonType.OK );
            alert.showAndWait();
        }
    }
    private void confirmVehicleToSearch(){
        String vehicleNO = txtVehicleNo.getText();
        if(vehicleNO!=null){
            try {
                VehicleDTO searchVehicle=vehicleBo.searchVehicle(vehicleNO);
                if(searchVehicle!=null){
                    this.vehicleSearch=true;
                    txtVehicleID.setText(searchVehicle.getVeh_ID());
                    cmbVehicleBrand.getSelectionModel().select(searchVehicle.getVeh_Brand());
                    cmbvehicleType.getSelectionModel().select(searchVehicle.getVeh_Type());
                    UpdateVehicle();
                }else{
                    cmbvehicleType.requestFocus();
                }
            } catch (Exception ex) {
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please Enter At Least NICNO or VehicleID\nto Access this Function",ButtonType.OK );
            alert.showAndWait();
        }
    }

    private void btnSearchAllClicked(MouseEvent event) {
        try {
            this.searchAll=true;
            confirmCustomerToSearch();
            confirmVehicleToSearch();
        } catch (Exception ex) {
           
        }
    }

    private void searchCustomers() throws Exception{
        CustomerDTO searchCustomerDTO = customerBo.searchCustomer(txtNICNO.getText());
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
    private void txtNICNOOnAction(ActionEvent event) {
        try {
            nicNo=txtNICNO.getText();
            ResultSet rst = CrudUtil.executeQuery("select * from customer where Nic_No=?",nicNo);
            boolean nicValidation = Validation.nicValidation(txtNICNO.getText());
            if(nicValidation){
                enteredNicNO = txtNICNO.getText();
                txtF_Name.requestFocus();
                while (rst.next()) {
                    String compareTo = rst.getString("Nic_No");
                    if(enteredNicNO.equals(compareTo )){
                        searchCustomers();
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
    private void txtF_NameOnAction(ActionEvent event) {
        boolean nameValidation = Validation.nameValidation(txtF_Name.getText());
        if(nameValidation){
            txtL_Name.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid First Name", ButtonType.OK);
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
    private void txtSalaryOnAction(ActionEvent event) {
        boolean salaryValidation = Validation.doubleValueValidation(txtSalary.getText());
        if(salaryValidation){
            txtMobileNo.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Customer Salary", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtMobileNoOnAction(ActionEvent event) {
        boolean mobileNoValidation = Validation.contactNoValidation(txtMobileNo.getText());
        if(mobileNoValidation){
            txtVehicleNo.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Mobile Number", ButtonType.OK);
            alert.showAndWait();
        }
    }
     private void searchVehicle() throws Exception{
        String vehicleNO = txtVehicleNo.getText();
        if(vehicleNO!=null){
            VehicleDTO searchVehicleDTO=vehicleBo.searchVehicle(txtVehicleNo.getText());
            if(searchVehicleDTO!=null){
                cmbVehicleBrand.getSelectionModel().select(searchVehicleDTO.getVeh_Brand());
                cmbvehicleType.getSelectionModel().select(searchVehicleDTO.getVeh_Type());
            }
        }
    }

    @FXML
    private void txtVehicleNoOnAction(ActionEvent event) {
        try {
            vehicleNo=txtVehicleNo.getText();
            ResultSet rst = CrudUtil.executeQuery("select * from vehicle where Veh_No=?",vehicleNo);
            boolean vehNOValidation = Validation.vehicleNoValidation(vehicleNo);
            if(vehNOValidation){
                enteredVehicleNo = txtVehicleNo.getText();
                cmbvehicleType.requestFocus();
                while (rst.next()) {
                    String compareTo = rst.getString("Veh_No");
                    if(enteredVehicleNo.equals(compareTo )){
                        searchVehicle();
                        break;
                    }else {
                        cmbvehicleType.requestFocus();
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
    private void txtAppointedTimeOnAction(ActionEvent event){
        String appointmentDate=null;
        try{
            boolean isEmpty = datepickerAppointedDate.getValue().toString().isEmpty();
            appointmentDate=datepickerAppointedDate.getValue().toString();
        }catch(Exception ex){
            Alert alert=new Alert(Alert.AlertType.ERROR, "Date Field can't be empty.\nPlease Fill Date Field.", ButtonType.OK);
            alert.setTitle("Fields can't be empty");
            alert.showAndWait();
        }
        if(appointmentDate!=null){
            String appointmentTime=txtAppointedTime.getText();
            boolean appointedTimeValidation=Validation.timeValidation(appointmentTime);
            if(!appointedTimeValidation){
                Alert alert=new Alert(Alert.AlertType.ERROR, "The Types You Entered Do not Recognize by the System\nPlease Enter Correct Date & Time", ButtonType.OK);
                alert.setTitle("Incompatible Types");
                alert.showAndWait();
            }
            boolean appointedDateValidation = Validation.dateValidation(appointmentDate);
            if(!appointedDateValidation){
                Alert alert=new Alert(Alert.AlertType.ERROR, "The Types You Entered Do not Recognize by the System\nPlease Enter Correct Date & Time", ButtonType.OK);
                alert.setTitle("Incompatible Types");
                alert.showAndWait();
            }
            if(appointedDateValidation  && appointedTimeValidation){
                try {
                    ResultSet rst = CrudUtil.executeQuery("select * from appointment where App_date=? && App_time = ? ", appointmentDate, appointmentTime);
                    while (rst.next()) {
                        String appDateComparison = rst.getString("App_date");
                        String appTimeComparison = rst.getString("App_time");
                        if (appointmentDate.equals(appDateComparison) && appointmentTime.equals(appTimeComparison)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "You Selected Date & Time has Reserved Early For Another Customer.\nPlease select Another Date & Time", ButtonType.OK);
                            alert.showAndWait();
                            break;
                        } else {
                            txtNICNO.requestFocus();
                        }
                    }
                    txtNICNO.requestFocus();
                } catch (Exception ex) {
                    Logger.getLogger(MakeAnAppointmentFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
           
        }
    }
    private void clearAll(){
        txtNICNO.clear();
        txtF_Name.clear();
        txtL_Name.clear();
        txtAddress.clear();
        txtSalary.clear();
        txtMobileNo.clear();
        txtVehicleNo.clear();
        cmbVehicleBrand.getSelectionModel().clearSelection();
        cmbvehicleType.getSelectionModel().clearSelection();
        txtAppointedTime.clear();
    }

    @FXML
    private void btnClearAllClicked(ActionEvent event) {
        clearAll();
    }

    @FXML
    private void datepickerAppointedDateOnAction(ActionEvent event) {
        try{
            boolean isEmpty = datepickerAppointedDate.getValue().toString().isEmpty();
        }catch(Exception ex){
            Alert alert=new Alert(Alert.AlertType.ERROR, "Date Field can't be empty.\nPlease Fill Date Field.", ButtonType.OK);
            alert.setTitle("Fields can't be empty");
            alert.showAndWait();
        }
        String appointmentDate=datepickerAppointedDate.getValue().toString();
        boolean appointedDateValidation = Validation.dateValidation(appointmentDate);
        if (!appointedDateValidation) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The Types You Entered Do not Recognize by the System\nPlease Enter Correct Date & Time", ButtonType.OK);
            alert.setTitle("Incompatible Types");
            alert.showAndWait();
        }else{
            txtAppointedTime.requestFocus();
        }
    }

    @FXML
    private void txtAppointedTimeOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Compatible Types:\n3:00AM /PM   3.00AM /PM");
        txtAppointedTime.setTooltip(tooltip);
    }

    @FXML
    private void txtNameonMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("First Letter Of Name should be a Capital Letter\nSample Text: Saman Perera");
        txtF_Name.setTooltip(tooltip);
        txtL_Name.setTooltip(tooltip);
    }

    @FXML
    private void txtAddressOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Customer Address Field\nIncompatible Types: / , ? , . , ! , ` , ~ , : , ; ");
        txtAddress.setTooltip(tooltip);
    }

    private void txtSalaryOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Sample Text: RS: 50000.00");
        txtSalary.setTooltip(tooltip);
    }

    @FXML
    private void txtMobileNoOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Phone Number Should include 10 Digits\nSample Text: 0712345678");
        txtMobileNo.setTooltip(tooltip);
    }

    @FXML
    private void txtVehicleNoOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Sample Text: SD2345");
        txtVehicleNo.setTooltip(tooltip);
    }

    @FXML
    private void txtNICNOOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Sample Text: 981234567V");
        txtVehicleNo.setTooltip(tooltip);
    }

}
