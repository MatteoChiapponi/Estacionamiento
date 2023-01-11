package bussinesLogic.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    private String patente;
    private String tipoVehiculo;
    private int precioXhora;
    private String horaEntrada;
    private String horaSalida;

    DateFormat dateFormat;
    Date date;

    public Vehicle(String patente, String tipoVehiculo, int precioXhora) {
        dateFormat = new SimpleDateFormat("HH:mm");
        date = new Date();
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.precioXhora = precioXhora;
        horaEntrada = dateFormat.format(date);
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getPrecioXhora() {
        return precioXhora;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {

        this.horaSalida = horaSalida;
    }
}
