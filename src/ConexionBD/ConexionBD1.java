package ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.math.BigDecimal;

public class ConexionBD1 extends JFrame {

    // CONEXIÓN para la base de datos donde esta el puerto 3306
    String url = "jdbc:mysql://localhost:3306/inventario";
    String usuario = "root";
    String password = "";

    Connection conexion;

    // PARTES
    JTextField txtId, txtNombre, txtPrecio;
    JButton btnGuardar, btnActualizar, btnEliminar;
    JTable tabla;
    DefaultTableModel modelo;

    //Constructor
    public ConexionBD1() {

        // VENTANA diseño 
        setTitle("Inventario de Productos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PANEL PRINCIPAL es el que guarda los componentes de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));
        panel.setBackground(Color.WHITE);

        // FORMULARIO EN ESTA PARTE VAN LOS CAMPOS 

        JPanel formulario = new JPanel(); 
        formulario.setLayout(new GridLayout(4,2,10,10));//Crea una cuadricula con 4 filas y 2 colimnas

        formulario.setBorder(
                BorderFactory.createTitledBorder("Productos Tienda")
        );

        // ETIQUETAS
        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblPrecio = new JLabel("Precio:");

        // CAMPOS es la caja donde el usuario escribe 
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtPrecio = new JTextField();

        // BOTONES
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        // COLOR BOTONES
        btnGuardar.setBackground(new Color(0,153,76));
        btnGuardar.setForeground(Color.WHITE);

        btnActualizar.setBackground(new Color(0,102,204));
        btnActualizar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(204,0,0));
        btnEliminar.setForeground(Color.WHITE);

        // AGREGAR componentes dentro del formulario 
        formulario.add(lblId);
        formulario.add(txtId);

        formulario.add(lblNombre);
        formulario.add(txtNombre);

        formulario.add(lblPrecio);
        formulario.add(txtPrecio);

        formulario.add(btnGuardar);
        formulario.add(btnActualizar);

        // PANEL BOTÓN ELIMINAR
        JPanel panelEliminar = new JPanel();
        panelEliminar.add(btnEliminar);

        // TABLA modelos de datos para la tabla 

        modelo = new DefaultTableModel();
        //agregar columnas 
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        // AGREGAR AL PANEL
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelEliminar, BorderLayout.SOUTH);

        add(panel);

        // EVENTOS
        btnGuardar.addActionListener(e -> insertarProducto());

        btnActualizar.addActionListener(e -> actualizarProducto());

        btnEliminar.addActionListener(e -> eliminarProducto());

        // CLICK TABLA
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int fila = tabla.getSelectedRow();

                txtId.setText(tabla.getValueAt(fila,0).toString());

                txtNombre.setText(tabla.getValueAt(fila,1).toString());

                txtPrecio.setText(tabla.getValueAt(fila,2).toString());
            }
        });

        conectar();

        mostrarProductos();
    }

    // CONEXIÓN
    public void conectar() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);
            
            JOptionPane.showMessageDialog(null, "Conexion EXITOSA");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error conexión");

            e.printStackTrace();
        }
    }

    // INSERTAR Productos
    
    public void insertarProducto() {

        try {

            String nombre = txtNombre.getText();

            BigDecimal precio = new BigDecimal (txtPrecio.getText());

            String sql = "INSERT INTO productos(nombre,precio) VALUES(?,?)";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, nombre);

            ps.setBigDecimal(2, precio);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto guardado");

            limpiar();

            mostrarProductos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error guardar");

            e.printStackTrace();
        }
    }

    // MOSTRAR

    public void mostrarProductos() {

        try {

            modelo.setRowCount(0);

            String sql = "SELECT * FROM productos";

            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                modelo.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("precio")
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error mostrar");

            e.printStackTrace();
        }
    }

    // ACTUALIZAR

    public void actualizarProducto() {

        try {

            int id = Integer.parseInt(txtId.getText());

            String nombre = txtNombre.getText();

            BigDecimal precio = new BigDecimal (txtPrecio.getText());

            String sql = "UPDATE productos SET nombre=?, precio=? WHERE id=?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, nombre);

            ps.setBigDecimal(2, precio);

            ps.setInt(3, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto actualizado");

            limpiar();

            mostrarProductos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error actualizar");

            e.printStackTrace();
        }
    }

    // ELIMINAR

    public void eliminarProducto() {

        try {

            int id = Integer.parseInt(txtId.getText());

            String sql = "DELETE FROM productos WHERE id=?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto eliminado");

            limpiar();

            mostrarProductos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error eliminar");

            e.printStackTrace();
        }
    }

    // LIMPIAR

    public void limpiar(){

        txtId.setText("");

        txtNombre.setText("");

        txtPrecio.setText("");
    }


    // Metodo MAIN

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ConexionBD1().setVisible(true);
        });
    }
}

