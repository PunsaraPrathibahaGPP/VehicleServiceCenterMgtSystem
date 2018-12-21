/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.dao;

import java.sql.SQLException;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.AppointmentDAO;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.AppointmentDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.CleaningDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.CustomerDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.LubricantDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.PaymentDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.RepairDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.ServiceDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.ServiceDetailDAOImpl;
import lk.edu.ijse.VehicleServiceCentre.dao.custom.impl.VehicleDAOImpl;

/**
 *
 * @author ACER
 */
public class DAOFactory {

    public static DAOFactory daoFactory;

    public static Object getBOFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum DAOFactTypes {
        CUSTOMER, VEHICLE, SERVICE,APPOINTMENT,PAYMENT,SERVICEDETAIL,LUBRICANT,CLEANING,REPAIR;
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getSuperDAO(DAOFactTypes types) throws ClassNotFoundException, SQLException {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case APPOINTMENT:
                return  new AppointmentDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SERVICEDETAIL:
                return new ServiceDetailDAOImpl();
            case LUBRICANT:
                return new LubricantDAOImpl();
            case CLEANING:
                return new CleaningDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            default:
                return null;
        }
    }
}
