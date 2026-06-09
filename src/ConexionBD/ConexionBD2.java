package ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.math.BigDecimal;

public class ConexionBD2 extends JFrame {

    String url = "jdbc:mysql://localhost:3306/inventario";
    String usuario = "root";
    String password = "";
    Connection conexion;

    int idSeleccionado = -1; 

    JTextField txtNombre, txtPrecio, txtStock;
    JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;
    JTable tabla;
    DefaultTableModel modelo;

    public ConexionBD2() {

        setTitle("Inventario de Productos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));
        panel.setBackground(Color.WHITE);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(5,2,10,10));
        formulario.setBorder(BorderFactory.createTitledBorder("Productos"));

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblPrecio = new JLabel("Precio $:");
        JLabel lblStock = new JLabel("Stock:");

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar Campos");

        btnGuardar.setBackground(new Color(0,153,76));
        btnGuardar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(0,102,204));
        btnActualizar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(204,0,0));
        btnEliminar.setForeground(Color.WHITE);
        
        btnLimpiar.setBackground(new Color(128,128,128));
        btnLimpiar.setForeground(Color.WHITE);

        formulario.add(lblNombre);
        formulario.add(txtNombre);
        formulario.add(lblPrecio);
        formulario.add(txtPrecio);
        formulario.add(lblStock);
        formulario.add(txtStock);
        formulario.add(btnGuardar);
        formulario.add(btnActualizar);
        
        formulario.add(new JLabel(""));
        formulario.add(btnLimpiar);

        JPanel panelEliminar = new JPanel();
        panelEliminar.add(btnEliminar);

        modelo = new DefaultTableModel();
        modelo.addColumn("N°"); 
        modelo.addColumn("ID BD");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio ($)");
        modelo.addColumn("Stock");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelEliminar, BorderLayout.SOUTH);

        add(panel);

        btnGuardar.addActionListener(e -> insertarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiar());

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                

                idSeleccionado = Integer.parseInt(tabla.getValueAt(fila,1).toString());
                
                txtNombre.setText(tabla.getValueAt(fila,2).toString());
                txtPrecio.setText(tabla.getValueAt(fila,3).toString());
                txtStock.setText(tabla.getValueAt(fila,4).toString());
            }
        });

        conectar();
        
        mostrarProductos();
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "<html><font color='red'><b>ERROR</b></font> de conexión</html>");
            e.printStackTrace();
        }
    }
    
    public void mostrarProductos() {
        try {
            modelo.setRowCount(0);
            String sql = "SELECT * FROM productos";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int contadorVisual = 1;

            while(rs.next()){
                modelo.addRow(new Object[]{
                        contadorVisual++,
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("stock")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar");
            e.printStackTrace();
        }
    }

    // --- MÉTODOS CRUD ---

    public void insertarProducto() {
        try {
            String nombre = txtNombre.getText();
            BigDecimal precio = new BigDecimal(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            String sql = "INSERT INTO productos(nombre, precio, stock) VALUES(?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setBigDecimal(2, precio);
            ps.setInt(3, stock);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto guardado");
            limpiar();
            mostrarProductos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar. Verifica los datos.");
            e.printStackTrace();
        }
    }

    public void actualizarProducto() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto de la tabla primero.");
            return;
        }

        try {
            String nombre = txtNombre.getText();
            BigDecimal precio = new BigDecimal(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setBigDecimal(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, idSeleccionado);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto actualizado");
            limpiar();
            mostrarProductos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar");
            e.printStackTrace();
        }
    }

    public void eliminarProducto() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto de la tabla primero.");
            return;
        }

        try {
            String sql = "DELETE FROM productos WHERE id=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSeleccionado);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto eliminado");
            limpiar();
            mostrarProductos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar");
            e.printStackTrace();
        }
    }

    public void limpiar(){
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        idSeleccionado = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConexionBD2().setVisible(true);
        });
    }
}