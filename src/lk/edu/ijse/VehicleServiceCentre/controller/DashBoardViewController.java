/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.vehicleServiceCentre.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DashBoardViewController implements Initializable {

    @FXML
    private Label lblHome;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lblMakeAnPayment;
    @FXML
    private Label lblMakePayment;
    @FXML
    private Label lblInteriorBodyRepair;
    @FXML
    private Label lblCarWashService;
    @FXML
    private Label lblLubricantService;
    @FXML
    private Label lblLogOut;
    @FXML
    private Label lblViewReports;
    @FXML
    private Label lblDateTimeDisplay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane ref = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/Home.fxml"));
            rootPane.getChildren().add(ref); 
        } catch (IOException ex) {
            Logger.getLogger(DashBoardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void MouseExited(MouseEvent event) {
        Label source=(Label) event.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(500),source);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
        source.setEffect(null);
    }

    @FXML
    private void MouseEntered(MouseEvent event) {
        Label source=(Label) event.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(500),source);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
        source.setEffect(null);
        loadDateAndTime();
    }
    private void loadDateAndTime(){
         Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                lblDateTimeDisplay.setText(new SimpleDateFormat("hh:mm:ss a" ).format(new Date()));
                lblDateTimeDisplay.setFocusTraversable(false);
                lblDateTimeDisplay.setDisable(true);
                lblDateTimeDisplay.setAlignment(Pos.CENTER);
            }
            
        }),new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

    @FXML
    private void HomeClicked(MouseEvent event) {
        try {
            this.rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/Home.fxml")); 
            this.rootPane.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashBoardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AddNewCustomerClicked(MouseEvent event) throws IOException {
            this.rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/AddNewCustomerForm.fxml"));
            this.rootPane.getChildren().add(pane);
    }

    @FXML
    private void MakeAnAppoinmentClicked(MouseEvent event) throws IOException {
            this.rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakeAnAppointmentForm.fxml"));
            this.rootPane.getChildren().add(pane);
    }

    private void lblSearchCustomerClicked(MouseEvent event) throws IOException {
            this.rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/SearchCustomer.fxml"));
            this.rootPane.getChildren().add(pane);
    }

    @FXML
    private void lblMakePaymentClicked(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/MakePayment.fxml"));
        this.rootPane.getChildren().add(pane);
    }

    private void ViewDailyProgress(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/ViewDailyProgress.fxml"));
        this.rootPane.getChildren().add(pane);
    }

    @FXML
    private void lblInterirBodyRepairMouseClicked(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/VehicleRepairForm.fxml"));
        this.rootPane.getChildren().add(pane);
    }

    @FXML
    private void lblInterirCarWashServiceMouseClicked(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/VehicleCleanForm.fxml"));
        this.rootPane.getChildren().add(pane);
    }
    
    @FXML
    private void lblLubricantServiceClicked(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/LubricentService.fxml"));
        this.rootPane.getChildren().add(pane);
    }

    @FXML
    private void lblLogOutClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void lblViewReportsClicked(MouseEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        AnchorPane pane=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/ViewReports.fxml"));
        this.rootPane.getChildren().add(pane);
    }
    
}
