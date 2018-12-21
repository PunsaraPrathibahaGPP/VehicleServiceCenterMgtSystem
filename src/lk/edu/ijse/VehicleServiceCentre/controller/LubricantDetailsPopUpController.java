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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.edu.ijse.VehicleServiceCentre.business.BOFactory;
import lk.edu.ijse.VehicleServiceCentre.business.custom.LubricantBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.MakePaymentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.PaymentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.MakePaymentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.model.LubricantDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class LubricantDetailsPopUpController implements Initializable {

    @FXML
    private JFXTextField txtServiceFee;
    @FXML
    private JFXTextField txtLubricantID;
    @FXML
    private JFXComboBox<String> cmbTechnicianID;
    @FXML
    private JFXComboBox<String> cmbTechnicianName;
    @FXML
    private JFXComboBox<String> cmbLubricantType;
    @FXML
    private JFXTextField txtOtherFee;
    @FXML
    private JFXTextField txtTotalFee;
    @FXML
    private JFXButton btnAddLubricant;
    
    private MakePaymentBO makePaymentBO;
    private LubricantBO lubricantBO;
    private PaymentBO paymentBO;
    private double totalFee;
    @FXML
    private ImageView lblClose;
    @FXML
    private AnchorPane lubricantPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTechnicianID();
            loadTechnicianName();
            loadLubricantType();
            loadLubricantBO();
            loadPaymentBO();
            makePaymentBO();
            loadLubricantID();
        } catch (Exception ex) {
            Logger.getLogger(LubricantDetailsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private void loadLubricantID(){
        try {
            String newID;
            newID = IDGenerator.getNewID("lubricant", "Lub_ID", "L");
            txtLubricantID.setText(newID);
            txtLubricantID.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(LubricantDetailsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void makePaymentBO() throws Exception{
        makePaymentBO=(MakePaymentBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.MAKEPAYMENT);
    }
    private void loadPaymentBO() throws Exception{
        paymentBO=(PaymentBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.PAYMENT);
    }
    private void loadLubricantBO() throws Exception{
        lubricantBO=(LubricantBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.LUBRICANT);
    }
    private void loadTechnicianID(){
        String[] technicianID = {"T001", "T002","T003","T004", "T005","T006","T007", "T008","T009","T0010"};
        cmbTechnicianID.getItems().clear();
        for (String cleantype : technicianID) {
            cmbTechnicianID.getItems().add(cleantype);
        }
    }
    private void loadTechnicianName(){
        String[] technicianName = {"Saman", "Kalum","Priyantha","Raveen", "Dasun","Janith","Lakmal", "Wipula","Indika","Pawan"};
        cmbTechnicianName.getItems().clear();
        for (String lubricantType : technicianName) {
            cmbTechnicianName.getItems().add(lubricantType);
        }
    }
    
    private void loadLubricantType() {
        String[] lubricantTypes = {"EngineOil", "BreakOil","Other"};
        cmbLubricantType.getItems().clear();
        for (String lubricantType : lubricantTypes) {
            cmbLubricantType.getItems().add(lubricantType);
        }
    }

    @FXML
    private void btnAddLubricantClicked(MouseEvent event){
        try {
            addLubricant();
            loadLubricantID();
        } catch (Exception ex) {
            Logger.getLogger(LubricantDetailsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addLubricant() throws Exception{
        String lubricatID=txtLubricantID.getText();
        String technicianID=cmbTechnicianID.getSelectionModel().getSelectedItem();
        String TechnicianName=cmbTechnicianName.getSelectionModel().getSelectedItem();
        String lubricantType=cmbLubricantType.getSelectionModel().getSelectedItem();
        double serviceFee=Double.parseDouble(txtServiceFee.getText());
        double otherFee=Double.parseDouble(txtOtherFee.getText());
        double totalFee=Double.parseDouble(txtTotalFee.getText());
        LubricantDTO lubricantDTo=new LubricantDTO(lubricatID,technicianID,TechnicianName,lubricantType,serviceFee,otherFee,totalFee);
        boolean addLubricant = lubricantBO.addLubricant(lubricantDTo);
        if(addLubricant){
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Added Lubricant Success", ButtonType.OK);
            alert.showAndWait();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Added Lubricant Not Success", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
    private void CalculateTotalAmmount(){
        double otherFee=Double.parseDouble(txtOtherFee.getText());
        double serviceFee=Double.parseDouble(txtServiceFee.getText());
        this.totalFee=serviceFee+otherFee;
        txtTotalFee.setText(Double.toString(totalFee));
    }

    @FXML
    private void txtOtherFeeOnAction(ActionEvent event) throws Exception {
        boolean serviceValidation = Validation.serviceFeeValidation(txtOtherFee.getText());
        if(serviceValidation){
            CalculateTotalAmmount();
            txtOtherFee.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtServiceFeeOnAction(ActionEvent event) {
        boolean serviceValidation = Validation.serviceFeeValidation(txtServiceFee.getText());
        if(serviceValidation){
            txtOtherFee.requestFocus();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtTotalFeeOnaction(ActionEvent event) throws Exception {
        boolean totalFeeValidation = Validation.serviceFeeValidation(txtTotalFee.getText());
        if(totalFeeValidation){
            addLubricant();
            makePaymentBO.getLubricantPrice(totalFee);
            loadLubricantID();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void cmbTechnicianIDOnAction(ActionEvent event) {
        cmbTechnicianName.requestFocus();
    }

    @FXML
    private void cmbTechnicianNameOnAction(ActionEvent event) {
        cmbLubricantType.requestFocus();
    }

    @FXML
    private void cmbLubricantTypeOnAction(ActionEvent event) {
        txtServiceFee.requestFocus();
    }

    @FXML
    private void lblCloseClicked(MouseEvent event) {
       Stage stage = (Stage) lblClose.getScene().getWindow();
       stage.close();
    }
}
