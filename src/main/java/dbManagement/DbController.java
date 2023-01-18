package dbManagement;

import bussinesLogic.models.Vehicle;
import java.sql.*;
import java.util.ArrayList;

public class DbController {
    private Statement sql;
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
    
    
    public void actualizarHoraSalida(String patente, String value){
        String update = "UPDATE vehiculo SET salida= '" + value + "'" + " WHERE patente= '" + patente + "' AND salida is null;";
        {
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(update);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    
    
    public void acutlizarPagoTotal(String patente, int value){
        String update = "UPDATE vehiculo SET pago_total= " + value + " WHERE patente= '" + patente + "' AND pago_total is null;";
        {
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(update);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void actualizarHorasTotal(String patente, String value){
        String update = "UPDATE vehiculo SET horas_total= '" + value + "'" + " WHERE patente= '" + patente + "' AND horas_total is null;";
        {
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(update);
                sql.close();
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
            resultSet = connection.createStatement().executeQuery("SELECT patente, tipo_vehiculo, precio, entrada FROM vehiculo WHERE salida IS NULL order by id desc");
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
    
    
    public Vehicle solicitarVehiculo(String patente){
        String query = "SELECT patente,tipo_vehiculo,precio,entrada,salida FROM vehiculo WHERE patente= '"+patente+"' AND pago_total is null;";
        Vehicle vehiculo = new Vehicle();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            vehiculo = new Vehicle(
                        resultSet.getString("patente"),
                        resultSet.getString("tipo_vehiculo"),
                        resultSet.getInt("precio"),
                        resultSet.getString("entrada"),
                        resultSet.getString("salida")
            );
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculo;
    }

}
