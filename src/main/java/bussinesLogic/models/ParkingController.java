package bussinesLogic.models;

import GUI.WindowData;
import GUI.WindowExit;
import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DbController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ParkingController {
    
    DbController controller = new DbController();
    
    public void agregarVehiculo(String patente, String codigo){
        Vehicle vehicle = VehicleFactory.getInstance().crearVehiculo(patente, codigo);
        controller.agregarVehiculo(vehicle);
    }
    public void setHoraSalida(String patente){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        controller.actualizarColumna(patente,dateFormat.format(date),"salida");
    }

    public ArrayList<Vehicle> solicitarLista() {
        ArrayList<Vehicle> list = controller.solicitarLista();
        Collections.reverse(list);
        return list;
    }
    public void calcularTotal(String entrada, String salida, String patente){
        int horaEntrada = Integer.parseInt(entrada);
        int horaSalida = Integer.parseInt(salida);
        int totalApagar = 0;

    }
    public void dataVehiculo(String patente){
        Vehicle vehicle = controller.solicitarVehiculo(patente);
        WindowData.setVehicle(vehicle);
    }

}
