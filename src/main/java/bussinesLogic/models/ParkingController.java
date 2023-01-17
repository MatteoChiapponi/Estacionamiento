package bussinesLogic.models;

import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DbController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParkingController {
    
    DbController controller = new DbController();
    
    public void agregarVehiculo(String patente, String codigo){
        Vehicle vehicle = VehicleFactory.getInstance().crearVehiculo(patente, codigo);
        controller.agregarVehiculo(vehicle);
    }
    
    
    public void setHoraSalida(String patente){
        DateFormat dateFormat = new SimpleDateFormat("DD/MM HH:mm");
        Date date = new Date();
        controller.actualizarHoraSalida(patente,dateFormat.format(date));
    }
    

    public ArrayList<Vehicle> solicitarLista() {
        return controller.solicitarLista();
    }
    
    
    public Data calcularTotal(String entrada, String salida, int precioXhora, String patente){
        Date horaEntrada = ParkingController.getDateFormat("DD/MM HH:mm", entrada);
        Date horaSalida = ParkingController.getDateFormat("DD/MM HH:mm", salida);
        long tiemp1 = horaEntrada.getTime();
        long tiemp2 = horaSalida.getTime();
        long mins = (tiemp2 - tiemp1)/(60*1000);
        System.out.println(mins);
        int horas = 0;
        while (mins > 60){
            horas = horas + 1;
            mins = mins - 60;
        }
        
        if(mins > 15)horas = horas+1;
        //if(mins > 20 && mins < 40)horas = horas+0.5;
        //if(mins > 40)horas = horas+1;

        int totalApagar = horas * precioXhora;
        String horasTotales = horas+":"+mins;
        controller.acutlizarPagoTotal(patente,totalApagar);
  
        Data objeto = new Data(horasTotales, totalApagar);
        return objeto;
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
        return controller.solicitarVehiculo(patente);
    }

}
