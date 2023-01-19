package dbManagement;

import bussinesLogic.models.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DbController {
    private Statement sql;
    private ResultSet resultSet;
    private Connection connection;
    
    public void exceptionError(SQLException ex){
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Base de Datos", JOptionPane.ERROR_MESSAGE);
    }
    
    public void agregarVehiculo(Vehicle vehicle){
        connection = DbConnection.getInstance().getConnection();
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
                exceptionError(e);
            }
        }
    }

    public void acutlizarPagoTotal(String patente, int value){
        String update = "UPDATE vehiculo SET pago_total= " + value + " WHERE patente= '" + patente + "' AND pago_total is null;";
        {
            try {
                connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(update);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }

    }
    public int actualizarColumnaString(String patente, String value, String columna){
        String update = "UPDATE vehiculo SET "+columna+" = '"+value+"'"+" WHERE patente= '"+patente+"' AND "+columna+" is null;";
        int resultQuery = 0;
        {
            try { 
                connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                resultQuery = sql.executeUpdate(update);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }
        return resultQuery;
    }
    
    public ArrayList<Vehicle> solicitarLista() {
        
        ArrayList<Vehicle> list = new ArrayList<>();
        
        try {
            connection = DbConnection.getInstance().getConnection();
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
            exceptionError(e);
        }    
        return list;
    }
    
    
    public Vehicle solicitarVehiculo(String patente){
        String query = "SELECT patente,tipo_vehiculo,precio,entrada,salida FROM vehiculo WHERE patente= '"+patente+"' AND pago_total is null;";
        Vehicle vehiculo = new Vehicle();
        try {
            connection = DbConnection.getInstance().getConnection();
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
            exceptionError(e);
        }
        return vehiculo;
    }
    
    
    public int solicitarPrecioXhoraVehiculo(String columna){
        String query = "SELECT "+columna+" FROM precio;";
        int precioXhora= 0;
        try {
            connection = DbConnection.getInstance().getConnection();
            resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            precioXhora = resultSet.getInt(columna);
            resultSet.close();
            connection.close();
          
        } catch (SQLException e) {
            exceptionError(e);
        }
        return precioXhora;
    }
    
    
    public void actuliazarPrecioXhora(String columna, int precioNuevo){
        String update = "UPDATE precio SET "+columna+ " = "+precioNuevo+" WHERE id = 1;";
        {
            try {
                connection = DbConnection.getInstance().getConnection();
                sql = connection.createStatement();
                sql.executeUpdate(update);
                sql.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }
    }
}
