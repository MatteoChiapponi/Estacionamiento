package dbManagement;

import bussinesLogic.models.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DbController {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;
    
    public void exceptionError(SQLException ex){
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Base de Datos", JOptionPane.ERROR_MESSAGE);
    }
    
    public void agregarVehiculo(Vehicle vehicle){
        String tipoVehiculo = vehicle.getTipoVehiculo();
        int precio = vehicle.getPrecioXhora();
        String entrada = vehicle.getHoraEntrada();
        String patente = vehicle.getPatente();

        {
            try {
                connection = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO vehiculo "
                        + "(tipo_vehiculo,precio,entrada,patente)"
                        + " VALUES(? ,? ,? ,? )");
                preparedStatement.setString(1,tipoVehiculo);
                preparedStatement.setInt(2,precio);
                preparedStatement.setString(3,entrada);
                preparedStatement.setString(4,patente);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }
    }

    public void acutlizarPagoTotal(String patente, int value){
        {
            try {
                connection = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE vehiculo SET pago_total = ?" +
                        " WHERE patente = ? AND pago_total is null");
                preparedStatement.setInt(1,value);
                preparedStatement.setString(2,patente);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }

    }
    public int actualizarColumnaString(String patente, String value, String columna){
        int resultQuery = 0;
        {
            try { 
                connection = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE vehiculo SET "+columna+" = ? WHERE patente = ? AND "+columna+" is null");
                preparedStatement.setString(1,value);
                preparedStatement.setString(2,patente);
                resultQuery = preparedStatement.executeUpdate();
                preparedStatement.close();
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

        Vehicle vehiculo = new Vehicle();
        try {
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT patente,tipo_vehiculo,precio,entrada,salida" +
                    " FROM vehiculo" +
                    " WHERE patente = ? AND pago_total is null");
            preparedStatement.setString(1,patente);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            vehiculo = new Vehicle(
                        resultSet.getString("patente"),
                        resultSet.getString("tipo_vehiculo"),
                        resultSet.getInt("precio"),
                        resultSet.getString("entrada"),
                        resultSet.getString("salida")
            );
            preparedStatement.close();
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
        {
            try {
                connection = DbConnection.getInstance().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE precio SET "+columna+ " = ? WHERE id = 1;");
                preparedStatement.setInt(1,precioNuevo);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                exceptionError(e);
            }
        }
    }

    public ArrayList<Vehicle> buscarCoincidencia(String patente) {
        
        ArrayList<Vehicle> list = new ArrayList<>();
        
        try{
            connection = DbConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT patente, tipo_vehiculo, precio, entrada FROM vehiculo WHERE patente LIKE ? AND salida is NULL order by id desc");
            preparedStatement.setString(1, patente+"%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                
                Vehicle vehiculo = new Vehicle(resultSet.getString("patente"),
                            resultSet.getString("tipo_vehiculo"),
                                   resultSet.getInt("precio"),
                                        resultSet.getString("entrada"));
                
                list.add(vehiculo); 
            }
            resultSet.close();
            connection.close();
            return list;
        }
        catch (SQLException e){
            exceptionError(e);
        }
        
        return solicitarLista();
    }
}
