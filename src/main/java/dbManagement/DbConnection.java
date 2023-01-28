package dbManagement;

import bussinesLogic.factorys.VehicleFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DbConnection {
    private static DbConnection instance;

    private DbConnection() {

    }
    public static DbConnection getInstance(){
        if (instance == null)
            instance = new DbConnection();
        return instance;
    }

    public Connection getConnection(){
        String url = "jdbc:mysql://127.0.0.1:3306/estacionamiento_db?";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection;
        }
        catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage(), "Error Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
}
