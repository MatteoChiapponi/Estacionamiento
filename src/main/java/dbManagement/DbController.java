package dbManagement;

import bussinesLogic.models.Vehicle;

import java.sql.*;

public class DbController {
    private Statement sql;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;
    public void agregarVehiculo(Vehicle vehicle){
        String patente = vehicle.getPatente();
        String tipoVehiculo = vehicle.getTipoVehiculo();
        int precio = vehicle.getPrecioXhora();
        String entrada = vehicle.getHoraEntrada();
        String insert = "INSERT INTO estacionamiento_db.Vehiculo(id,tipo_vehiculo,precio,entrada,patente)VALUES(default,'" + tipoVehiculo + "'," + precio + ",'" + entrada + "','" + patente + "');";

        {
            try {
                connection  = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(insert);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void actualizarHoraSalida(String patente, String horaSalida){
        String update = "UPDATE vehiculo SET salida= '"+ horaSalida +"' WHERE patente= '" + patente + "' AND salida is null";
        {
            try {
                connection  = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



}
