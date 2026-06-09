package C2;

import javax.swing.*;
import java.awt.event.*;

public class TablaMultiplicarGUI extends JFrame{
    
    private JTextField campoNumero;
    private JTextArea areaResultado;
    private JButton botonCalcular;
    
    public TablaMultiplicarGUI(){
        setTitle("Tabla de Multiplicar");
        setSize(400,400);
        setLayout(null);
        
        JLabel etiqueta = new JLabel("Ingrese un numero:");
        etiqueta.setBounds(20,20,150,30);
        add(etiqueta);
        
        campoNumero = new JTextField();
        campoNumero.setBounds(160,20,100,30);
        add(campoNumero);
        
        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(150,70,100,30);
        add(botonCalcular);
        
        areaResultado = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBounds(50,120,280,200);
        add(scroll);
        
        //Evento boton
        botonCalcular.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
                    int numero = Integer.parseInt(campoNumero.getText());
                    
                    areaResultado.setText("");
                    
                    for(int i = 1; i <= 20; i++){
                        areaResultado.append(numero + "*" + i + "=" + (numero * i) + "\n");
                    }
                }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
}

class Principal_Tabla{
    public static void main(String[]args){
        TablaMultiplicarGUI ventana = new TablaMultiplicarGUI();
    }
}