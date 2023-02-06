package bussinesLogic.models;

import bussinesLogic.factorys.VehicleFactory;
import dbManagement.DAO.DbController;
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

    public int setHoraSalida(String patente){
        DateFormat dateFormat = new SimpleDateFormat("DD/MM HH:mm");
        Date date = new Date();
        return controller.actualizarColumnaString(patente,dateFormat.format(date),"salida");     
    }

    public ArrayList<Vehicle> solicitarLista() {
        return controller.solicitarLista();
    }
    
    public ArrayList<Object> calcularTotal(String entrada, String salida, int precioXhora, String patente){
        Date horaEntrada = ParkingController.getDateFormat("DD/MM HH:mm", entrada);
        Date horaSalida = ParkingController.getDateFormat("DD/MM HH:mm", salida);
        long tiemp1 = horaEntrada.getTime();
        long tiemp2 = horaSalida.getTime();
        long mins = tiemp2 - tiemp1;
        mins = mins /(60*1000);
        int horas = 0;
        while (mins >= 60){
            horas = horas + 1;
            mins = mins - 60;
        }
        String totalHoras = horas+":"+mins;
        if(mins > 15){
            horas = horas + 1;
        }

        int totalApagar = horas * precioXhora;
        controller.acutlizarPagoTotal(patente,totalApagar);
        controller.actualizarColumnaString(patente,totalHoras,"horas_total");
        
        ArrayList<Object> list = new ArrayList<>();
        list.add(totalHoras);
        list.add(totalApagar);
        return list;
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
    
    
    public void actualizarPrecioXhora(String vehicleToUpdate, int precioNuevo){
        if (vehicleToUpdate.equals("Moto"))
            controller.actuliazarPrecioXhora("precio_moto", precioNuevo);
        else if (vehicleToUpdate.equals("Camioneta"))
            controller.actuliazarPrecioXhora("precio_camioneta",precioNuevo);
        else
            controller.actuliazarPrecioXhora("precio_auto",precioNuevo);
    }
    
    
    public int getPrecioXhora(String tipoVehiculo){
        if (tipoVehiculo.equals("Moto"))
            return controller.solicitarPrecioXhoraVehiculo("precio_moto");
        if (tipoVehiculo.equals("Auto"))
            return controller.solicitarPrecioXhoraVehiculo("precio_auto");
        else
            return controller.solicitarPrecioXhoraVehiculo("precio_camioneta");
    }

    public ArrayList<Vehicle> getCoincidencia(String patente) {
        
        return controller.buscarCoincidencia(patente);
    }
}
