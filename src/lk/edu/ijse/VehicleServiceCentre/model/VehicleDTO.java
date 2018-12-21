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
public class VehicleDTO extends SuperDTO{
    private String veh_ID;
    private String veh_No;
    private String veh_Type;
    private String veh_Brand;

    public VehicleDTO() {
    }

    public VehicleDTO(String veh_ID, String veh_No, String veh_Type, String veh_Brand) {
        this.veh_ID = veh_ID;
        this.veh_No = veh_No;
        this.veh_Type = veh_Type;
        this.veh_Brand = veh_Brand;
    }

    /**
     * @return the veh_ID
     */
    public String getVeh_ID() {
        return veh_ID;
    }

    /**
     * @param veh_ID the veh_ID to set
     */
    public void setVeh_ID(String veh_ID) {
        this.veh_ID = veh_ID;
    }

    /**
     * @return the veh_No
     */
    public String getVeh_No() {
        return veh_No;
    }

    /**
     * @param veh_No the veh_No to set
     */
    public void setVeh_No(String veh_No) {
        this.veh_No = veh_No;
    }

    /**
     * @return the veh_Type
     */
    public String getVeh_Type() {
        return veh_Type;
    }

    /**
     * @param veh_Type the veh_Type to set
     */
    public void setVeh_Type(String veh_Type) {
        this.veh_Type = veh_Type;
    }

    /**
     * @return the veh_Brand
     */
    public String getVeh_Brand() {
        return veh_Brand;
    }

    /**
     * @param veh_Brand the veh_Brand to set
     */
    public void setVeh_Brand(String veh_Brand) {
        this.veh_Brand = veh_Brand;
    }

   
}
