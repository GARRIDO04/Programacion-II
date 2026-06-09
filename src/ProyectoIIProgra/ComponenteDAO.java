package ProyectoIIProgra;

import java.sql.*;

public class ComponenteDAO {

    // CREATE: Agrega un nuevo componente al inventario
    public boolean agregarComponente(String nombre, String tipo, int stock, int stockMinimo) {
        String sql = "INSERT INTO Componentes (nombre, tipo, stock, stock_minimo) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, tipo);
            ps.setInt(3, stock);
            ps.setInt(4, stockMinimo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar: " + e.getMessage());
            return false;
        }
    }

    // READ: Lee todos los componentes (Ideal para llenar un JTable)
    public void listarComponentes() {
        String sql = "SELECT * FROM Componentes";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("--- Inventario Actual ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | " + 
                                   rs.getString("nombre") + " | Stock: " + 
                                   rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
    }

    // USO DEL PROCEDIMIENTO ALMACENADO Y TRIGGERS
    public void registrarUso(int idProyecto, int idComponente, int cantidad) {
        String sql = "{CALL sp_RegistrarUso(?, ?, ?)}"; // Llamada al procedimiento
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idProyecto);
            cs.setInt(2, idComponente);
            cs.setInt(3, cantidad);
            cs.execute();
            System.out.println("Uso registrado exitosamente. Stock actualizado.");
        } catch (SQLException e) {
            // ¡AQUÍ SE ATRAPA EL ERROR DEL TRIGGER trg_ValidarStockNegativo!
            System.err.println("Operación rechazada por la Base de Datos: " + e.getMessage());
        }
    }
    
    // UPDATE y DELETE (Implementaciones básicas del CRUD)
    public boolean eliminarComponente(int id) {
        String sql = "DELETE FROM Componentes WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
