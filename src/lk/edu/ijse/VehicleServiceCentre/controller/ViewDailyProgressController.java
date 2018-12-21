/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.edu.ijse.VehicleServiceCentre.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import lk.edu.ijse.VehicleServiceCentre.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class ViewDailyProgressController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //loadJasperReport();
    }    
    private void loadJasperReport(){
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/viewBusinessProgress.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
                JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (JRException ex) {
                Logger.getLogger(ViewDailyProgressController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewDailyProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
