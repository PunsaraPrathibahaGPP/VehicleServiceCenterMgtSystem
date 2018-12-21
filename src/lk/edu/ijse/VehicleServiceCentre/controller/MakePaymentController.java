/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.SuperBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.AppointmentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.CustomerBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.LubricantBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.MakePaymentBO;
import lk.edu.ijse.VehicleServiceCentre.model.CustomerDTO;
import lk.edu.ijse.VehicleServiceCentre.model.PaymentDTO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.PaymentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.ServiceDetailBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.MakePaymentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
import lk.edu.ijse.VehicleServiceCentre.model.AppointmentDTO;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDetailDTO;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDTO;
import lk.edu.ijse.VehicleServiceCentre.model.ServiceDetailDTO;
import lk.edu.ijse.VehicleServiceCentre.other.CustomMethod;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MakePaymentController implements Initializable {

    @FXML
    private JFXButton btnMakePayment;
    @FXML
    private JFXTextField txtNicNo;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtCustomerAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtPaymentID;
    @FXML
    private JFXTextField txtServiceID;
    @FXML
    private JFXTextField txtPaymentDate;
    @FXML
    private JFXTextField txtPaymentTime;
    @FXML
    private TableView<AppointmentDTO> tblViewAll;
    ObservableList<AppointmentDTO> tblData = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXTextField txtCustomerID;
    @FXML
    private AnchorPane txtRepairFee;
    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXButton btnRepairDetails;
    @FXML
    private JFXButton btnLubricantDetails;
    @FXML
    private JFXButton btnCleaningDetails;
    @FXML
    private JFXButton btnRefresh;
    private MakePaymentBO makePaymentBo;
    private CustomerBO customerBo;
    private double lubricantPrice;
    private double repairPrice;
    private double cleaningPrice;
    private static double fullTotalAmmount;
    @FXML
    private JFXTextField txtTotalServiceAmmount;
    @FXML
    private JFXTextField txtAppointmentDate;
    @FXML
    private JFXTextField txtAppointmentTime;
    private static String nicNo;
    private static String enteredNicNO;
    private static String enteredVehicleNo;
    private static String vehicleNo;
    private boolean btnSearchClicked;
    private CustomerBO customerBO;
    private PaymentBO paymentBO;
    private MakePaymentBO makePaymentBO;
    private LubricantBO lubricantBO;
    private AppointmentBO apppintmentBO;
    @FXML
    private JFXButton btnClearAll;
    @FXML
    private JFXButton btnDeleteAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadCustomerBO();
            loadPaymentBO();
            loadPaymentDate();
            loadPaymentTime();
            loadLubricantBO();
            loadAppointedDateAndTime();
            loadCustomerDetail();
            loadPaymentIds();
            loadServiceIds();
            loadMakePaymentBO();
            loadAppointmentBO();
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadMakePaymentBO() throws Exception{
        makePaymentBo=(MakePaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.MAKEPAYMENT);
        customerBo=(CustomerBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    private void loadCustomerDetail() throws Exception {
//        tblViewAll.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
//        tblViewAll.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Veh_ID"));
//        tblViewAll.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("App_no"));
//        tblViewAll.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Curr_date"));
//        tblViewAll.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Curr_time"));
//        tblViewAll.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("App_date"));
//        tblViewAll.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("App_time"));
//        ArrayList<AppointmentDTO> appointmentDTO = apppintmentBO.getAllAppointment();
//        tblViewAll.setItems(FXCollections.observableArrayList(appointmentDTO));
//        tblViewAll.widthProperty().addListener(new ChangeListener<Number>(){
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                TableHeaderRow header = (TableHeaderRow) tblViewAll.lookup("TableHeaderRow");
//                header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                          header.setReordering(false);
//                    }
//                });
//            }
//        }); 
    }
      private void loadPaymentIds() {
        try {
            String newID;
            newID = IDGenerator.getNewID("payment", "Pay_ID", "P");
            txtPaymentID.setText(newID);
            txtPaymentID.setDisable(true);
            txtCustomerID.setDisable(true);
            txtVehicleID.setDisable(true);
            txtAppointmentDate.setDisable(true);;
            txtAppointmentTime.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadServiceIds() {
        try {
            String newID;
            newID = IDGenerator.getNewID("service", "SID", "S");

            txtServiceID.setText(newID);
            txtServiceID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void loadAppointedDateAndTime() {

    }

    private void loadAppointmentBO() throws Exception {
        apppintmentBO = (AppointmentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.APPOINTMENT);
    }

    private void loadPaymentBO() {
        try {
            paymentBO = (PaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.PAYMENT);
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLubricantBO() {
        try {
            lubricantBO = (LubricantBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.LUBRICANT);
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerBO() {
        try {
            customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CUSTOMER);
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPaymentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:dd");
        String nowDate = dateFormat.format(date);
        txtPaymentDate.setText(" " + nowDate);
        txtPaymentDate.setDisable(true);
    }
    private void loadNowPaymentTime(){
        try {
            Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    txtPaymentTime.setText(new SimpleDateFormat("hh:mm:ss" ).format(new Date()));
                    txtPaymentTime.setFocusTraversable(false);
                    txtPaymentTime.setDisable(true);
                }
                
            }),new KeyFrame(Duration.seconds(1)));
            newTimeLine.setCycleCount(Animation.INDEFINITE);
            newTimeLine.play();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPaymentTime() {
        loadNowPaymentTime();
    }

    private PaymentDTO paymentDetails() {
        String paymentID = txtPaymentID.getText();
        String paymentDate = txtPaymentDate.getText();
        String paymentTime = txtPaymentTime.getText();
        double totalAmmount = Double.parseDouble(txtTotalServiceAmmount.getText());
        return new PaymentDTO(paymentID, paymentDate, paymentTime, totalAmmount);
    }

    private ServiceDTO services() {
        String paymentID = txtPaymentID.getText();
        String serviceID = txtServiceID.getText();
//        long inquiryDate = 16322; // 09/09/2014
//        long appointedDate = 16329; // 09/16/2014
//        int  days  = (int) Math.abs(date1 - date2);
        double serviceFee = Double.parseDouble(txtTotalServiceAmmount.getText());
        ServiceDTO serviceDTO = new ServiceDTO(paymentID, serviceID, "2 Days", serviceFee);
        return serviceDTO;
    }

    private ServiceDetailDTO serviceDetails() {
        String vehicleID = txtVehicleID.getText();
        String serviceID = txtServiceID.getText();
        ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO(vehicleID, serviceID);
        return serviceDetailDTO;
    }

    private CleaningDetailDTO cleaningDetails() {
        return null;
    }
    
    @FXML
    private void btnMakePaymentClicked(MouseEvent event) throws Exception {
        makePaymentBO = new MakePaymentBOImpl();
        boolean isAdded = makePaymentBO.addNewPayment(paymentDetails(), services());
        if (isAdded) {
            loadServiceIds();
            loadPaymentIds();
            fullTotalAmmount=0;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment Details Added Successfully", ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Payment Details Added Unccessfully", ButtonType.OK);
            alert.showAndWait();
            fullTotalAmmount=0;
        }
    }

    @FXML
    private void btnSearchClicked(MouseEvent event) throws Exception {
        CustomerDTO searchCustomer = customerBO.searchCustomer(txtNicNo.getText());
        txtCustomerID.setText(searchCustomer.getCust_Id());
        txtNicNo.setText(searchCustomer.getNIC_NO());
        txtFName.setText(searchCustomer.getF_Name());
        txtLName.setText(searchCustomer.getL_Name());
        txtCustomerAddress.setText(searchCustomer.getCust_Address());
        txtSalary.setText("" + searchCustomer.getCust_Salary());
        txtMobileNo.setText("" + searchCustomer.getCust_Tel());
    }

    @FXML
    private void btnDeleteClicked(MouseEvent event) throws Exception {
        boolean isDeleted = customerBO.deleteCustomer(txtNicNo.getText());
        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Deleted Not Successfully", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void btnRepairDetailsClicked(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/RepairDetailPopUp.fxml"));
        Scene tempScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(tempScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        TranslateTransition rempTranslate = new TranslateTransition(Duration.millis(1800), tempScene.getRoot());
        rempTranslate.setFromX(tempScene.getWidth());
        rempTranslate.setToX(0);
        rempTranslate.play();
    }

    @FXML
    private void btnLubricantDetailsClicked(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/LubricantDetailsPopUp.fxml"));
        Scene tempScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(tempScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        TranslateTransition rempTranslate = new TranslateTransition(Duration.millis(1800), tempScene.getRoot());
        rempTranslate.setFromX(tempScene.getWidth());
        rempTranslate.setToX(0);
        rempTranslate.play();
    }

    @FXML
    private void btnCleaningDetailsClicked(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/CleaningDetailPopUp.fxml"));
        Scene tempScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(tempScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
        TranslateTransition rempTranslate = new TranslateTransition(Duration.millis(1800), tempScene.getRoot());
        rempTranslate.setFromX(tempScene.getWidth());
        rempTranslate.setToX(0);
        rempTranslate.play();
    }

    @FXML
    private void onClickTable(MouseEvent event) {
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getCust_Id();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getF_Name();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getL_Name();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getNIC_NO();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getCust_Address();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getCust_Salary();
//        CustomMethod.value = tblViewAll.getSelectionModel().getSelectedItem().getCust_Tel();

    }

    @FXML
    private void btnLoadCustomerView(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/ViewCustomer.fxml"));
        CustomMethod.modelSeet(root, event);
        txtCustomerID.setText(CustomMethod.value);
        txtFName.setText(CustomMethod.value2);
        txtLName.setText(CustomMethod.value3);
        txtNicNo.setText(CustomMethod.value4);
        txtCustomerAddress.setText(CustomMethod.value5);
        txtSalary.setText(CustomMethod.value6);
        txtMobileNo.setText(CustomMethod.value7);
    }
    public void getLubricantPrice(double lubricantPrice)throws Exception{
        MakePaymentController.fullTotalAmmount = MakePaymentController.fullTotalAmmount + lubricantPrice;
    }
    public void getRepairPrice(double repairPrice){
        MakePaymentController.fullTotalAmmount=MakePaymentController.fullTotalAmmount+repairPrice;
    }
    public void getCleaningPrice(double cleaningPrice){
        MakePaymentController.fullTotalAmmount=MakePaymentController.fullTotalAmmount+cleaningPrice;
    }
    private void displayTotalAmmount(){
        txtTotalServiceAmmount.setText(""+fullTotalAmmount);
    }

    @FXML
    private void txtTotalServiceAmmountOnAction(ActionEvent event) {
        displayTotalAmmount();
    }
    private void confirmCustomerToSearch() throws Exception{
        String nicNO = txtNicNo.getText();
        if(nicNO!=null){
            CustomerDTO searchCustomer = customerBo.searchCustomer(txtNicNo.getText());
            if(searchCustomer != null){
                txtCustomerID.setText(searchCustomer.getCust_Id());
                txtFName.setText(searchCustomer.getF_Name());
                txtLName.setText(searchCustomer.getL_Name());
                txtNicNo.setText(searchCustomer.getNIC_NO());
                txtCustomerAddress.setText(searchCustomer.getCust_Address());
                txtSalary.setText(Double.toString(searchCustomer.getCust_Salary()));
                txtMobileNo.setText(Integer.toString(searchCustomer.getCust_Tel()));
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please Enter At Least NICNO or VehicleID\nto Access this Function",ButtonType.OK );
            alert.showAndWait();
        }
    }
    private void searchCustomer() throws Exception{
        CustomerDTO searchCustomerDTO = customerBO.searchCustomer(txtNicNo.getText());
        if(searchCustomerDTO!=null){
            txtCustomerID.setText(searchCustomerDTO.getCust_Id());
            txtFName.setText(searchCustomerDTO.getF_Name());
            txtLName.setText(searchCustomerDTO.getL_Name());
            txtCustomerAddress.setText(searchCustomerDTO.getCust_Address());
            txtSalary.setText("" + searchCustomerDTO.getCust_Salary());
            txtMobileNo.setText("" + searchCustomerDTO.getCust_Tel());
            btnSearchClicked=true;  
        }
    }
    private void searchAppointment(String customerID){
        try {
            AppointmentDTO searchCustomerAppointment = apppintmentBO.searchCustomerAppointment(customerID);
            if(searchCustomerAppointment!=null){
                System.out.println("In the Appointment");
                txtAppointmentDate.setText(searchCustomerAppointment.getApp_date());;
                txtAppointmentTime.setText(searchCustomerAppointment.getApp_time());
                txtVehicleID.setText(searchCustomerAppointment.getVeh_ID());
            }
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
                txtFName.requestFocus();
                while (rst.next()) {
                    String compareTo = rst.getString("Nic_No");
                    if(enteredNicNO.equals(compareTo )){
                        searchCustomer();
                        txtFName.requestFocus();
                        break;
                    }else {
                        txtFName.requestFocus();
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
    private void txtFNameOnAction(ActionEvent event) {
        boolean nameValidation = Validation.nameValidation(txtFName.getText());
        if(nameValidation){
            txtLName.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid First Name", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtCustomerAddressOnAction(ActionEvent event) {
        boolean addressValidation = Validation.addressValidation(txtCustomerAddress.getText());
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
            txtTotalServiceAmmount.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Mobile Number", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtLNameOnAction(ActionEvent event) {
        boolean nameValidation = Validation.nameValidation(txtLName.getText());
        if(nameValidation){
            txtCustomerAddress.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter a Valid Last Name", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void btnDeleteOnAction(ActionEvent event){
        try {
        } catch (Exception ex) {
            Logger.getLogger(MakePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearAll(){
        txtNicNo.clear();
        txtFName.clear();
        txtLName.clear();
        txtCustomerAddress.clear();
        txtSalary.clear();
        txtMobileNo.clear();
    }
    public void loadData(CustomerDTO customerDTO,AppointmentDTO appointmentDTO ){
        txtAppointmentDate.setText(appointmentDTO.getApp_date());
        txtAppointmentTime.setText(appointmentDTO.getApp_time());
        txtVehicleID.setText(appointmentDTO.getVeh_ID());
        txtNicNo.setText(customerDTO.getNIC_NO());
        txtFName.setText(customerDTO.getF_Name());
        txtLName.setText(customerDTO.getL_Name());
        txtCustomerAddress.setText(customerDTO.getCust_Address());
        txtSalary.setText(""+customerDTO.getCust_Salary());
        txtMobileNo.setText(""+customerDTO.getCust_Tel());
        txtCustomerID.setText(customerDTO.getCust_Id());
    }

    @FXML
    private void btnClearAllOnAction(ActionEvent event) {
        clearAll();
    }

    @FXML
    private void btnDeleteAllOnAction(ActionEvent event) {
        //boolean deletePayment = paymentBO.deletePayment(txtPaymentID.getText());
        //boolean isDeleted = customerBO.deleteCustomer(txtNicNo.getText());
        
    }
}
