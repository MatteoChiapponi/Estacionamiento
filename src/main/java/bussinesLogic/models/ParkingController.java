package bussinesLogic.models;

import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DbController;

public class ParkingController {
    DbController controller = new DbController();
    public void agregarVehiculo(String patente, String codigo){
        Vehicle vehicle = VehicleFactory.getInstance().crearVehiculo(patente, VehicleFactory.codAuto);
        controller.agregarVehiculo(vehicle);
    }
}
