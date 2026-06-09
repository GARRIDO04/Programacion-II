package C2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IndiceMasaCorporal extends JFrame implements ActionListener {

    // Etiquetas
    private JLabel lblIMC, lblLogo, lblAltura, lblPeso;
    private JLabel lblResultado;
    private JLabel lblEstado1, lblEstado2, lblEstado3;

    // Cajas de texto
    private JTextField txtAltura, txtPeso;
    private JTextField txtResultado;

    // Botones
    private JButton btnCalcular, btnLimpiar;

    public IndiceMasaCorporal() {
        setTitle("Calculadora de IMC");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Título
        lblIMC = new JLabel("Calculadora de IMC");
        lblIMC.setBounds(20, 40, 260, 50);
        lblIMC.setFont(new Font("Arial", Font.BOLD, 25));
        lblIMC.setOpaque(true);
       // lblIMC.setBackground(Color.BLACK);
        lblIMC.setForeground(Color.BLACK);
        lblIMC.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIMC);

        // Logo
        lblLogo = new JLabel("LOGO");
        lblLogo.setBounds(300, 20, 80, 80);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 20));
        lblLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblLogo);

        // Altura
        lblAltura = new JLabel("Altura:");
        lblAltura.setBounds(80, 150, 100, 30);
        lblAltura.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblAltura);

        txtAltura = new JTextField();
        txtAltura.setBounds(160, 150, 160, 30);
        txtAltura.setFont(new Font("Arial", Font.PLAIN, 15));
        add(txtAltura);

        // Peso
        lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(80, 250, 100, 30);
        lblPeso.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(160, 250, 160, 30);
        txtPeso.setFont(new Font("Arial", Font.PLAIN, 15));
        add(txtPeso);

        // Estados
        lblEstado1 = new JLabel("");
        lblEstado1.setBounds(420, 120, 200, 30);
        lblEstado1.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblEstado1);

        lblEstado2 = new JLabel("");
        lblEstado2.setBounds(420, 160, 200, 30);
        lblEstado2.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblEstado2);

        lblEstado3 = new JLabel("");
        lblEstado3.setBounds(420, 200, 200, 30);
        lblEstado3.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblEstado3);

        // Botones
        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(80, 350, 130, 45);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(230, 350, 130, 45);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        // Resultado
        lblResultado = new JLabel("Resultado (IMC) :");
        lblResultado.setBounds(420, 70, 150, 30);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(560, 70, 100, 30);
        txtResultado.setEditable(false);
        add(txtResultado);
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
            double altura = Double.parseDouble(txtAltura.getText());
            double peso = Double.parseDouble(txtPeso.getText());

            double imc = peso / (altura * altura);

            txtResultado.setText(String.format("%.2f", imc));

            lblEstado1.setText("");
            lblEstado2.setText("");
            lblEstado3.setText("");

            if (imc >= 18.5 && imc <= 24.9) {
                lblEstado1.setText("Peso normal");
                lblEstado1.setForeground(new Color(0, 128, 0));
            } else if (imc >= 25.0 && imc <= 29.9) {
                lblEstado2.setText("Sobrepeso");
                lblEstado2.setForeground(Color.ORANGE);
            } else if (imc >= 30.0) {
                lblEstado3.setText("Obesidad");
                lblEstado3.setForeground(Color.RED);
            } else {
                lblEstado1.setText("Bajo peso");
                lblEstado1.setForeground(Color.BLUE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa valores numéricos válidos");
        }
    }

    private void limpiar() {
        txtPeso.setText("");
        txtAltura.setText("");
        txtResultado.setText("");

        lblEstado1.setText("");
        lblEstado2.setText("");
        lblEstado3.setText("");

        txtAltura.requestFocus();
    }

    public static void main(String[] args) {
        new IndiceMasaCorporal().setVisible(true);
    }
}
