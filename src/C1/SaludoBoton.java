package C1;

import javax.swing.*;
import java.awt.event.*;

public class SaludoBoton{
    public static void main(String[] args) {
        //Crear ventana
        JFrame ventana = new JFrame("Nombre Programa");
        ventana.setSize(300,200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setVisible(true);
        
        //Crear boton
        JButton boton = new JButton("Presiona AQUI");
        boton.setBounds(80,60,140,30);
        
        //Accion del boton
        boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"!Hola¡ Bienvenido al programa.");
            }
        });
        //Agregar boton a la ventana
        ventana.add(boton);
        
        //Mostrar ventana
        
    }
    
}
