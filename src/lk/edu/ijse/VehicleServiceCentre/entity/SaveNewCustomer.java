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
public class SaveNewCustomer {
    private String Cust_Id_FK;
    private String F_Name;
    private String L_Name;
    private String NIC_NO;
    private String cust_Address;
    private double cust_Salary;
    private int cust_Tel;
    
    private String veh_ID_FK;
    private String veh_No;
    private String veh_Type;
    private String veh_Brand;
    
    private String App_no_PK;
    private String Curr_date;
    private String Curr_time;
    private String App_date;
    private String App_time;

    public SaveNewCustomer() {
    }

    public SaveNewCustomer(String Cust_Id_FK, String F_Name, String L_Name, String NIC_NO, String cust_Address, double cust_Salary, int cust_Tel, String veh_ID_FK, String veh_No, String veh_Type, String veh_Brand, String App_no_PK, String Curr_date, String Curr_time, String App_date, String App_time) {
        this.Cust_Id_FK = Cust_Id_FK;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.NIC_NO = NIC_NO;
        this.cust_Address = cust_Address;
        this.cust_Salary = cust_Salary;
        this.cust_Tel = cust_Tel;
        this.veh_ID_FK = veh_ID_FK;
        this.veh_No = veh_No;
        this.veh_Type = veh_Type;
        this.veh_Brand = veh_Brand;
        this.App_no_PK = App_no_PK;
        this.Curr_date = Curr_date;
        this.Curr_time = Curr_time;
        this.App_date = App_date;
        this.App_time = App_time;
    }

    /**
     * @return the Cust_Id_FK
     */
    public String getCust_Id_FK() {
        return Cust_Id_FK;
    }

    /**
     * @param Cust_Id_FK the Cust_Id_FK to set
     */
    public void setCust_Id_FK(String Cust_Id_FK) {
        this.Cust_Id_FK = Cust_Id_FK;
    }

    /**
     * @return the F_Name
     */
    public String getF_Name() {
        return F_Name;
    }

    /**
     * @param F_Name the F_Name to set
     */
    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    /**
     * @return the L_Name
     */
    public String getL_Name() {
        return L_Name;
    }

    /**
     * @param L_Name the L_Name to set
     */
    public void setL_Name(String L_Name) {
        this.L_Name = L_Name;
    }

    /**
     * @return the NIC_NO
     */
    public String getNIC_NO() {
        return NIC_NO;
    }

    /**
     * @param NIC_NO the NIC_NO to set
     */
    public void setNIC_NO(String NIC_NO) {
        this.NIC_NO = NIC_NO;
    }

    /**
     * @return the cust_Address
     */
    public String getCust_Address() {
        return cust_Address;
    }

    /**
     * @param cust_Address the cust_Address to set
     */
    public void setCust_Address(String cust_Address) {
        this.cust_Address = cust_Address;
    }

    /**
     * @return the cust_Salary
     */
    public double getCust_Salary() {
        return cust_Salary;
    }

    /**
     * @param cust_Salary the cust_Salary to set
     */
    public void setCust_Salary(double cust_Salary) {
        this.cust_Salary = cust_Salary;
    }

    /**
     * @return the cust_Tel
     */
    public int getCust_Tel() {
        return cust_Tel;
    }

    /**
     * @param cust_Tel the cust_Tel to set
     */
    public void setCust_Tel(int cust_Tel) {
        this.cust_Tel = cust_Tel;
    }

    /**
     * @return the veh_ID_FK
     */
    public String getVeh_ID_FK() {
        return veh_ID_FK;
    }

    /**
     * @param veh_ID_FK the veh_ID_FK to set
     */
    public void setVeh_ID_FK(String veh_ID_FK) {
        this.veh_ID_FK = veh_ID_FK;
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

    /**
     * @return the App_no_PK
     */
    public String getApp_no_PK() {
        return App_no_PK;
    }

    /**
     * @param App_no_PK the App_no_PK to set
     */
    public void setApp_no_PK(String App_no_PK) {
        this.App_no_PK = App_no_PK;
    }

    /**
     * @return the Curr_date
     */
    public String getCurr_date() {
        return Curr_date;
    }

    /**
     * @param Curr_date the Curr_date to set
     */
    public void setCurr_date(String Curr_date) {
        this.Curr_date = Curr_date;
    }

    /**
     * @return the Curr_time
     */
    public String getCurr_time() {
        return Curr_time;
    }

    /**
     * @param Curr_time the Curr_time to set
     */
    public void setCurr_time(String Curr_time) {
        this.Curr_time = Curr_time;
    }

    /**
     * @return the App_date
     */
    public String getApp_date() {
        return App_date;
    }

    /**
     * @param App_date the App_date to set
     */
    public void setApp_date(String App_date) {
        this.App_date = App_date;
    }

    /**
     * @return the App_time
     */
    public String getApp_time() {
        return App_time;
    }

    /**
     * @param App_time the App_time to set
     */
    public void setApp_time(String App_time) {
        this.App_time = App_time;
    }

    
}
