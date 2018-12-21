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
public class Lubricant{
    private String Lub_ID_PK;
    private String Techni_ID;  
    private String Techni_Name ;
    private String Lub_Type; 
    private double Service_Fee; 
    private double LubricantFee;
    private double Total_Fee;

    public Lubricant() {
    }

    public Lubricant(String Lub_ID_PK, String Techni_ID, String Techni_Name, String Lub_Type, double Service_Fee, double LubricantFee, double Total_Fee) {
        this.Lub_ID_PK = Lub_ID_PK;
        this.Techni_ID = Techni_ID;
        this.Techni_Name = Techni_Name;
        this.Lub_Type = Lub_Type;
        this.Service_Fee = Service_Fee;
        this.LubricantFee = LubricantFee;
        this.Total_Fee = Total_Fee;
    }

    /**
     * @return the Lub_ID_PK
     */
    public String getLub_ID_PK() {
        return Lub_ID_PK;
    }

    /**
     * @param Lub_ID_PK the Lub_ID_PK to set
     */
    public void setLub_ID_PK(String Lub_ID_PK) {
        this.Lub_ID_PK = Lub_ID_PK;
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
     * @return the Lub_Type
     */
    public String getLub_Type() {
        return Lub_Type;
    }

    /**
     * @param Lub_Type the Lub_Type to set
     */
    public void setLub_Type(String Lub_Type) {
        this.Lub_Type = Lub_Type;
    }

    /**
     * @return the Service_Fee
     */
    public double getService_Fee() {
        return Service_Fee;
    }

    /**
     * @param Service_Fee the Service_Fee to set
     */
    public void setService_Fee(double Service_Fee) {
        this.Service_Fee = Service_Fee;
    }

    /**
     * @return the LubricantFee
     */
    public double getLubricantFee() {
        return LubricantFee;
    }

    /**
     * @param LubricantFee the LubricantFee to set
     */
    public void setLubricantFee(double LubricantFee) {
        this.LubricantFee = LubricantFee;
    }

    /**
     * @return the Total_Fee
     */
    public double getTotal_Fee() {
        return Total_Fee;
    }

    /**
     * @param Total_Fee the Total_Fee to set
     */
    public void setTotal_Fee(double Total_Fee) {
        this.Total_Fee = Total_Fee;
    }


    
}
