package ConexionSeparada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ActualizarProducto extends JFrame {

    JLabel lblId, lblNombre, lblPrecio;

    JTextField txtId, txtNombre, txtPrecio;

    JButton btnActualizar;

    public ActualizarProducto() {

        setTitle("Actualizar Producto");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 10, 10));

        // Etiquetas
        lblId = new JLabel("ID:");
        lblNombre = new JLabel("Nuevo Nombre:");
        lblPrecio = new JLabel("Nuevo Precio:");

        // Cajas de texto
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtPrecio = new JTextField();

        // Botón
        btnActualizar = new JButton("Actualizar");

        // Agregar componentes
        add(lblId);
        add(txtId);

        add(lblNombre);
        add(txtNombre);

        add(lblPrecio);
        add(txtPrecio);

        add(new JLabel(""));
        add(btnActualizar);

        // Evento botón
        btnActualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                actualizarProducto();
            }
        });
    }

    // Método UPDATE
    public void actualizarProducto() {

        String id = txtId.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            String sql = "UPDATE precios SET nombre = ?, precio = ? WHERE id = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, Integer.parseInt(id));

            int filas = ps.executeUpdate();

            if (filas > 0) {

                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");

            } else {

                JOptionPane.showMessageDialog(null, "No existe el ID");
            }

            // Limpiar campos
            txtId.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");

            conexion.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al actualizar");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ActualizarProducto ventana = new ActualizarProducto();
        ventana.setVisible(true);
    }
}
