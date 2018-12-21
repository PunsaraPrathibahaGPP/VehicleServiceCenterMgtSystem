/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.edu.ijse.VehicleServiceCentre.business;

import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AddAppointmentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.AppointmentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.CleaningBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.CustomerBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.LubricantBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.MakePaymentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.VehicleBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.PaymentBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.RepairBOImpl;
import lk.edu.ijse.VehicleServiceCentre.business.custom.impl.ServiceDetailBOImpl;

/**
 *
 * @author ACER
 */
public class BOFactory {

    private static BOFactory bOFactory;

    public enum BOTypes {
        CUSTOMER, VEHICLE, APPOINTMENT,SERVICE,ADDAPOINTMENT,PAYMENT,SERVICEDETAIL,LUBRICANT,CLEANING,REPAIR,MAKEPAYMENT;
    }

    public static BOFactory getBOFactory() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }

    public SuperBO getSuperBO(BOTypes types) throws Exception {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case ADDAPOINTMENT:
                return new AddAppointmentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SERVICEDETAIL:
                return  new ServiceDetailBOImpl();
            case LUBRICANT:
                return new LubricantBOImpl();
            case CLEANING:
                return new CleaningBOImpl();
            case SERVICE:
                return new ServiceDetailBOImpl();
            case REPAIR:
                return new RepairBOImpl();
            case MAKEPAYMENT:
                return new MakePaymentBOImpl();
            default:
                return null;
        }

    }
}
