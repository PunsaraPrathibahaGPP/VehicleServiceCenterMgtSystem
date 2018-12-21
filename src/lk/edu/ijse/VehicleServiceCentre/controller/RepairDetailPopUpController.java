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
import lk.edu.ijse.VehicleServiceCentre.business.custom.MakePaymentBO;
import lk.edu.ijse.VehicleServiceCentre.business.custom.RepairBO;
import lk.edu.ijse.VehicleServiceCentre.model.RepairDTO;
import lk.edu.ijse.VehicleServiceCentre.other.IDGenerator;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class RepairDetailPopUpController implements Initializable {

    @FXML
    private JFXTextField txtServiceFee;
    @FXML
    private JFXTextField txtRepairID;
    @FXML
    private JFXComboBox<String> cmbTechnicianID;
    @FXML
    private JFXComboBox<String> cmbTechnicianName;
    @FXML
    private JFXComboBox<String> cmbRepairType;
    @FXML
    private JFXTextField txtOtherFee;
    @FXML
    private JFXTextField txtTotalFee;
    
    private RepairBO repairBo;
    @FXML
    private JFXButton btnAddRepair;
    private double totalAmmount;
    private MakePaymentBO makepaymentBO;
    @FXML
    private ImageView lblClose;
    @FXML
    private AnchorPane repairPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadRepairID();
            loadTechnicianID();
            loadTechnicianName();
            loadReapirType();
            loadMakePaymentBO();
            loadRepairBO();
        } catch (Exception ex) {
            Logger.getLogger(RepairDetailPopUpController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void loadRepairBO() throws Exception{
        repairBo=(RepairBO)BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.REPAIR);
    }
    private void loadMakePaymentBO() throws Exception{
        makepaymentBO=(MakePaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOTypes.MAKEPAYMENT);
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
    
    private void loadReapirType() {
        String[] repairTypes = {"EngineRepair","BreakBindingRepair","ElectricalRepair","ClutchPlateRepair","Other"};
        cmbRepairType.getItems().clear();
        for (String repairType : repairTypes) {
            cmbRepairType.getItems().add(repairType);
        }
    }

    @FXML
    private void btnAddRepairClicked(MouseEvent event){
        try {
            addRepair();
        } catch (Exception ex) {
            Logger.getLogger(RepairDetailPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void CalculateTotalAmmount(){
        double otherFee=Double.parseDouble(txtOtherFee.getText());
        double serviceFee=Double.parseDouble(txtServiceFee.getText());
        this.totalAmmount=serviceFee+otherFee;
        txtTotalFee.setText(Double.toString(totalAmmount));
    }
    private void addRepair() throws Exception{
        String repairID=txtRepairID.getText();
        String technicianID=cmbTechnicianID.getSelectionModel().getSelectedItem();
        String technicianName=cmbTechnicianName.getSelectionModel().getSelectedItem();
        String repairType=cmbRepairType.getSelectionModel().getSelectedItem();
        double serviceFee=Double.parseDouble(txtServiceFee.getText());
        double otherFee=Double.parseDouble(txtOtherFee.getText());
        double totalFee=Double.parseDouble(txtTotalFee.getText());
        RepairDTO repairDTO=new RepairDTO(repairID, technicianID,technicianName, repairType, totalFee, otherFee, totalFee);
        boolean isAddedRepair = repairBo.addRepair(repairDTO);
        if(isAddedRepair){
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Added Success",ButtonType.OK);
            alert.showAndWait();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Added Unsuccess",ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtOtherFeeOnAction(ActionEvent event) {
        boolean otherFeeValidation =  Validation.serviceFeeValidation(txtOtherFee.getText());
        if(otherFeeValidation){
            txtTotalFee.requestFocus();
            CalculateTotalAmmount();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtServiceFeeOnAction(ActionEvent event) {
        boolean serviceFeeValidation =  Validation.serviceFeeValidation(txtServiceFee.getText());
        if(serviceFeeValidation){
            txtOtherFee.requestFocus();
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
        cmbRepairType.requestFocus();
    }

    @FXML
    private void cmbRepairTypeOnAction(ActionEvent event) {
        txtServiceFee.requestFocus();
    }

    @FXML
    private void txtTotalFeeOnAction(ActionEvent event) {
        boolean totalFeeValidation = Validation.serviceFeeValidation(txtTotalFee.getText());
        if(totalFeeValidation){
            try {
                addRepair();
                makepaymentBO.getRepairPrice(totalAmmount);
                loadRepairID();
            } catch (Exception ex) {
                Logger.getLogger(RepairDetailPopUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter valid Service Fee", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void lblCloseClicked(MouseEvent event) {
       Stage stage = (Stage) lblClose.getScene().getWindow();
       stage.close();
    }
    
}
