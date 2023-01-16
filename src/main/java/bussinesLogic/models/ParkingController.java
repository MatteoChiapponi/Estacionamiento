package bussinesLogic.models;

import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DbController;
import java.text.DateFormat;
import java.text.ParseException;
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
        controller.actualizarHoraSalida(patente,dateFormat.format(date));
    }

    public ArrayList<Vehicle> solicitarLista() {
        ArrayList<Vehicle> list = controller.solicitarLista();
        Collections.reverse(list);
        return list;
    }
    public int calcularTotal(String entrada, String salida, int precioXhora, String patente){
        Date horaEntrada = ParkingController.getDateFormat("HH:mm", entrada);
        Date horaSalida = ParkingController.getDateFormat("HH:mm", salida);
        long tiemp1 = horaEntrada.getTime();
        long tiemp2 = horaSalida.getTime();
        long resta = tiemp2 - tiemp1;
        resta = resta/(1000*60);
        System.out.println(resta);
        int horas = 0;
        while (resta > 60){
            horas = horas + 1;
            resta = resta - 60;
        }
        if (resta > 15)
            horas = horas+1;

        int totalApagar = horas * precioXhora;
        controller.acutlizarPagoTotal(patente,totalApagar);

        return totalApagar;
    }
    public static Date getDateFormat(String formatPattern, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicle getDataVehicle(String patente) {
        Vehicle vehicle = controller.solicitarVehiculo(patente);
        return vehicle;
    }

}
