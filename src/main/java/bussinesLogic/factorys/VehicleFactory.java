package bussinesLogic.factorys;

import bussinesLogic.models.Vehicle;
import dbManagement.DbController;

public class VehicleFactory {
    public final static String codAuto = "Auto";
    public final static String codMoto = "Moto";
    public final static String codCamioneta = "Camioneta";

    private static VehicleFactory instance;
    DbController controller = new DbController();

    private VehicleFactory() {

    }
    public static VehicleFactory getInstance(){
        if (instance == null)
            instance = new VehicleFactory();
        return instance;
    }
    public Vehicle crearVehiculo(String patente, String codigo){
        if (codigo == codAuto)
            return new Vehicle(patente, "Auto", controller.solicitarPrecioXhoraVehiculo("precio_auto"));
        if (codigo == codMoto)
            return new Vehicle(patente, "Moto", controller.solicitarPrecioXhoraVehiculo("precio_moto"));
        else
            return new Vehicle(patente, "Camioneta", controller.solicitarPrecioXhoraVehiculo("precio_camioneta"));
    }
}
