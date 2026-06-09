package C1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPromedios extends JFrame implements ActionListener {

    // Etiquetas
    private JLabel lblInstitucion, lblLogo, lblNombre, lblSexo;
    private JLabel lblMateria1, lblMateria2, lblMateria3;
    private JLabel lblEstado1, lblEstado2, lblEstado3;
    private JLabel lblPromedio, lblAprobadas, lblNoAprobadas;

    // Cajas de texto
    private JTextField txtNombre, txtMateria1, txtMateria2, txtMateria3;
    private JTextField txtPromedio, txtAprobadas, txtNoAprobadas;

    // Radio buttons
    private JRadioButton rbF, rbM;
    private ButtonGroup grupoSexo;

    // Botones
    private JButton btnCalcular, btnLimpiar;

    public VentanaPromedios() {
        setTitle("Sistema de Calificaciones");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== Encabezado =====
        lblInstitucion = new JLabel("TESCI");
        lblInstitucion.setBounds(80, 30, 250, 40);
        lblInstitucion.setFont(new Font("Arial", Font.BOLD, 40));
        lblInstitucion.setOpaque(true);
        //lblInstitucion.setBackground(new Color(220, 235, 250));
        lblInstitucion.setBackground(new Color(0, 0, 0));
        lblInstitucion.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInstitucion);

        lblLogo = new JLabel("LOGO TESCI");
        lblLogo.setBounds(470, 30, 150, 70);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 20));
        lblLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblLogo);

        // ===== Nombre =====
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(70, 120, 100, 30);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        //lblNombre.setForeground(Color.DARK_GRAY);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(170, 120, 220, 30);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        add(txtNombre);

        // ===== Sexo =====
        lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(430, 120, 60, 30);
        lblSexo.setFont(new Font("Arial", Font.BOLD, 16));
        //lblSexo.setForeground(new Color(120, 120, 120));
        lblSexo.setForeground(new Color(0, 0, 0));
        add(lblSexo);

        rbF = new JRadioButton("F");
        rbF.setBounds(530, 110, 50, 30);
        rbF.setBackground(Color.WHITE);
        rbF.setFont(new Font("Arial", Font.PLAIN, 15));
        add(rbF);

        rbM = new JRadioButton("M");
        rbM.setBounds(530, 140, 50, 30);//120 (y)
        rbM.setBackground(Color.WHITE);
        rbM.setFont(new Font("Arial", Font.PLAIN, 15));
        add(rbM);

        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbF);
        grupoSexo.add(rbM);

        // ===== Materias =====
        lblMateria1 = new JLabel("Materia 1:");
        lblMateria1.setBounds(70, 180, 100, 30);
        lblMateria1.setFont(new Font("Arial", Font.BOLD, 16));
        lblMateria1.setForeground(new Color(100, 149, 237)); // azul claro
        add(lblMateria1);

        txtMateria1 = new JTextField();
        txtMateria1.setBounds(170, 180, 100, 30);
        add(txtMateria1);

        lblEstado1 = new JLabel("");
        lblEstado1.setBounds(290, 180, 130, 30);
        lblEstado1.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblEstado1);

        lblMateria2 = new JLabel("Materia 2:");
        lblMateria2.setBounds(70, 230, 100, 30);
        lblMateria2.setFont(new Font("Arial", Font.BOLD, 16));
        lblMateria2.setForeground(new Color(255, 105, 180)); // rosa
        add(lblMateria2);

        txtMateria2 = new JTextField();
        txtMateria2.setBounds(170, 230, 100, 30);
        add(txtMateria2);

        lblEstado2 = new JLabel("");
        lblEstado2.setBounds(290, 230, 130, 30);
        lblEstado2.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblEstado2);

        lblMateria3 = new JLabel("Materia 3:");
        lblMateria3.setBounds(70, 280, 100, 30);
        lblMateria3.setFont(new Font("Arial", Font.BOLD, 16));
        lblMateria3.setForeground(Color.GRAY);
        add(lblMateria3);

        txtMateria3 = new JTextField();
        txtMateria3.setBounds(170, 280, 100, 30);
        add(txtMateria3);

        lblEstado3 = new JLabel("");
        lblEstado3.setBounds(290, 280, 130, 30);
        lblEstado3.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblEstado3);

        // ===== Botones =====
        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(500, 200, 130, 45);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setBackground(Color.GREEN);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(500, 280, 130, 45);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        btnLimpiar.setBackground(Color.YELLOW);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        // ===== Resultados =====
        lblPromedio = new JLabel("Promedio:");
        lblPromedio.setBounds(170, 350, 100, 30);
        lblPromedio.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblPromedio);

        txtPromedio = new JTextField();
        txtPromedio.setBounds(250, 350, 100, 30);
        txtPromedio.setEditable(false);
        add(txtPromedio);

        lblAprobadas = new JLabel("Materias Aprobadas:");
        lblAprobadas.setBounds(70, 400, 200, 30);
        lblAprobadas.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblAprobadas);

        txtAprobadas = new JTextField();
        txtAprobadas.setBounds(270, 400, 60, 30);
        txtAprobadas.setEditable(false);
        add(txtAprobadas);

        lblNoAprobadas = new JLabel("Materias No Aprobadas:");
        lblNoAprobadas.setBounds(70, 440, 220, 30);
        lblNoAprobadas.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblNoAprobadas);

        txtNoAprobadas = new JTextField();
        txtNoAprobadas.setBounds(270, 440, 60, 30);
        txtNoAprobadas.setEditable(false);
        add(txtNoAprobadas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            calcular();
        }

        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    private void calcular() {
        try {
            double m1 = Double.parseDouble(txtMateria1.getText());
            double m2 = Double.parseDouble(txtMateria2.getText());
            double m3 = Double.parseDouble(txtMateria3.getText());

            // Validación de rango
            if (m1 < 0 || m1 > 10 || m2 < 0 || m2 > 10 || m3 < 0 || m3 > 10) {
                JOptionPane.showMessageDialog(this,
                        "Las calificaciones deben estar entre 0 y 10.");
                return;
            }

            int aprobadas = 0;
            int noAprobadas = 0;

            // Materia 1
            if (m1 >= 6) {
                lblEstado1.setText("Acreditada");
                lblEstado1.setForeground(new Color(0, 128, 0));//verde
                aprobadas++;
            } else {
                lblEstado1.setText("No acreditada");
                lblEstado1.setForeground(Color.RED);
                noAprobadas++;
            }

            // Materia 2
            if (m2 >= 6) {
                lblEstado2.setText("Acreditada");
                lblEstado2.setForeground(new Color(0, 128, 0));
                aprobadas++;
            } else {
                lblEstado2.setText("No acreditada");
                lblEstado2.setForeground(Color.RED);
                noAprobadas++;
            }

            // Materia 3
            if (m3 >= 6) {
                lblEstado3.setText("Acreditada");
                lblEstado3.setForeground(new Color(0, 128, 0));
                aprobadas++;
            } else {
                lblEstado3.setText("No acreditada");
                lblEstado3.setForeground(Color.RED);
                noAprobadas++;
            }

            double promedio = (m1 + m2 + m3) / 3.0;

            txtPromedio.setText(String.format("%.2f", promedio));
            txtAprobadas.setText(String.valueOf(aprobadas));
            txtNoAprobadas.setText(String.valueOf(noAprobadas));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ingresa solo números válidos en las materias.");
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtMateria1.setText("");
        txtMateria2.setText("");
        txtMateria3.setText("");
        txtPromedio.setText("");
        txtAprobadas.setText("");
        txtNoAprobadas.setText("");

        lblEstado1.setText("");
        lblEstado2.setText("");
        lblEstado3.setText("");

        grupoSexo.clearSelection();

        txtNombre.requestFocus();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPromedios().setVisible(true);
        });
    }*/
    
    public static void main(String[] args) {
        new VentanaPromedios().setVisible(true);
    }
}
