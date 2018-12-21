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
public class Cleaning {
    private String Cl_ID_PK;
    private String Techni_ID;
    private String Techni_Name;
    private String Cl_Types;
    private double Cl_Fee;

    public Cleaning() {
    }

    public Cleaning(String Cl_ID_PK, String Techni_ID, String Techni_Name, String Cl_Types, double Cl_Fee) {
        this.Cl_ID_PK = Cl_ID_PK;
        this.Techni_ID = Techni_ID;
        this.Techni_Name = Techni_Name;
        this.Cl_Types = Cl_Types;
        this.Cl_Fee = Cl_Fee;
    }

    /**
     * @return the Cl_ID_PK
     */
    public String getCl_ID_PK() {
        return Cl_ID_PK;
    }

    /**
     * @param Cl_ID_PK the Cl_ID_PK to set
     */
    public void setCl_ID_PK(String Cl_ID_PK) {
        this.Cl_ID_PK = Cl_ID_PK;
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
     * @return the Cl_Types
     */
    public String getCl_Types() {
        return Cl_Types;
    }

    /**
     * @param Cl_Types the Cl_Types to set
     */
    public void setCl_Types(String Cl_Types) {
        this.Cl_Types = Cl_Types;
    }

    /**
     * @return the Cl_Fee
     */
    public double getCl_Fee() {
        return Cl_Fee;
    }

    /**
     * @param Cl_Fee the Cl_Fee to set
     */
    public void setCl_Fee(double Cl_Fee) {
        this.Cl_Fee = Cl_Fee;
    }
    
    
}
