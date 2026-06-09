package ConexionSeparada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertarProducto {
     public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {
            // Conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Consulta SQL
            String sql = "INSERT INTO precios(nombre, precio) VALUES (?, ?)";

            // Preparar consulta
            PreparedStatement ps = conexion.prepareStatement(sql);

            // Valores
            ps.setString(1, "Lionel Messi");
            ps.setDouble(2, 1500);

            // Ejecutar
            ps.executeUpdate();

            System.out.println("Producto insertado correctamente");

            // Cerrar conexión
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
    }
}
    

