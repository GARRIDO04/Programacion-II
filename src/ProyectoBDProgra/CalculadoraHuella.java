
package ProyectoBDProgra;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CalculadoraHuella extends JFrame {

    private JTextField txtId, txtEdificio, txtCarreras, txtHoras;
    private JTextField txtLamparas, txtContactos, txtSanitarios, txtLavamanos, txtPet, txtPapel;
    private JLabel lblResultado;
    private JButton btnGuardar, btnBuscar, btnActualizar, btnEliminar, btnLimpiar;

    private final String URL = "jdbc:mysql://localhost:3306/huella_carbono_escuela";
    private final String USER = "root"; 
    private final String PASS = "";     

    public CalculadoraHuella() {
        setTitle("Calculadora de Huella de Carbono - Agua, Energía y Consumibles");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(10, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelForm.add(new JLabel("ID (Buscar/Actualizar/Eliminar):"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nombre del Edificio:"));
        txtEdificio = new JTextField("Edificio Principal");
        panelForm.add(txtEdificio);

        panelForm.add(new JLabel("Carreras que lo ocupan:"));
        txtCarreras = new JTextField("Ej. Sistemas / Administración");
        panelForm.add(txtCarreras);

        panelForm.add(new JLabel("Horas de actividad al día:"));
        txtHoras = new JTextField("14");
        panelForm.add(txtHoras);

        panelForm.add(new JLabel("Cant. Lámparas (Aprox 60W):"));
        txtLamparas = new JTextField("");
        panelForm.add(txtLamparas);

        panelForm.add(new JLabel("Cant. Contactos de luz:"));
        txtContactos = new JTextField("");
        panelForm.add(txtContactos);

        panelForm.add(new JLabel("Cant. Sanitarios:"));
        txtSanitarios = new JTextField("");
        panelForm.add(txtSanitarios);

        panelForm.add(new JLabel("Cant. Lavamanos:"));
        txtLavamanos = new JTextField("");
        panelForm.add(txtLavamanos);

        panelForm.add(new JLabel("Cant. Botellas PET (Mensual estimado):"));
        txtPet = new JTextField("");
        panelForm.add(txtPet);

        panelForm.add(new JLabel("Cant. Rollos de Papel (Mensual):"));
        txtPapel = new JTextField("");
        panelForm.add(txtPapel);

        lblResultado = new JLabel("Huella y Nivel de Impacto: ---");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 12));
        lblResultado.setForeground(new Color(0, 102, 51)); 

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Calcular y Guardar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        
        JPanel panelAbajo = new JPanel(new BorderLayout());
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        
        panelAbajo.add(lblResultado, BorderLayout.NORTH);
        panelAbajo.add(panelBotones, BorderLayout.CENTER);
        
        add(panelAbajo, BorderLayout.SOUTH);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnGuardar.addActionListener(e -> guardarRegistro());
        btnBuscar.addActionListener(e -> buscarRegistro());
        btnActualizar.addActionListener(e -> actualizarRegistro());
        btnEliminar.addActionListener(e -> eliminarRegistro());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private double calcularHuella() {
        try {
            int horas = Integer.parseInt(txtHoras.getText());
            int lamparas = Integer.parseInt(txtLamparas.getText());
            int contactos = Integer.parseInt(txtContactos.getText());
            int sanitarios = Integer.parseInt(txtSanitarios.getText());
            int lavamanos = Integer.parseInt(txtLavamanos.getText());
            
            int pet = Integer.parseInt(txtPet.getText());
            int papel = Integer.parseInt(txtPapel.getText());

            // LUZ: Horas dinámicas * 22 días hábiles al mes
            double energiaKWh = ((lamparas * 0.06) + (contactos * 0.2)) * horas * 22;
            double huellaLuz = energiaKWh * 0.434;

            // AGUA: Adaptable por horas de uso
            double usosSanitariosDiarios = sanitarios * 6 * (horas * 4); 
            double usosLavamanosDiarios = lavamanos * 2 * (horas * 5);
            double litrosMes = (usosSanitariosDiarios + usosLavamanosDiarios) * 22;
            double huellaAgua = (litrosMes / 1000.0) * 0.3;

            // PET y Papel (0.08kg CO2 por botella, 0.8kg CO2 por rollo)
            double huellaConsumibles = (pet * 0.08) + (papel * 0.8);

            return Math.round((huellaLuz + huellaAgua + huellaConsumibles) * 100.0) / 100.0;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Asegúrate de no dejar campos vacíos y usar solo números enteros.");
            return 0.0;
        }
    }

    private String calcularImpacto(double huella) {
        if (huella < 800) return "BAJO (Sustentable)";
        else if (huella < 2500) return "MEDIO (Requiere acciones)";
        else return "ALTO (Crítico)";
    }

    //CRUD

    private void guardarRegistro() {
        double huella = calcularHuella();
        if (huella == 0.0) return;
        String impacto = calcularImpacto(huella);

        String sql = "INSERT INTO registros_edificio (nombre_edificio, carreras, horas_actividad, lamparas, contactos, sanitarios, lavamanos, botellas_pet, rollos_papel, huella_mensual, nivel_impacto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtEdificio.getText());
            pstmt.setString(2, txtCarreras.getText());
            pstmt.setInt(3, Integer.parseInt(txtHoras.getText()));
            pstmt.setInt(4, Integer.parseInt(txtLamparas.getText()));
            pstmt.setInt(5, Integer.parseInt(txtContactos.getText()));
            pstmt.setInt(6, Integer.parseInt(txtSanitarios.getText()));
            pstmt.setInt(7, Integer.parseInt(txtLavamanos.getText()));
            pstmt.setInt(8, Integer.parseInt(txtPet.getText()));
            pstmt.setInt(9, Integer.parseInt(txtPapel.getText()));
            pstmt.setDouble(10, huella);
            pstmt.setString(11, impacto);
            
            pstmt.executeUpdate();
            lblResultado.setText(String.format("Huella: %.2f kg CO2 | Nivel: %s", huella, impacto));
            JOptionPane.showMessageDialog(this, "Registro del edificio guardado exitosamente.");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de BD: " + ex.getMessage());
        }
    }

    private void buscarRegistro() {
        String sql = "SELECT * FROM registros_edificio WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(txtId.getText()));
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                txtEdificio.setText(rs.getString("nombre_edificio"));
                txtCarreras.setText(rs.getString("carreras"));
                txtHoras.setText(String.valueOf(rs.getInt("horas_actividad")));
                txtLamparas.setText(String.valueOf(rs.getInt("lamparas")));
                txtContactos.setText(String.valueOf(rs.getInt("contactos")));
                txtSanitarios.setText(String.valueOf(rs.getInt("sanitarios")));
                txtLavamanos.setText(String.valueOf(rs.getInt("lavamanos")));
                txtPet.setText(String.valueOf(rs.getInt("botellas_pet")));
                txtPapel.setText(String.valueOf(rs.getInt("rollos_papel")));
                lblResultado.setText(String.format("Huella: %.2f kg CO2 | Nivel: %s", rs.getDouble("huella_mensual"), rs.getString("nivel_impacto")));
            } else {
                JOptionPane.showMessageDialog(this, "ID no encontrado.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido para buscar.");
        }
    }

    private void actualizarRegistro() {
        double huella = calcularHuella();
        if (huella == 0.0) return;
        String impacto = calcularImpacto(huella);
        
        String sql = "UPDATE registros_edificio SET nombre_edificio=?, carreras=?, horas_actividad=?, lamparas=?, contactos=?, sanitarios=?, lavamanos=?, botellas_pet=?, rollos_papel=?, huella_mensual=?, nivel_impacto=? WHERE id=?";
        
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtEdificio.getText());
            pstmt.setString(2, txtCarreras.getText());
            pstmt.setInt(3, Integer.parseInt(txtHoras.getText()));
            pstmt.setInt(4, Integer.parseInt(txtLamparas.getText()));
            pstmt.setInt(5, Integer.parseInt(txtContactos.getText()));
            pstmt.setInt(6, Integer.parseInt(txtSanitarios.getText()));
            pstmt.setInt(7, Integer.parseInt(txtLavamanos.getText()));
            pstmt.setInt(8, Integer.parseInt(txtPet.getText()));
            pstmt.setInt(9, Integer.parseInt(txtPapel.getText()));
            pstmt.setDouble(10, huella);
            pstmt.setString(11, impacto);
            pstmt.setInt(12, Integer.parseInt(txtId.getText()));
            
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                lblResultado.setText(String.format("Huella: %.2f kg CO2 | Nivel: %s", huella, impacto));
                JOptionPane.showMessageDialog(this, "Registro actualizado.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error, verifica el ID y los datos.");
        }
    }

    private void eliminarRegistro() {
        String sql = "DELETE FROM registros_edificio WHERE id=?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(txtId.getText()));
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Registro eliminado.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "ID no encontrado.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido para eliminar.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtEdificio.setText("Edificio ");
        txtCarreras.setText("Ej. Sistemas / Administración");
        txtHoras.setText("14");
        txtLamparas.setText("");
        txtContactos.setText("");
        txtSanitarios.setText("");
        txtLavamanos.setText("");
        txtPet.setText("");
        txtPapel.setText("");
        lblResultado.setText("Huella y Nivel de Impacto: ---");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraHuella().setVisible(true);
        });
    }
}
