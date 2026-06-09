package ConexionSeparada;
 
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
    }
}