package bussinesLogic.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Vehicle {

    private String patente;
    private String tipoVehiculo;
    private int precioXhora;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();


    public Vehicle(String patente, String tipoVehiculo, int precioXhora) {
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.precioXhora = precioXhora;
        horaEntrada = LocalDateTime.parse(dateFormat.format(date));
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

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getPrecioXhora() {
        return precioXhora;
    }

    public void setPrecioXhora(int precioXhora) {
        this.precioXhora = precioXhora;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}
