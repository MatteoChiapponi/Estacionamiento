package bussinesLogic.factorys;

import bussinesLogic.models.Vehicle;

public class VehicleFactory {
    public final static String codAuto = "auto";
    public final static String codMoto = "moto";
    public final static String codCamioneta = "camioneta";

    private static VehicleFactory instance;

    private VehicleFactory() {

    }
    public static VehicleFactory getInstance(){
        if (instance == null)
            instance = new VehicleFactory();
        return instance;
    }
    public Vehicle crearVehiculo(String patente, String codigo){
        if (codigo == codAuto)
            return new Vehicle(patente, "auto", 200);
        if (codigo == codMoto)
            return new Vehicle(patente, "moto", 100);
        if (codigo == codCamioneta)
            return new Vehicle(patente, "camioneta", 250);
        else
            return null;
    }
}
