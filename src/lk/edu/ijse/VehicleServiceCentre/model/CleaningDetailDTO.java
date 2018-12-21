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
public class CleaningDetailDTO extends SuperDTO{
    private String SID;
    private String Cl_ID;
    private String Cl_Detail;
    private String Super_ID;
    private String Super_Name;

    public CleaningDetailDTO() {
    }

    public CleaningDetailDTO(String SID, String Cl_ID, String Cl_Detail, String Super_ID, String Super_Name) {
        this.SID = SID;
        this.Cl_ID = Cl_ID;
        this.Cl_Detail = Cl_Detail;
        this.Super_ID = Super_ID;
        this.Super_Name = Super_Name;
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
     * @return the Cl_Detail
     */
    public String getCl_Detail() {
        return Cl_Detail;
    }

    /**
     * @param Cl_Detail the Cl_Detail to set
     */
    public void setCl_Detail(String Cl_Detail) {
        this.Cl_Detail = Cl_Detail;
    }

    /**
     * @return the Super_ID
     */
    public String getSuper_ID() {
        return Super_ID;
    }

    /**
     * @param Super_ID the Super_ID to set
     */
    public void setSuper_ID(String Super_ID) {
        this.Super_ID = Super_ID;
    }

    /**
     * @return the Super_Name
     */
    public String getSuper_Name() {
        return Super_Name;
    }

    /**
     * @param Super_Name the Super_Name to set
     */
    public void setSuper_Name(String Super_Name) {
        this.Super_Name = Super_Name;
    }
    
    
}
