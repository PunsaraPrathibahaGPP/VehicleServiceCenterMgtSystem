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
public class VerifyProvidedPassword {
    public static boolean providePassword() throws InvalidKeySpecException{
        String providedPassword = "1234";
        String securePassword = "NZLz/E9xE0qDgOXto1Mm63nG6Zxc9bS6JiqCbSRFL4U=";
        String salt = "JdoSYGtWnCdrZ93RXD34aVGp8b4Mlm";
        boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
        if(passwordMatch) {
            return true;
        } else {
            return false;
        }
    }
}
