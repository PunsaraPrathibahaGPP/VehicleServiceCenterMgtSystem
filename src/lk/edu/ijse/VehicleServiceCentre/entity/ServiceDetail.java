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
public class ServiceDetail {
    private String Veh_ID_FK;
    private String SID_PK;

    public ServiceDetail() {
    }

    public ServiceDetail(String Veh_ID_FK, String SID_PK) {
        this.Veh_ID_FK = Veh_ID_FK;
        this.SID_PK = SID_PK;
    }

    /**
     * @return the Veh_ID_FK
     */
    public String getVeh_ID_FK() {
        return Veh_ID_FK;
    }

    /**
     * @param Veh_ID_FK the Veh_ID_FK to set
     */
    public void setVeh_ID_FK(String Veh_ID_FK) {
        this.Veh_ID_FK = Veh_ID_FK;
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
    
    
}
