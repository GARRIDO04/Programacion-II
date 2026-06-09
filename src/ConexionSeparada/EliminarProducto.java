package ConexionSeparada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EliminarProducto  extends JFrame {

    JLabel lblId;
    JTextField txtId;
    JButton btnEliminar;

    public EliminarProducto() {

        setTitle("Eliminar Producto");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2, 2, 10, 10));

        // Componentes
        lblId = new JLabel("ID del Producto:");

        txtId = new JTextField();

        btnEliminar = new JButton("Eliminar");

        // Agregar componentes
        add(lblId);
        add(txtId);

        add(new JLabel(""));
        add(btnEliminar);

        // Evento botón
        btnEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarProducto();
            }
        });
    }

    // Método DELETE
    public void eliminarProducto() {

        String id = txtId.getText();

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            String sql = "DELETE FROM precios WHERE id = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            int filas = ps.executeUpdate();

            if (filas > 0) {

                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");

            } else {

                JOptionPane.showMessageDialog(null, "No existe el ID");
            }

            // Limpiar campo
            txtId.setText("");

            conexion.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar");
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {

        EliminarProducto ventana = new EliminarProducto();
        ventana.setVisible(true);
    }
}
