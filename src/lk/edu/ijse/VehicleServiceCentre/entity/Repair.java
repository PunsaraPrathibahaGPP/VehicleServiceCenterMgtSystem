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
public class Repair {
    private String R_ID_PK;
    private String Techni_ID;
    private String Techni_Name;
    private String R_Type;
    private double R_Fee;
    private double Other_Fees;
    private double Total_Fees;

    public Repair() {
    }

    public Repair(String R_ID_PK, String Techni_ID, String Techni_Name, String R_Type, double R_Fee, double Other_Fees, double Total_Fees) {
        this.R_ID_PK = R_ID_PK;
        this.Techni_ID = Techni_ID;
        this.Techni_Name = Techni_Name;
        this.R_Type = R_Type;
        this.R_Fee = R_Fee;
        this.Other_Fees = Other_Fees;
        this.Total_Fees = Total_Fees;
    }

    /**
     * @return the R_ID_PK
     */
    public String getR_ID_PK() {
        return R_ID_PK;
    }

    /**
     * @param R_ID_PK the R_ID_PK to set
     */
    public void setR_ID_PK(String R_ID_PK) {
        this.R_ID_PK = R_ID_PK;
    }

    /**
     * @return the Techni_ID
     */
    public String getTechni_ID() {
        return Techni_ID;
    }

    /**
     * @param Techni_ID the Techni_ID to set
     */
    public void setTechni_ID(String Techni_ID) {
        this.Techni_ID = Techni_ID;
    }

    /**
     * @return the Techni_Name
     */
    public String getTechni_Name() {
        return Techni_Name;
    }

    /**
     * @param Techni_Name the Techni_Name to set
     */
    public void setTechni_Name(String Techni_Name) {
        this.Techni_Name = Techni_Name;
    }

    /**
     * @return the R_Type
     */
    public String getR_Type() {
        return R_Type;
    }

    /**
     * @param R_Type the R_Type to set
     */
    public void setR_Type(String R_Type) {
        this.R_Type = R_Type;
    }

    /**
     * @return the R_Fee
     */
    public double getR_Fee() {
        return R_Fee;
    }

    /**
     * @param R_Fee the R_Fee to set
     */
    public void setR_Fee(double R_Fee) {
        this.R_Fee = R_Fee;
    }

    /**
     * @return the Other_Fees
     */
    public double getOther_Fees() {
        return Other_Fees;
    }

    /**
     * @param Other_Fees the Other_Fees to set
     */
    public void setOther_Fees(double Other_Fees) {
        this.Other_Fees = Other_Fees;
    }

    /**
     * @return the Total_Fees
     */
    public double getTotal_Fees() {
        return Total_Fees;
    }

    /**
     * @param Total_Fees the Total_Fees to set
     */
    public void setTotal_Fees(double Total_Fees) {
        this.Total_Fees = Total_Fees;
    }
    
    
}
