
package C2;

import javax.swing.*;
import java.awt.event.*;

public class MayorMenorExamen {
    public static void main(String[]args){
    JFrame ventana = new JFrame("Verificador Mayoria de Edad");
    ventana.setSize(400,300);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLayout(null);
    ventana.setLocationRelativeTo(null);
    
    JLabel titulo = new JLabel("\n=== Verificador de Edad ===");
    titulo.setBounds(120, 20, 250, 20);
    ventana.add(titulo);
    
    JLabel nombre = new JLabel("Nombre:");
    nombre.setBounds(20, 50, 80, 30);
    ventana.add(nombre);
    JTextField txtNombre = new JTextField();
    txtNombre.setBounds(105, 50, 90, 30);
    ventana.add(txtNombre);
    
    JLabel edad = new JLabel("Edad:");
    edad.setBounds(20, 100, 80, 30);
    ventana.add(edad);
    JTextField txtEdad = new JTextField();
    txtEdad.setBounds(105, 100, 90, 30);
    ventana.add(txtEdad);
    
    JRadioButton rbH = new JRadioButton("Hombre");
    rbH.setBounds(220, 50, 80, 30);
    ventana.add(rbH);
    JRadioButton rbM = new JRadioButton("Mujer");
    rbM.setBounds(220, 100, 80, 30);
    ventana.add(rbM);
    ButtonGroup grupoSexo = new ButtonGroup();
    grupoSexo.add(rbH);
    grupoSexo.add(rbM);
    
    JButton boton = new JButton(" * Validar * ");
    boton.setBounds(150, 180, 100, 30);
    ventana.add(boton);
    
    boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int edad = Integer.parseInt(txtEdad.getText());
                
                if(edad <= 17){
                JOptionPane.showMessageDialog(null,"!Hola¡ " + txtNombre.getText() +". NO eres mayor de edad");
                }else{JOptionPane.showMessageDialog(null,"¡Hola " + txtNombre.getText() +"!. Eres mayor de edad, bienvenido al programa");}               
            }
        });
    
    ventana.setVisible(true);
    }
}
