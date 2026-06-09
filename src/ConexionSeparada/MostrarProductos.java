package ConexionSeparada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class MostrarProductos extends JFrame {

    JTable tabla;
    DefaultTableModel modelo;
    JScrollPane scroll;

    public MostrarProductos() {

        setTitle("Mostrar Productos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear tabla
        tabla = new JTable();

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");

        tabla.setModel(modelo);

        scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        // Cargar datos
        mostrarDatos();
    }

    public void mostrarDatos() {

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement st = conexion.createStatement();

            String sql = "SELECT * FROM precios";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String precio = rs.getString("precio");

                modelo.addRow(new Object[]{id, nombre, precio});
            }

            conexion.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar datos");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MostrarProductos ventana = new MostrarProductos();
        ventana.setVisible(true);
    }
}