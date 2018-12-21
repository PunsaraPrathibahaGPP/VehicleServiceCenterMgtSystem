/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.other;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class CustomMethod {

    public static String value;
      public static String value2;
        public static String value3;
          public static String value4;
            public static String value5;
              public static String value6;
                public static String value7;
          

    public static void modelSeet(Parent modalWindow, Event event) throws Exception {
        Window theStage = ((Node) event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(500), modalWindow);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Stage dialog = new Stage();
        Scene scene = new Scene(modalWindow);

        dialog.setScene(scene);
        dialog.initOwner(theStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.centerOnScreen();
        dialog.showAndWait();
        scene.setRoot(new Parent() {
        });
    }

}
