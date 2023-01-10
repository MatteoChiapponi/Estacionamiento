package bussinesLogic.factorys;

import bussinesLogic.models.Vehicle;

public class VehicleFactory {
    public final static String codAuto = "Auto";
    public final static String codMoto = "Moto";
    public final static String codCamioneta = "Camioneta";

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
            return new Vehicle(patente, "Auto", 200);
        if (codigo == codMoto)
            return new Vehicle(patente, "Moto", 100);
        if (codigo == codCamioneta)
            return new Vehicle(patente, "Camioneta", 250);
        else
            return null;
    }
}
