/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.other;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;

/**
 *
 * @author ACER
 */
public class Validation {
    public static boolean nameValidation(String name){
        if(name.trim().isEmpty()){
            return false;
        }
        if(!name.matches("[A-Z]{1}[A-z .]+")){
            return false;
        }
        return true;
    }
    public static boolean contactNoValidation(String number){
        if(number.trim().isEmpty()){
            return false;
        }
        if(!number.matches("[0]{1}[0-9]{9}")){
            return false;
        }
        return true;
    }
    public static boolean addressValidation(String adress){
        if(adress.trim().isEmpty()){
            return false;
        }
        if(!adress.matches("[A-z ,' 0-9]+")){
            return false;
        }
        return true;
    }
    public static boolean isNotEmptyValidation(String text){
        if(!text.trim().isEmpty()){
            return true;
        }
        return false;
    }
    public static boolean doubleValueValidation(String value){
        if(value.trim().isEmpty()){
            return false;
        }
        if(!value.matches("(\\d{5}|\\d{6})+(\\.\\d{2}|\\.\\d{1})?")){
            return false;
        }
        return true;
    }
    public static boolean serviceFeeValidation(String value){
        if(value.trim().isEmpty()){
            return false;
        }
        if(!value.matches("(\\d{3}|\\d{4}|\\d{5})+(\\.\\d{2}|\\.\\d{1})?")){
            return false;
        }
        return true;
    }
    public static boolean intValueValidation(String value){
        if(value.trim().isEmpty()){
            return false;
        }
        if(!value.matches("[0-9]+")){
            return false;
        }
        return true;
    }
    public static boolean nicValidation(String nic){
        if(nic.trim().isEmpty()){
            return false;
        }
        if(!nic.matches("[0-9]{9}[vVwW]")){
            return false;
        }
        return true;
    }
    public static boolean passportNoValidation(String id){
        if(id.trim().isEmpty()){
            return false;
        }
        if(!id.matches("[A-z]{1}[0-9]{8}")){
            return false;
        }
        return true;
    }
    public static boolean vehicleNoValidation(String vehicleNo){
        if(vehicleNo.trim().isEmpty()){
            return false;
        }
        if(!vehicleNo.matches("[A-z]{2}[0-9]{4}")){
            return false;
        }
        return true;
    }
    public static boolean dateValidation(String dateValidation){
        if(dateValidation.trim().isEmpty()){
            return false;
        }
        if (!dateValidation.matches("\\d{4}([-]{1}|[:]{1}|[/]{1}|[.]{1})\\d{2}([-]{1}|[:]{1}|[/]{1}|[.]{1})\\d{2}")){
            return false;
        }
        return true;
    }
    public static boolean timeValidation(String timeValidation){
        if(timeValidation.trim().isEmpty()){
            return false;
        }
        if (!timeValidation.matches("\\d{1}([:]{1}|[.]{1})\\d{2}([AM]{2}|[PM]{2})")){
            return false;
        }
        return true;
    }
    
    public static boolean textFieldNotEmpty(JFXTextField txtfield) {
        boolean b = false;
        if (txtfield.getText().length() != 0 || !txtfield.getText().isEmpty()) {
            b = true;
        }
        return b;
    }
    
    public static boolean textFieldNotEmpty(JFXTextField txtfield, Label lb, String error_Message) {

        boolean b = true;
        String msg = null;
        txtfield.getStyleClass().remove("error");
        if (!textFieldNotEmpty(txtfield)) {

            b = false;
            msg = error_Message;
            txtfield.getStyleClass().add("error");
        }
        lb.setText(msg);

        return b;
    }
    public static boolean isPasswordFieldnotEmpty(JFXPasswordField passfield) {
        boolean b = false;
        if (passfield.getText().length() != 0 || !passfield.getText().isEmpty()) {
            b = true;
        }
        return b;
    }

    public static boolean isPasswordFieldnotEmpty(JFXPasswordField passfield, Label lb, String error_Message) {

        boolean b = true;
        String msg = null;
        passfield.getStyleClass().remove("error");
        if (!isPasswordFieldnotEmpty(passfield)) {

            b = true;
            msg = error_Message;
            passfield.getStyleClass().add("error");
        }

        lb.setText(msg);
        return b;

    }
}
