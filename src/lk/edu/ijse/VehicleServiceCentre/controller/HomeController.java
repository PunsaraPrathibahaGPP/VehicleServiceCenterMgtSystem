/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.edu.ijse.VehicleServiceCentre.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.edu.ijse.VehicleServiceCentre.dao.CrudUtil;
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
public class HomeController implements Initializable {

    @FXML
    private JFXTextField txtDateloader;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private BarChart<String,Double> AppointmentProgressBarChart;
    @FXML
    private PieChart vehicleServiceChart;
    @FXML
    private Label lblNoOfAppointments;
    ArrayList < String > sid = new ArrayList < String > ();
    ArrayList < Double > sFee = new ArrayList < > ();
    @FXML
    private LineChart<String,Double> chartReputation;
    private JFXTextField txtAppointmentNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
            showVehicleServiceProgress();
            lineChartAppoinmentProgress();
            loadNoOfAppointment();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void loadDate(){
        try {
            appointmentProgressAreaChart();
            Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    txtDateloader.setText(new SimpleDateFormat("YYYY-MM-dd" ).format(new Date()));
                    txtDateloader.setFocusTraversable(false);
                    txtDateloader.setDisable(true);
                    txtTime.setText(new SimpleDateFormat("hh:mm:ss a" ).format(new Date()));
                    txtTime.setFocusTraversable(false);
                    txtTime.setDisable(true);
                }
                
            }),new KeyFrame(Duration.seconds(1)));
            newTimeLine.setCycleCount(Animation.INDEFINITE);
            newTimeLine.play();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TxtAction(ActionEvent event) {
    }

    @FXML
    private void TimeAction(ActionEvent event) {
    }

    private void MouseExited(MouseEvent event) {
        ImageView source=(ImageView) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(1000),source);
        scaleTrandsition.setToX(1.0);
        scaleTrandsition.setToY(1.0);
        scaleTrandsition.play();
        source.setEffect(null); 
    }

    private void MouseEntered(MouseEvent event) {
        ImageView source= (ImageView) event.getSource();
        ScaleTransition scaleTrandsition=new ScaleTransition(Duration.millis(600),source);
        scaleTrandsition.setToX(1.1);
        scaleTrandsition.setToY(1.1);
        scaleTrandsition.play();
        source.setEffect(null);
    }
    private void loadNoOfAppointment() throws Exception{
        LocalDate date = LocalDate.now();
        String currentDate = date.toString();
        int count = 0;
        ResultSet rst;
            rst = CrudUtil.executeQuery("SELECT COUNT(*) FROM appointment WHERE App_date= ? ", currentDate);
            if (rst.next()) {
                count = rst.getInt("COUNT(*)");
            }
            lblNoOfAppointments.setText(""+count);
    }

    private void appointmentProgressAreaChart() throws Exception{
        ResultSet rst = CrudUtil.executeQuery("select Pay_Date,Tot_Ammount from payment ORDER BY Pay_ID desc");
                
        BarChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            while (rst.next()) {
                series.getData().add(new XYChart.Data<>("" + rst.getString("Pay_Date"), rst.getDouble("Tot_Ammount")));
            }
            AppointmentProgressBarChart.getData().add(series);
        } catch (Exception e) {
        }
    }
    
    private void showVehicleServiceProgress() throws Exception{
        String query = "select SID , S_Fee From service"; 
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();
        Connection connection;
        try {
          connection = DBConnection.getInstance().getConnection();
          ResultSet rst = connection.createStatement().executeQuery(query);
          while (rst.next()) {
            piechartdata.add(new PieChart.Data(rst.getString("SID"), rst.getDouble("S_Fee")));
            sid.add(rst.getString("SID"));
            sFee.add(rst.getDouble("S_Fee"));
          }
          vehicleServiceChart.getData().addAll(piechartdata);
        } catch (Exception e) {}
    }
    
    public void lineChartAppoinmentProgress() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select SID , S_Fee From service");
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            while (rst.next()) {

                series.getData().add(new XYChart.Data<>(rst.getString("SID"), rst.getDouble("S_Fee")));
            }
            chartReputation.getData().add(series);
        } catch (Exception e) {
        }
    }
}
