package ProyectoIIProgra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/inventariohardwaresoftware";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion existosa");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
    public static void main(String args[]){
        getConexion();
    }
}


