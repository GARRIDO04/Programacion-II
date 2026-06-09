package C1;

import javax.swing.*;
import java.awt.*;

public class ControlEscolar{
    private JFrame frame;

    private JTextField txtNombre, txtMateria1, txtMateria2, txtMateria3;
    private JTextField txtPromedio, txtAprobadas, txtNoAprobadas;

    private JLabel lblEstado1, lblEstado2, lblEstado3;

    private JRadioButton rbF, rbM;
    private ButtonGroup grupoSexo;

    public ControlEscolar(){

        frame = new JFrame("Sistema de Calificaciones");
        frame.setSize(750, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        JLabel titulo = new JLabel("\nREGISTRO CALIFICACIONES");
        titulo.setBounds(300,40,200,50);
        frame.add(titulo);

        // ===== Nombre =====
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(70, 120, 100, 30);
        frame.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(170, 120, 200, 30);
        frame.add(txtNombre);

        // ===== Sexo =====
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(400, 120, 60, 30);
        frame.add(lblSexo);

        rbF = new JRadioButton("F");
        rbF.setBounds(460, 120, 50, 30);
        frame.add(rbF);

        rbM = new JRadioButton("M");
        rbM.setBounds(520, 120, 50, 30);
        frame.add(rbM);

        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbF);
        grupoSexo.add(rbM);

        // ===== Materias =====
        JLabel lblM1 = new JLabel("Materia 1:");
        lblM1.setBounds(70, 180, 100, 30);
        frame.add(lblM1);

        txtMateria1 = new JTextField();
        txtMateria1.setBounds(170, 180, 100, 30);
        frame.add(txtMateria1);

        lblEstado1 = new JLabel("");
        lblEstado1.setBounds(280, 180, 120, 30);
        frame.add(lblEstado1);

        JLabel lblM2 = new JLabel("Materia 2:");
        lblM2.setBounds(70, 230, 100, 30);
        frame.add(lblM2);

        txtMateria2 = new JTextField();
        txtMateria2.setBounds(170, 230, 100, 30);
        frame.add(txtMateria2);

        lblEstado2 = new JLabel("");
        lblEstado2.setBounds(280, 230, 120, 30);
        frame.add(lblEstado2);

        JLabel lblM3 = new JLabel("Materia 3:");
        lblM3.setBounds(70, 280, 100, 30);
        frame.add(lblM3);

        txtMateria3 = new JTextField();
        txtMateria3.setBounds(170, 280, 100, 30);
        frame.add(txtMateria3);

        lblEstado3 = new JLabel("");
        lblEstado3.setBounds(280, 280, 120, 30);
        frame.add(lblEstado3);

        // ===== Botón Calcular =====
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(500, 200, 120, 40);
        frame.add(btnCalcular);

        // ===== Botón Limpiar =====
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(500, 260, 120, 40);
        frame.add(btnLimpiar);

        // ===== Resultados =====
        JLabel lblPromedio = new JLabel("Promedio:");
        lblPromedio.setBounds(70, 340, 100, 30);
        frame.add(lblPromedio);

        txtPromedio = new JTextField();
        txtPromedio.setBounds(170, 340, 100, 30);
        txtPromedio.setEditable(false);
        frame.add(txtPromedio);

        JLabel lblAprobadas = new JLabel("Aprobadas:");
        lblAprobadas.setBounds(70, 380, 100, 30);
        frame.add(lblAprobadas);

        txtAprobadas = new JTextField();
        txtAprobadas.setBounds(170, 380, 50, 30);
        txtAprobadas.setEditable(false);
        frame.add(txtAprobadas);

        JLabel lblNoAprobadas = new JLabel("No aprobadas:");
        lblNoAprobadas.setBounds(70, 420, 120, 30);
        frame.add(lblNoAprobadas);

        txtNoAprobadas = new JTextField();
        txtNoAprobadas.setBounds(200, 420, 50, 30);
        txtNoAprobadas.setEditable(false);
        frame.add(txtNoAprobadas);

        // ===== Eventos =====
        btnCalcular.addActionListener(e -> calcular());
        btnLimpiar.addActionListener(e -> limpiar());

        frame.setVisible(true);
    }

    private void calcular() {
        try {
            double m1 = Double.parseDouble(txtMateria1.getText());
            double m2 = Double.parseDouble(txtMateria2.getText());
            double m3 = Double.parseDouble(txtMateria3.getText());

            int aprobadas = 0;
            int noAprobadas = 0;

            if (m1 >= 6) {
                lblEstado1.setText("Acreditada");
                aprobadas++;
            } else {
                lblEstado1.setText("No acreditada");
                noAprobadas++;
            }

            if (m2 >= 6) {
                lblEstado2.setText("Acreditada");
                aprobadas++;
            } else {
                lblEstado2.setText("No acreditada");
                noAprobadas++;
            }

            if (m3 >= 6) {
                lblEstado3.setText("Acreditada");
                aprobadas++;
            } else {
                lblEstado3.setText("No acreditada");
                noAprobadas++;
            }

            double promedio = (m1 + m2 + m3) / 3;

            txtPromedio.setText(String.format("%.2f", promedio));
            txtAprobadas.setText(String.valueOf(aprobadas));
            txtNoAprobadas.setText(String.valueOf(noAprobadas));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Ingresa números válidos");
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
    }

    public static void main(String[] args) {
        new ControlEscolar();
    }
}