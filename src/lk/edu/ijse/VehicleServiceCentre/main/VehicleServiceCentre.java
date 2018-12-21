package lk.edu.ijse.VehicleServiceCentre.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class VehicleServiceCentre extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/edu/ijse/VehicleServiceCentre/view/LogIn.fxml"));
            Scene tempScene=new Scene(root);
            primaryStage.setScene(tempScene);
            primaryStage.setX(300);
            primaryStage.setY(110);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setResizable(false);
            primaryStage.show();

            FadeTransition tempTransition =new FadeTransition(Duration.millis(2800),root);
            tempTransition.setFromValue(0.0);
            tempTransition.setToValue(1.0);
            tempTransition.play();
        }catch (IOException ex) {
            Logger.getLogger(VehicleServiceCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
