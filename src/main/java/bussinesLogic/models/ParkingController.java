package bussinesLogic.models;

import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DbController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingController {
    DbController controller = new DbController();
    public void agregarVehiculo(String patente, String codigo){
        Vehicle vehicle = VehicleFactory.getInstance().crearVehiculo(patente, VehicleFactory.codAuto);
        controller.agregarVehiculo(vehicle);
    }
    public void setHoraSalida(String patente){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        controller.actualizarHoraSalida(patente,dateFormat.format(date));
    }
}
