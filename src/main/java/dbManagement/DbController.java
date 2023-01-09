package dbManagement;

import bussinesLogic.models.Vehicle;

import java.sql.*;

public class DbController {
    private Statement sql;
    private PreparedStatement preparedStatement;
    private Connection connection = DbConnection.getInstance().getConnection();
    ResultSet resultSet;
    public void agregarVehiculo(Vehicle vehicle){
        String patente = vehicle.getPatente();
        String tipoVehiculo = vehicle.getTipoVehiculo();
        int precio = vehicle.getPrecioXhora();
        String entrada = vehicle.getHoraEntrada();

        {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO estacionamiento_db.Vehiculo(patente,tipo_vehiculo,precio,entrada)VALUES(?,?,?,?)");
                preparedStatement.setString(1,patente);
                preparedStatement.setString(2,tipoVehiculo);
                preparedStatement.setInt(3,precio);
                preparedStatement.setString(4,entrada);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void actualizarHoraSalida(String patente, String horaSalida){
        String query = "UPDATE vehiculo SET salida= '"+ horaSalida +"' WHERE patente= '" + patente + "'";
        {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



}
