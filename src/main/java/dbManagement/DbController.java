package dbManagement;

import bussinesLogic.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class DbController {
    private Statement sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    
    public void agregarVehiculo(Vehicle vehicle){
        Connection connection = DbConnection.getInstance().getConnection();
        String patente = vehicle.getPatente();
        String tipoVehiculo = vehicle.getTipoVehiculo();
        int precio = vehicle.getPrecioXhora();
        String entrada = vehicle.getHoraEntrada();
        String insert = "INSERT INTO estacionamiento_db.Vehiculo(id,tipo_vehiculo,precio,entrada,patente)VALUES(default,'" + tipoVehiculo + "'," + precio + ",'" + entrada + "','" + patente + "');";

        {
            try {
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
                Connection connection = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ArrayList<Vehicle> solicitarLista() {
        
        ArrayList<Vehicle> list = new ArrayList<>();
        
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            resultSet = connection.createStatement().executeQuery("SELECT patente, tipo_vehiculo, precio, entrada FROM vehiculo WHERE salida IS NULL");
            while(resultSet.next()){
                
                Vehicle vehiculo = new Vehicle(resultSet.getString("patente"),
                            resultSet.getString("tipo_vehiculo"),
                                   resultSet.getInt("precio"),
                                        resultSet.getString("entrada"));
                
                list.add(vehiculo);     
            }
            resultSet.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
        return list;
    }
}
