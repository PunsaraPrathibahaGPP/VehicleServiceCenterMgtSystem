/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import lk.edu.ijse.VehicleServiceCentre.other.Validation;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class LogInController implements Initializable {
    @FXML
    private JFXButton btnLogIn;
    @FXML
    private Label lblWelcome;
    @FXML
    private ImageView LoginImage;
    @FXML
    private ImageView lblExit;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lblPasswordError;
    @FXML
    private Label lblUserNameError;
    private boolean providePasswordCorrect;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void LoadDashBoard(ActionEvent event) {
        try {
            loadDashBoardUI();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadDashBoardUI() throws ClassNotFoundException{
        boolean userNameEmpty = Validation.textFieldNotEmpty(txtUserName,lblUserNameError, "Error! Username Field Can't be Empty");
        boolean passwordIsEmpty = Validation.isPasswordFieldnotEmpty(txtPassword, lblPasswordError, "Error! Password  Field Can't be Empty");
        String username, password;
        if (userNameEmpty && passwordIsEmpty) {
            try {
                username = txtUserName.getText();
                password = txtPassword.getText();
                Connection conn = DBConnection.getInstance().getConnection();
                Statement stm = conn.createStatement();
                ResultSet rst = stm.executeQuery("select * from userLogin where userName='" + username + "' and  password='" + password + "'");
                try {
                    lk.edu.ijse.VehicleServiceCentre.encription.ProtectUserPassword.protectPassword();
                    providePasswordCorrect = lk.edu.ijse.VehicleServiceCentre.encription.VerifyProvidedPassword.providePassword();
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rst.next()) {
                    if (providePasswordCorrect) {
                        try {
                            Parent parentRoot = FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/DashBoardView.fxml"));
                            Scene tempScene = new Scene(parentRoot);
                            this.root.getChildren().clear();
                            Stage stage = (Stage) this.root.getScene().getWindow();
                            stage.setScene(tempScene);
                            stage.setResizable(false);
                            stage.centerOnScreen();
                            stage.show();

                            TranslateTransition rempTranslate = new TranslateTransition(Duration.millis(1800), tempScene.getRoot());
                            rempTranslate.setFromY(-tempScene.getWidth());
                            rempTranslate.setToY(0);
                            rempTranslate.play();
                        } catch (IOException ex) {
                            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Inavild username or password\nPlease Check Your UserName and Password", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void SystemExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void MouseExitedWelcome(MouseEvent event) {
        Label source=(Label) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(1800),source);
        scaleTrandsition.setToX(1.0);
        scaleTrandsition.setToY(1.0);
        scaleTrandsition.play();
        source.setEffect(null);
    }

    @FXML
    private void MouseEnteredWelcome(MouseEvent event) {
        Label source=(Label) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(1800),source);
        scaleTrandsition.setToX(1.2);
        scaleTrandsition.setToY(1.2);
        scaleTrandsition.play();
        source.setEffect(null);
    }

    @FXML
    private void MouseExitedImage(MouseEvent event) {
        ImageView source= (ImageView) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(1200),source);
        scaleTrandsition.setToX(1.0);
        scaleTrandsition.setToY(1.0);
        scaleTrandsition.play();
        source.setEffect(null);
    }

    @FXML
    private void MouseEnteredImage(MouseEvent event) {
        ImageView source= (ImageView) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(1200),source);
        scaleTrandsition.setToX(1.1);
        scaleTrandsition.setToY(1.1);
        scaleTrandsition.play();
        source.setEffect(null);
    }

    @FXML
    private void lblExitClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    private void txtPasswordOnAction(ActionEvent event) throws ClassNotFoundException {
        loadDashBoardUI();
    }
    
}
