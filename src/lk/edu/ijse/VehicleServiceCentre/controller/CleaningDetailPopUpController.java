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
import lk.edu.ijse.VehicleServiceCentre.business.custom.CleaningBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.MakePaymentBO;
import lk.edu.ijse.VehicleServiceCentre.model.CleaningDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class CleaningDetailPopUpController implements Initializable {

    @FXML
    private JFXTextField txtServiceFee;
    @FXML
    private JFXTextField txtCleaningID;
    @FXML
    private JFXComboBox<String> cmbTechnicianID;
    @FXML
    private JFXComboBox<String> cmbTechnicianName;
    @FXML
    private JFXComboBox<String> cmbTechnicianType;
    @FXML
    private JFXButton btnAddCleaningDetail;
    private MakePaymentBO makePaymentBO;
    private MakePaymentBO makepaymentBO;
    private double totalAmmount;
    private CleaningBO cleaningBO;
    @FXML
    private ImageView lblClose;
    @FXML
    private AnchorPane cleaningPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCleaningID();
            loadTechnicianID();
            loadTechnicianName();
            loadCleanType();
            loadMakePaymentBO();
            loadCleaningBO();
        } catch (Exception ex) {
            Logger.getLogger(CleaningDetailPopUpController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void loadCleaningBO() throws Exception{
        cleaningBO=(CleaningBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.CLEANING);
    }
    private void loadMakePaymentBO() throws Exception{
        makePaymentBO=(MakePaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.MAKEPAYMENT);
    }
    private void loadTechnicianID(){
        String[] technicianIDs = {"T001", "T002","T003","T004", "T005","T006","T007", "T008","T009","T0010"};
        cmbTechnicianID.getItems().clear();
        for (String technicianID : technicianIDs) {
            cmbTechnicianID.getItems().add(technicianID);
        }
    }
    private void loadTechnicianName(){
        String[] technicianNames = {"Saman", "Kalum","Priyantha","Raveen", "Dasun","Janith","Lakmal", "Wipula","Indika","Pawan"};
        cmbTechnicianName.getItems().clear();
        for (String technicianName : technicianNames) {
            cmbTechnicianName.getItems().add(technicianName);
        }
    }

    private void loadCleanType() {
        String[] cleanTypes = {"InteriorCleaning", "FullBodyCleaning", "Other"};
        cmbTechnicianType.getItems().clear();
        for (String cleantype : cleanTypes) {
            cmbTechnicianType.getItems().add(cleantype);
        }
    }

    @FXML
    private void btnAddCleaningDetailClicked(MouseEvent event){
        try {
            addCleaning();
        } catch (Exception ex) {
            Logger.getLogger(CleaningDetailPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addCleaning() throws Exception{
        String cleaningID=txtCleaningID.getText();
        String technicianID=cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName=cmbTechnicianName.getSelectionModel().getSelectedItem();
        String cleaningType=cmbTechnicianType.getSelectionModel().getSelectedItem();
        double serviceFee=Double.parseDouble(txtServiceFee.getText());
        CleaningDTO cleaningDTO=new CleaningDTO(cleaningID,technicianID,technicianName,cleaningType,serviceFee);
        boolean isAddedCleaning = cleaningBO.addCleaning(cleaningDTO);
        if(isAddedCleaning){
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Successfully Added",ButtonType.OK);
            alert.showAndWait();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Added Unsuccessfully",ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    private void txtServiceFeeOnAction(ActionEvent event) throws Exception {
        boolean serviceFeeValidation = Validation.serviceFeeValidation(txtServiceFee.getText());
        if(serviceFeeValidation){
            addCleaning();
            this.totalAmmount = Double.parseDouble(txtServiceFee.getText());
            makePaymentBO.getCleaningPrice(totalAmmount);
            loadCleaningID();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Please Enter valid Service Fee",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void cmbTechnicianIDOnAction(ActionEvent event) {
        cmbTechnicianName.requestFocus();
    }

    @FXML
    private void cmbTechnicianNameOnAction(ActionEvent event) {
        cmbTechnicianType.requestFocus();
    }

    @FXML
    private void cmbTechnicianTypeOnAction(ActionEvent event) {
        txtServiceFee.requestFocus();
    }

    @FXML
    private void lblCloseClicked(MouseEvent event) {
       Stage stage = (Stage) lblClose.getScene().getWindow();
       stage.close();
    }
}
