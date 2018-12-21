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
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
public class ViewReportsController implements Initializable {
    @FXML
    private Pane lblVehiclereport;
    @FXML
    private Pane lblAppointmentReport;
    @FXML
    private Pane lblBusinessProgress;
    @FXML
    private Pane lblServiceDetail;
    @FXML
    private Pane lblCustomerdetail;
    @FXML
    private Pane lblRepairdetailReport;
    @FXML
    private Pane lblLubricantdetail;
    @FXML
    private Pane lblCleaningdetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lblMouseExited(MouseEvent event) {
        Pane source=(Pane)event.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(1200),source);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
        source.setEffect(null);
    }

    @FXML
    private void lblMouseEntered(MouseEvent event) {
        Pane source=(Pane)event.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(1200),source);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
        source.setEffect(null);
    }

    @FXML
    private void lblVehiclereportClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/viewAllVehicle.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblAppointmentReportClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/viewAllappointmentReports.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblBusinessProgressClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/BusinessProgressReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblServiceDetailClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/serviceDetailReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblCustomerdetailClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/CustomerDetailReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblRepairdetailReportClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/repairDetailReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblLubricantdetailClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/lubricantDetailReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void lblCleaningdetailClicked(MouseEvent event) {
        try {
            InputStream stream=this.getClass().getResourceAsStream("/lk/edu/ijse/VehicleServiceCentre/reports/CleaningDetailReport.jasper");
            HashMap map=new HashMap();
            //map.put("name", cusID.getText());
            Connection conn=DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(stream,map, conn);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
