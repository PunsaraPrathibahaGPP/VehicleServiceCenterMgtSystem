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
public class ServiceDTO extends SuperDTO{
    private String Pay_ID;
    private String SID;
    private String Time_Duration;
    private double S_Fee;

    public ServiceDTO() {
    }

    public ServiceDTO(String Pay_ID, String SID, String Time_Duration, double S_Fee) {
        this.Pay_ID = Pay_ID;
        this.SID = SID;
        this.Time_Duration = Time_Duration;
        this.S_Fee = S_Fee;
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
     * @return the SID
     */
    public String getSID() {
        return SID;
    }

    /**
     * @param SID the SID to set
     */
    public void setSID(String SID) {
        this.SID = SID;
    }

    /**
     * @return the Time_Duration
     */
    public String getTime_Duration() {
        return Time_Duration;
    }

    /**
     * @param Time_Duration the Time_Duration to set
     */
    public void setTime_Duration(String Time_Duration) {
        this.Time_Duration = Time_Duration;
    }

    /**
     * @return the S_Fee
     */
    public double getS_Fee() {
        return S_Fee;
    }

    /**
     * @param S_Fee the S_Fee to set
     */
    public void setS_Fee(double S_Fee) {
        this.S_Fee = S_Fee;
    }
    
}
