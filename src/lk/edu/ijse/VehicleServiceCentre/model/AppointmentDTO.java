/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.model;

/**
 *
 * @author ACER
 */
public class AppointmentDTO extends SuperDTO{
    private String Cust_ID;
    private String Veh_ID;
    private String App_no;
    private String Curr_date;
    private String Curr_time;
    private String App_date;
    private String App_time;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String Cust_ID, String Veh_ID, String App_no, String Curr_date, String Curr_time, String App_date, String App_time) {
        this.Cust_ID = Cust_ID;
        this.Veh_ID = Veh_ID;
        this.App_no = App_no;
        this.Curr_date = Curr_date;
        this.Curr_time = Curr_time;
        this.App_date = App_date;
        this.App_time = App_time;
    }

    /**
     * @return the Cust_ID
     */
    public String getCust_ID() {
        return Cust_ID;
    }

    /**
     * @param Cust_ID the Cust_ID to set
     */
    public void setCust_ID(String Cust_ID) {
        this.Cust_ID = Cust_ID;
    }

    /**
     * @return the Veh_ID
     */
    public String getVeh_ID() {
        return Veh_ID;
    }

    /**
     * @param Veh_ID the Veh_ID to set
     */
    public void setVeh_ID(String Veh_ID) {
        this.Veh_ID = Veh_ID;
    }

    /**
     * @return the App_no
     */
    public String getApp_no() {
        return App_no;
    }

    /**
     * @param App_no the App_no to set
     */
    public void setApp_no(String App_no) {
        this.App_no = App_no;
    }

    /**
     * @return the Curr_date
     */
    public String getCurr_date() {
        return Curr_date;
    }

    /**
     * @param Curr_date the Curr_date to set
     */
    public void setCurr_date(String Curr_date) {
        this.Curr_date = Curr_date;
    }

    /**
     * @return the Curr_time
     */
    public String getCurr_time() {
        return Curr_time;
    }

    /**
     * @param Curr_time the Curr_time to set
     */
    public void setCurr_time(String Curr_time) {
        this.Curr_time = Curr_time;
    }

    /**
     * @return the App_date
     */
    public String getApp_date() {
        return App_date;
    }

    /**
     * @param App_date the App_date to set
     */
    public void setApp_date(String App_date) {
        this.App_date = App_date;
    }

    /**
     * @return the App_time
     */
    public String getApp_time() {
        return App_time;
    }

    /**
     * @param App_time the App_time to set
     */
    public void setApp_time(String App_time) {
        this.App_time = App_time;
    }

    
}
