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
public class ServiceDetailDTO extends SuperDTO{
    private String Veh_ID;
    private String SID;

    public ServiceDetailDTO() {
    }

    public ServiceDetailDTO(String Veh_ID, String SID) {
        this.Veh_ID = Veh_ID;
        this.SID = SID;
    }

    /**
     * @return the Veh_ID
     */
    public String getVeh_ID() {
        return Veh_ID;
    }

    /**
     * @param Veh_ID the Veh_ID to set
     */
    public void setVeh_ID(String Veh_ID) {
        this.Veh_ID = Veh_ID;
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
    
    
}
