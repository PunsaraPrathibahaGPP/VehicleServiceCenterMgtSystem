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
public class PaymentDTO extends SuperDTO{
    private String Pay_ID ;
    private String Pay_Date;
    private String Pay_Time;
    private double Tot_Ammountl;

    public PaymentDTO() {
    }

    public PaymentDTO(String Pay_ID, String Pay_Date, String Pay_Time, double Tot_Ammountl) {
        this.Pay_ID = Pay_ID;
        this.Pay_Date = Pay_Date;
        this.Pay_Time = Pay_Time;
        this.Tot_Ammountl = Tot_Ammountl;
    }

    /**
     * @return the Pay_ID
     */
    public String getPay_ID() {
        return Pay_ID;
    }

    /**
     * @param Pay_ID the Pay_ID to set
     */
    public void setPay_ID(String Pay_ID) {
        this.Pay_ID = Pay_ID;
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
     * @return the Tot_Ammountl
     */
    public double getTot_Ammountl() {
        return Tot_Ammountl;
    }

    /**
     * @param Tot_Ammountl the Tot_Ammountl to set
     */
    public void setTot_Ammountl(double Tot_Ammountl) {
        this.Tot_Ammountl = Tot_Ammountl;
    }

    
}
