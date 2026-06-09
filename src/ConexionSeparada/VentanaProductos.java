
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class VentanaProductos extends JFrame {

    // Componentes
    JLabel lblNombre, lblPrecio;
    JTextField txtNombre, txtPrecio;
    JButton btnGuardar;

    public VentanaProductos() {

        // Configuración ventana
        setTitle("Registro de Productos");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2, 10, 10));

         getContentPane().setBackground(new Color(40, 44, 52));
        // Crear componentes
        lblNombre = new JLabel("Nombre:");
        lblPrecio = new JLabel("Precio:");

        txtNombre = new JTextField();
        txtPrecio = new JTextField();

        btnGuardar = new JButton("Guardar");

        // Agregar componentes
        add(lblNombre);
        lblNombre.setForeground(Color.CYAN);
        add(txtNombre);


        add(lblPrecio);
         lblPrecio.setForeground(Color.MAGENTA);
        add(txtPrecio);

        add(new JLabel(""));
        add(btnGuardar);

        // Evento botón
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                insertarProducto();
            }
        });
    }

    // Método para insertar
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

            // Limpiar campos
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

        VentanaProductos ventana = new VentanaProductos();
        ventana.setVisible(true);
    }
}
