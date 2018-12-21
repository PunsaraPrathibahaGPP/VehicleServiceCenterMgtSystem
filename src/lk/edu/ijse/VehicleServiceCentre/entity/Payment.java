/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.entity;

/**
 *
 * @author ACER
 */
public class Payment {
    private String Pay_ID_PK ;
    private String Pay_Date;
    private String Pay_Time;
    private double Tot_Ammount;

    public Payment() {
    }

    public Payment(String Pay_ID_PK, String Pay_Date, String Pay_Time, double Tot_Ammount) {
        this.Pay_ID_PK = Pay_ID_PK;
        this.Pay_Date = Pay_Date;
        this.Pay_Time = Pay_Time;
        this.Tot_Ammount = Tot_Ammount;
    }

    /**
     * @return the Pay_ID_PK
     */
    public String getPay_ID_PK() {
        return Pay_ID_PK;
    }

    /**
     * @param Pay_ID_PK the Pay_ID_PK to set
     */
    public void setPay_ID_PK(String Pay_ID_PK) {
        this.Pay_ID_PK = Pay_ID_PK;
    }

    /**
     * @return the Pay_Date
     */
    public String getPay_Date() {
        return Pay_Date;
    }

    /**
     * @param Pay_Date the Pay_Date to set
     */
    public void setPay_Date(String Pay_Date) {
        this.Pay_Date = Pay_Date;
    }

    /**
     * @return the Pay_Time
     */
    public String getPay_Time() {
        return Pay_Time;
    }

    /**
     * @param Pay_Time the Pay_Time to set
     */
    public void setPay_Time(String Pay_Time) {
        this.Pay_Time = Pay_Time;
    }

    /**
     * @return the Tot_Ammount
     */
    public double getTot_Ammount() {
        return Tot_Ammount;
    }

    /**
     * @param Tot_Ammount the Tot_Ammount to set
     */
    public void setTot_Ammount(double Tot_Ammount) {
        this.Tot_Ammount = Tot_Ammount;
    }

    
}
