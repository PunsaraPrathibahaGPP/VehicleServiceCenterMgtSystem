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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AddAppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.VehicleBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AddAppointmentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.VehicleDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MakeAnAppointmentController implements Initializable {

    @FXML
    private JFXTextField txtNICNO;
    @FXML
    private JFXTextField txtF_Name;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtL_Name;
    private JFXTextField txtCustomerID;
    @FXML
    private JFXTextField txtVehicleNo;
    @FXML
    private JFXTextField txtVehicleID;
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
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtAppointmentNo;
    @FXML
    private JFXTextField txtAppointedTime;
    @FXML
    private JFXComboBox<String> cmbvehicleType;
    @FXML
    private JFXComboBox<String> cmbVehicleBrand;
    @FXML
    private JFXTextField txtCustomerId;

    private boolean isAdded;
    private boolean isVehicleSearched;
    private boolean isCustomerSearched;
    private CustomerDTO customerDTO;
    private VehicleDTO vehicleDTO;

    private CustomerBO customerBo;
    private VehicleBO vehicleBo;
    private AppointmentBO appointmentBO;
    private AddAppointmentBO addAppointmentBO;
    private CustomerDTO addCustomer;
    private TableView<CustomerDTO> tblViewAll;
    @FXML
    private JFXButton btnCustomerSearch;
    @FXML
    private JFXButton btnVehicleSearch;

    public MakeAnAppointmentController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadAppointmentBO();
            loadCustomerBO();
            loadVehicleBO();
            loadVehicleType();
            loadVehicleBrand();
            loadInquieryDate();
            loadInquieryTime();
            loadCustomerDetail();
            loadAppointmentID();

        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerDetail() throws Exception {
        tblViewAll.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
        tblViewAll.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
        tblViewAll.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
        tblViewAll.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC_NO"));
        tblViewAll.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Cust_Address"));
        tblViewAll.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Cust_Salary"));
        tblViewAll.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Cust_Tel"));
        ArrayList<CustomerDTO> customerDTO = customerBo.getAllCustomer();
        tblViewAll.setItems(FXCollections.observableArrayList(customerDTO));
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

    private boolean searchCustomer() throws Exception {
        CustomerDTO customerDTO = customerBo.searchCustomer(txtNICNO.getText());
        boolean isUpdated = false;
        if (customerDTO != null) {
            txtCustomerId.setText(customerDTO.getCust_Id());
            txtF_Name.setText(customerDTO.getF_Name());
            txtL_Name.setText(customerDTO.getL_Name());
            txtAddress.setText(customerDTO.getCust_Address());
            txtNICNO.setText(customerDTO.getNIC_NO());
            txtSalary.setText("" + customerDTO.getCust_Salary());
            txtMobileNo.setText("" + customerDTO.getCust_Tel());
        }
        return true;
    }

    @FXML
    private void btnCustomerSearchClicked(MouseEvent event) throws Exception {
        this.isCustomerSearched = searchCustomer();
    }

    private boolean updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtF_Name.getText(), txtL_Name.getText(), txtNICNO.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()), Integer.parseInt(txtMobileNo.getText()));
        boolean isUpdated = customerBo.updateCustomer(customerDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated  Succefully", ButtonType.OK);
            alert.showAndWait();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Updated  Unsuccefully", ButtonType.OK);
            alert.showAndWait();
        }
        return false;
    }

    private CustomerDTO saveNewCustomer() {
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
        String customerID = txtCustomerId.getText();
        String vehicleID=txtVehicleID.getText();
        String appointmentNo = txtAppointmentNo.getText();
        String appointedDate = datepickerAppointedDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String inquiryTime = txtInquieryTime.getText();
        String inquiryDate = datepickerInquieryDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String appointedTime = txtAppointedTime.getText();
        AppointmentDTO appointmentDTO = new AppointmentDTO(customerID,vehicleID,appointmentNo, inquiryDate, inquiryTime, appointedDate, appointedTime);
        return appointmentDTO;
    }

    private VehicleDTO saveVehicle() {
        String vehicleID = txtVehicleID.getText();
        String vehicleNO = txtVehicleNo.getText();
        String vehicleType = cmbvehicleType.getSelectionModel().getSelectedItem();
        String vehicleBrand = cmbVehicleBrand.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNO, vehicleType, vehicleBrand);
        return vehicleDTO;
    }

    @FXML
    private void lblSaveClicked(MouseEvent event) throws Exception {
//        try {
//            if (isCustomerSearched | isVehicleSearched) {
//                boolean isAddAppointment = appointmentBO.addAppointment(saveAppointment());
//                if (isAddAppointment) {
//                    Alert appointmentAlert = new Alert(Alert.AlertType.INFORMATION, "Appointment Added Successfully", ButtonType.OK);
//                    appointmentAlert.showAndWait();
//                } else {
//                    Alert appointmentAlert = new Alert(Alert.AlertType.ERROR, "Appointment Added Unsuccessfully", ButtonType.OK);
//                    appointmentAlert.showAndWait();
//                }
//                if (isCustomerSearched) {
//                    boolean updateCustomer = customerBo.updateCustomer(saveNewCustomer());
//                    if (updateCustomer) {
//                        Alert customerAlert = new Alert(Alert.AlertType.INFORMATION, "Customer Updated", ButtonType.OK);
//                        customerAlert.showAndWait();
//                    } else {
//                        boolean isAdded = customerBo.addCustomer(saveNewCustomer());
//                        if (isAdded) {
//                            Alert customerAlert = new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully", ButtonType.OK);
//                            customerAlert.showAndWait();
//                        } else {
//                            Alert customerAlert = new Alert(Alert.AlertType.ERROR, "Customer Added Unsuccessfully", ButtonType.OK);
//                            customerAlert.showAndWait();
//                        }
//                    }
//                }
//                if (isVehicleSearched) {
//                    boolean updateVehicle = vehicleBo.updateVehicle(saveVehicle());
//                    if (updateVehicle) {
//                        Alert vehicleAlert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Updated Successfully", ButtonType.OK);
//                        vehicleAlert.showAndWait();
//                    } else {
//                        boolean isAdded = vehicleBo.addVehicle(saveVehicle());
//                        if (isAdded) {
//                            Alert vehicleAlert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Added Successfully", ButtonType.OK);
//                            vehicleAlert.showAndWait();
//                        } else {
//                            Alert vehicleAlert = new Alert(Alert.AlertType.ERROR, "Vehicle Added Unsuccessfully", ButtonType.OK);
//                            vehicleAlert.showAndWait();
//                        }
//                    }
//                }
//
//            } else {
//                addAppointmentBO = new AddAppointmentBOImpl();
//                boolean addAppointment = addAppointmentBO.addAppointment(saveNewCustomer(), saveAppointment(), saveVehicle());
//                if (addAppointment) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfull", ButtonType.OK);
//                    alert.showAndWait();
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Not Successfully", ButtonType.OK);
//                    alert.showAndWait();
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void lblDiscardClicked(MouseEvent event) {

    }

    private boolean updateVehicle() throws Exception {
        String vehicleID = txtVehicleID.getText();
        String vehicleNO = txtVehicleNo.getText();
        String vehicleType = cmbvehicleType.getSelectionModel().getSelectedItem();
        String vehicleBrand = cmbVehicleBrand.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNO, vehicleType, vehicleBrand);
        return vehicleBo.updateVehicle(vehicleDTO);
    }

    private boolean searchVehicle() throws Exception {
        VehicleDTO vehicleDTO = vehicleBo.searchVehicle(txtVehicleNo.getText());
        boolean isUpdated = false;
        if (vehicleDTO != null) {
            txtVehicleID.setText(vehicleDTO.getVeh_ID());
            cmbvehicleType.getSelectionModel().select(vehicleDTO.getVeh_Type());
            cmbVehicleBrand.getSelectionModel().select(vehicleDTO.getVeh_Brand());
            isUpdated = updateVehicle();
        }
        return isUpdated;
    }

    @FXML
    private void btnVehicleSearch(MouseEvent event) throws Exception {
        this.isVehicleSearched = searchVehicle();
    }

    private void loadCustomerDetails(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            this.txtCustomerID.setText(customerDTO.getCust_Id());
            this.txtCustomerId.setText(customerDTO.getCust_Id());
            this.txtF_Name.setText(customerDTO.getF_Name());
            this.txtL_Name.setText(customerDTO.getL_Name());
            this.txtNICNO.setText(customerDTO.getNIC_NO());
            this.txtAddress.setText(customerDTO.getCust_Address());
            this.txtSalary.setText("" + customerDTO.getCust_Salary());
            this.txtMobileNo.setText("" + customerDTO.getCust_Tel());
        }
    }

    public void getCustomerDetails(CustomerDTO customerDTO, VehicleDTO vehicleDTO) {
        this.customerDTO = customerDTO;
        this.vehicleDTO = vehicleDTO;
    }

    private void lblmouseExited(MouseEvent event) {
        Label source = (Label) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), source);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
        source.setEffect(null);
    }

    private void lblmouseEntered(MouseEvent event) {
        Label source = (Label) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), source);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
        source.setEffect(null);
    }

    private void btnMakePaymentClicked(MouseEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        alert.setTitle("Confirmation to Save Appointment Details");
        alert.setContentText("Do you wish to Continute the process to MakePayment");
        alert.setContentText("Select" +"OK"+ "to Save "+","+"Select"+"Cancel to Delete");
        if (result.get() == ButtonType.OK) {
            boolean isSaved = saveAppointmentDetails();
            if(isSaved){
                Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment Successfully Added into the System", ButtonType.OK);
                saveAlert.show();
            }else{
                Alert saveAlert = new Alert(Alert.AlertType.ERROR, "Appointment Was not Successfully Added into the System", ButtonType.OK);
                saveAlert.show();
            }
        }else{
            //
        }
        
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();

    }

    
    private boolean saveAppointmentDetails(){
         try {
            if (isCustomerSearched | isVehicleSearched) {
                boolean isAddAppointment = appointmentBO.addAppointment(saveAppointment());
                if (isAddAppointment) {
                    Alert appointmentAlert = new Alert(Alert.AlertType.INFORMATION, "Appointment Added Successfully", ButtonType.OK);
                    appointmentAlert.showAndWait();
                } else {
                    Alert appointmentAlert = new Alert(Alert.AlertType.ERROR, "Appointment Added Unsuccessfully", ButtonType.OK);
                    appointmentAlert.showAndWait();
                }
                if (isCustomerSearched) {
                    boolean updateCustomer = customerBo.updateCustomer(saveNewCustomer());
                    if (updateCustomer) {
                        Alert customerAlert = new Alert(Alert.AlertType.INFORMATION, "Customer Updated", ButtonType.OK);
                        customerAlert.showAndWait();
                    } else {
                        boolean isAdded = customerBo.addCustomer(saveNewCustomer());
                        if (isAdded) {
                            Alert customerAlert = new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully", ButtonType.OK);
                            customerAlert.showAndWait();
                        } else {
                            Alert customerAlert = new Alert(Alert.AlertType.ERROR, "Customer Added Unsuccessfully", ButtonType.OK);
                            customerAlert.showAndWait();
                        }
                    }
                }
                if (isVehicleSearched) {
                    boolean updateVehicle = vehicleBo.updateVehicle(saveVehicle());
                    if (updateVehicle) {
                        Alert vehicleAlert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Updated Successfully", ButtonType.OK);
                        vehicleAlert.showAndWait();
                    } else {
                        boolean isAdded = vehicleBo.addVehicle(saveVehicle());
                        if (isAdded) {
                            Alert vehicleAlert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Added Successfully", ButtonType.OK);
                            vehicleAlert.showAndWait();
                        } else {
                            Alert vehicleAlert = new Alert(Alert.AlertType.ERROR, "Vehicle Added Unsuccessfully", ButtonType.OK);
                            vehicleAlert.showAndWait();
                        }
                    }
                }

            }else {
                addAppointmentBO = new AddAppointmentBOImpl();
                boolean addAppointment = addAppointmentBO.addAppointment(saveNewCustomer(), saveAppointment(), saveVehicle());
                if (addAppointment) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfull", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Not Successfully", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MakeAnAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
       return false;
    }

    private void btnManageCustomerVehicleClicked(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/CustomerManagementForm.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
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

}
