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
public class Service {
    private String Pay_ID_FK;
    private String SID_PK;
    private String Time_Duration;
    private double S_Fee;

    public Service() {
    }

    public Service(String Pay_ID_FK, String SID_PK, String Time_Duration, double S_Fee) {
        this.Pay_ID_FK = Pay_ID_FK;
        this.SID_PK = SID_PK;
        this.Time_Duration = Time_Duration;
        this.S_Fee = S_Fee;
    }

    /**
     * @return the Pay_ID_FK
     */
    public String getPay_ID_FK() {
        return Pay_ID_FK;
    }

    /**
     * @param Pay_ID_FK the Pay_ID_FK to set
     */
    public void setPay_ID_FK(String Pay_ID_FK) {
        this.Pay_ID_FK = Pay_ID_FK;
    }

    /**
     * @return the SID_PK
     */
    public String getSID_PK() {
        return SID_PK;
    }

    /**
     * @param SID_PK the SID_PK to set
     */
    public void setSID_PK(String SID_PK) {
        this.SID_PK = SID_PK;
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
