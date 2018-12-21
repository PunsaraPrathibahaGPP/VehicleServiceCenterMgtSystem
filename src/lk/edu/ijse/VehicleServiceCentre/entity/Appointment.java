package lk.edu.ijse.VehicleServiceCentre.entity;

import lk.edu.ijse.VehicleServiceCentre.model.SuperDTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class Appointment{
    private String Cust_ID_FK;
    private String Veh_ID_FK;
    private String App_no_PK;
    private String Curr_date;
    private String Curr_time;
    private String App_date;
    private String App_time;

    public Appointment() {
    }

    public Appointment(String Cust_ID_FK, String Veh_ID_FK, String App_no_PK, String Curr_date, String Curr_time, String App_date, String App_time) {
        this.Cust_ID_FK = Cust_ID_FK;
        this.Veh_ID_FK = Veh_ID_FK;
        this.App_no_PK = App_no_PK;
        this.Curr_date = Curr_date;
        this.Curr_time = Curr_time;
        this.App_date = App_date;
        this.App_time = App_time;
    }

    /**
     * @return the Cust_ID_FK
     */
    public String getCust_ID_FK() {
        return Cust_ID_FK;
    }

    /**
     * @param Cust_ID_FK the Cust_ID_FK to set
     */
    public void setCust_ID_FK(String Cust_ID_FK) {
        this.Cust_ID_FK = Cust_ID_FK;
    }

    /**
     * @return the Veh_ID_FK
     */
    public String getVeh_ID_FK() {
        return Veh_ID_FK;
    }

    /**
     * @param Veh_ID_FK the Veh_ID_FK to set
     */
    public void setVeh_ID_FK(String Veh_ID_FK) {
        this.Veh_ID_FK = Veh_ID_FK;
    }

    /**
     * @return the App_no_PK
     */
    public String getApp_no_PK() {
        return App_no_PK;
    }

    /**
     * @param App_no_PK the App_no_PK to set
     */
    public void setApp_no_PK(String App_no_PK) {
        this.App_no_PK = App_no_PK;
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
