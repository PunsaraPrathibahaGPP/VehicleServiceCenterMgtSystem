/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.encription;

import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author ACER
 */
public class ProtectUserPassword {
    public static void protectPassword() throws InvalidKeySpecException{
        String myPassword = "1234";
        String salt = PasswordUtils.getSalt(30);
        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
    }
}
