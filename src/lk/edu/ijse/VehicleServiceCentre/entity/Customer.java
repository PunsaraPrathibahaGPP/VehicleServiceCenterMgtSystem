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
public class Customer {
    private String Cust_Id_PK;
    private String F_Name;
    private String L_Name;
    private String NIC_NO;
    private String cust_Address;
    private double cust_Salary;
    private int cust_Tel;

    public Customer() {
    }

    public Customer(String Cust_Id_PK, String F_Name, String L_Name, String NIC_NO, String cust_Address, double cust_Salary, int cust_Tel) {
        this.Cust_Id_PK = Cust_Id_PK;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.NIC_NO = NIC_NO;
        this.cust_Address = cust_Address;
        this.cust_Salary = cust_Salary;
        this.cust_Tel = cust_Tel;
    }

    /**
     * @return the Cust_Id_PK
     */
    public String getCust_Id_PK() {
        return Cust_Id_PK;
    }

    /**
     * @param Cust_Id_PK the Cust_Id_PK to set
     */
    public void setCust_Id_PK(String Cust_Id_PK) {
        this.Cust_Id_PK = Cust_Id_PK;
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

    
}

