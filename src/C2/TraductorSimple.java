package C2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TraductorSimple {
    public static void main(String[] args) {

        // Ventana
        JFrame ventana = new JFrame("Traductor");
        ventana.setSize(500,400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        // Título
        JLabel titulo = new JLabel("TRADUCTOR");
        titulo.setBounds(150,20,200,40);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        ventana.add(titulo);
        
        JLabel español = new JLabel("Palabras Español");
        español.setBounds(50,70,150,30);
        ventana.add(español);

        JLabel ingles = new JLabel("Palabras Ingles");
        ingles.setBounds(250,70,150,30);
        ventana.add(ingles);

        // Cajas de texto
        JTextField txt1 = new JTextField();
        txt1.setBounds(50,100,150,30);
        ventana.add(txt1);

        JTextField txt2 = new JTextField();
        txt2.setBounds(50,140,150,30);
        ventana.add(txt2);

        JTextField txt3 = new JTextField();
        txt3.setBounds(50,180,150,30);
        ventana.add(txt3);

        JTextField txt4 = new JTextField();
        txt4.setBounds(50,220,150,30);
        ventana.add(txt4);

        // Labels traducciones
        JLabel PI1 = new JLabel();
        PI1.setBounds(250,100,150,30);
        ventana.add(PI1);

        JLabel PI2 = new JLabel();
        PI2.setBounds(250,140,150,30);
        ventana.add(PI2);

        JLabel PI3 = new JLabel();
        PI3.setBounds(250,180,150,30);
        ventana.add(PI3);

        JLabel PI4 = new JLabel();
        PI4.setBounds(250,220,150,30);
        ventana.add(PI4);

        // Botón traducir
        JButton boton = new JButton("Traducir");
        boton.setBounds(180,270,120,30);

        boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                PI1.setText(traducir(txt1.getText()));
                PI2.setText(traducir(txt2.getText()));
                PI3.setText(traducir(txt3.getText()));
                PI4.setText(traducir(txt4.getText()));
            }

            private String traducir(String palabra){
                palabra = palabra.toLowerCase();

                switch (palabra){
                    case "hola": return "Hello";
                    case "adios": return "Bye";
                    case "comida": return "Food";
                    case "dormir": return "Sleep";
                    default: return "No traducido";
                }
            }
        });

        ventana.add(boton);

        // Botón limpiar
        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(180,310,120,30);

        limpiar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");

                PI1.setText("");
                PI2.setText("");
                PI3.setText("");
                PI4.setText("");
                
                txt1.requestFocus();
            }
        });

        ventana.add(limpiar);

        // Mostrar ventana
        ventana.setVisible(true);
    }
}