package C1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazOpciones{
    public static void main(String[]args){
        //Crear ventana
        JFrame ventana = new JFrame("Ejemplo de interfaz");
        ventana.setSize(300,250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        
        //Etiqueta nombre
        JLabel etiqueta1 = new JLabel("Nombre:");
        etiqueta1.setBounds(20,20,80,20);
        ventana.add(etiqueta1);
        //Campo de texto nombre
        JTextField texto1 = new JTextField();
        texto1.setBounds(100,20,150,10);
        ventana.add(texto1);
        
        //Etiqueta edad
        JLabel etiqueta2 = new JLabel("Edad:");
        etiqueta2.setBounds(20,40,80,20);
        ventana.add(etiqueta2);
        //
        JTextField texto2 = new JTextField();
        texto2.setBounds(100,40,150,10);
        ventana.add(texto2);
        
        //Etiqueta ciudad
        JLabel etiqueta3 = new JLabel("Ciudad:");
        etiqueta3.setBounds(20,60,80,20);
        ventana.add(etiqueta3);
        //
        JTextField texto3 = new JTextField();
        texto3.setBounds(100,60,150,10);
        ventana.add(texto3);
        
        //Boton guardar
        JButton boton = new JButton("GUARDAR");
        boton.setBounds(100,100,100,20);
        ventana.add(boton);
        JButton boton2 = new JButton("LIMPIAR");
        boton2.setBounds(100,130,100,20);
        ventana.add(boton2);
        
        //Evento del boton
        boton.addActionListener(new ActionListener(){
        @Override 
        public void actionPerformed(ActionEvent e){
            String nombre = texto1.getText();
            String edad = texto2.getText();
            String ciudad = texto3.getText();
            
            JOptionPane.showMessageDialog(null, "Datos guardados." + "\nNombre: " + nombre + "\nEdad: " + edad + "\nCiudad: " + ciudad);
            
            boton2.addActionListener(new ActionListener(){
                @Override 
                public void actionPerformed(ActionEvent e){
                    texto1.setText("");
                    texto2.setText("");
                    texto3.setText("");
                    }
                });
                }
               });
             //Mostrar ventana
             ventana.setVisible(true);                        
    }
}