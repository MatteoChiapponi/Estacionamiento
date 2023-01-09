package dbManagement;

import bussinesLogic.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbController {
    private Statement sql;
    private PreparedStatement statement;
    private Connection connection = DbConnection.getInstance().getConnection();
    public void agregarVehiculo(Vehicle vehicle){
        String patente = vehicle.getPatente();
        String tipoVehiculo = vehicle.getTipoVehiculo();
        int precio = vehicle.getPrecioXhora();
        String entrada = String.valueOf(vehicle.getHoraEntrada());

        {
            try {
                sql = connection.createStatement();
                statement = connection.prepareStatement("INSERT INTO estacionamiento_db.Vehiculo(patente,tipo_vehiculo,precio,entrada)VALUES(?,?,?,?)");
                statement.setString(1,patente);
                statement.setString(2,tipoVehiculo);
                statement.setInt(3,precio);
                statement.setString(4,entrada);
                statement.executeUpdate();
                sql.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void agregarHoraSalida(String patente, String horaSalida){
        {
            try {
                sql = connection.createStatement();
                statement = connection.prepareStatement("INSERT INTO estacionamiento_db.Vehiculo(patente,tipo_vehiculo,precio,entrada)VALUES(?,?,?,?)");
                statement.executeUpdate();
                sql.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



}
