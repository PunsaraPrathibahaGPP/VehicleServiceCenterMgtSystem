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
public class CleaningDTO extends SuperDTO{
    private String Cl_ID;
    private String Techni_ID;
    private String Techni_Name;
    private String Cl_Types;
    private double Cl_Fee;

    public CleaningDTO() {
    }

    public CleaningDTO(String Cl_ID, String Techni_ID, String Techni_Name, String Cl_Types, double Cl_Fee) {
        this.Cl_ID = Cl_ID;
        this.Techni_ID = Techni_ID;
        this.Techni_Name = Techni_Name;
        this.Cl_Types = Cl_Types;
        this.Cl_Fee = Cl_Fee;
    }

    /**
     * @return the Cl_ID
     */
    public String getCl_ID() {
        return Cl_ID;
    }

    /**
     * @param Cl_ID the Cl_ID to set
     */
    public void setCl_ID(String Cl_ID) {
        this.Cl_ID = Cl_ID;
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
