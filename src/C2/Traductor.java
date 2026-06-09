package C2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Traductor extends JFrame implements ActionListener {

    /*private*/ JTextField txtPE1, txtPE2, txtPE3, txtPE4;
    /*private*/ JLabel lblTitulo,lblPE, lblPI, lblPI1, lblPI2, lblPI3, lblPI4;
    /*private*/ JButton btnTraducir, btnLimpiar;

    public Traductor() {
        setTitle("Traductor");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Títulos
        lblTitulo = new JLabel("Traductor");
        lblTitulo.setBounds(300, 40, 260, 50);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setOpaque(true);
        add(lblTitulo);
        
        lblTitulo = new JLabel("Palabra en Español");
        lblTitulo.setBounds(75, 150, 110, 50);
        //lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setOpaque(true);
        add(lblTitulo);
        
        lblTitulo = new JLabel("Palabra en Ingles");
        lblTitulo.setBounds(250, 150, 100, 50);
        //lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setOpaque(true);
        add(lblTitulo);
        
        // Cajas de texto
        txtPE1 = new JTextField();
        txtPE1.setBounds(75, 200, 160, 30);
        add(txtPE1);

        txtPE2 = new JTextField();
        txtPE2.setBounds(75, 250, 160, 30);
        add(txtPE2);

        txtPE3 = new JTextField();
        txtPE3.setBounds(75, 300, 160, 30);
        add(txtPE3);

        txtPE4 = new JTextField();
        txtPE4.setBounds(75, 350, 160, 30);
        add(txtPE4);

        // Traduccion
        lblPI1 = new JLabel();
        lblPI1.setBounds(250, 200, 200, 30);
        add(lblPI1);

        lblPI2 = new JLabel();
        lblPI2.setBounds(250, 250, 200, 30);
        add(lblPI2);

        lblPI3 = new JLabel();
        lblPI3.setBounds(250, 300, 200, 30);
        add(lblPI3);

        lblPI4 = new JLabel();
        lblPI4.setBounds(250, 350, 200, 30);
        add(lblPI4);

        // Botones
        btnTraducir = new JButton("Traducir");
        btnTraducir.setBounds(500, 200, 130, 45);
        btnTraducir.addActionListener(this);
        add(btnTraducir);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(500, 260, 130, 45);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTraducir) {
            traducir();
        }

        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    private void traducir() {
        lblPI1.setText(traducirPalabra(txtPE1.getText()));
        lblPI2.setText(traducirPalabra(txtPE2.getText()));
        lblPI3.setText(traducirPalabra(txtPE3.getText()));
        lblPI4.setText(traducirPalabra(txtPE4.getText()));
    }

    private String traducirPalabra(String palabra) {
        palabra = palabra.toLowerCase();

        switch (palabra) {
            case "hola":
                return "Hello";
            case "adios":
                return "Bye";
            case "comida":
                return "Food";
            case "dormir":
                return "Sleep";
            default:
                return "No traducido";
        }
    }

    private void limpiar() {
        txtPE1.setText("");
        txtPE2.setText("");
        txtPE3.setText("");
        txtPE4.setText("");

        lblPI1.setText("");
        lblPI2.setText("");
        lblPI3.setText("");
        lblPI4.setText("");
    }

    public static void main(String[] args) {
        new Traductor().setVisible(true);
    }
}
