package ConexionSeparada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Combinada extends JFrame {

    // Componentes
    JLabel lblNombre, lblPrecio;
    JTextField txtNombre, txtPrecio;
    JButton btnGuardar, btnMostrar;

    public Combinada() {

        setTitle("Registro de Productos");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 10, 10));

        getContentPane().setBackground(new Color(40, 44, 52));
        
        lblNombre = new JLabel("Nombre:");
        lblPrecio = new JLabel("Precio:");

        txtNombre = new JTextField();
        txtPrecio = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnMostrar = new JButton("Mostrar Stickers");

        add(lblNombre);
        lblNombre.setForeground(Color.CYAN);
        add(txtNombre);

        add(lblPrecio);
        lblPrecio.setForeground(Color.MAGENTA);
        add(txtPrecio);

        add(btnGuardar);
        add(btnMostrar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarProducto();
            }
        });        
        
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarProductos ventanaTabla = new MostrarProductos();
                ventanaTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaTabla.setVisible(true);
            }
        });
    }

    public void insertarProducto() {

        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());

        String url = "jdbc:mysql://localhost:3306/stickersmundial";
        String usuario = "root";
        String contraseña = "";

        try {

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            String sql = "INSERT INTO precios(nombre, precio) VALUES (?, ?)";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto guardado correctamente");

            txtNombre.setText("");
            txtPrecio.setText("");

            conexion.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al guardar");
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {

        Combinada ventana = new Combinada();
        ventana.setVisible(true);
    }
}