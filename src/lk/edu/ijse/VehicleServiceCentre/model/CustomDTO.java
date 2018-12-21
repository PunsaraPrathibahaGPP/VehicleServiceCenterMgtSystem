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
class CustomDTO extends SuperDTO{
    private String Cust_Id;
    private String F_Name;
    private String L_Name;
    private String NIC_NO;
    private String cust_Address;
    private double cust_Salary;
    private int cust_Tel;
    private String veh_ID;
    private String veh_No;
    private String veh_Type;
    private String veh_Brand;

    public CustomDTO() {
    }

    public CustomDTO(String Cust_Id, String F_Name, String L_Name, String NIC_NO, String cust_Address, double cust_Salary, int cust_Tel, String veh_ID, String veh_No, String veh_Type, String veh_Brand) {
        this.Cust_Id = Cust_Id;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.NIC_NO = NIC_NO;
        this.cust_Address = cust_Address;
        this.cust_Salary = cust_Salary;
        this.cust_Tel = cust_Tel;
        this.veh_ID = veh_ID;
        this.veh_No = veh_No;
        this.veh_Type = veh_Type;
        this.veh_Brand = veh_Brand;
    }

    /**
     * @return the Cust_Id
     */
    public String getCust_Id() {
        return Cust_Id;
    }

    /**
     * @param Cust_Id the Cust_Id to set
     */
    public void setCust_Id(String Cust_Id) {
        this.Cust_Id = Cust_Id;
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
