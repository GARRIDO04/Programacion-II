import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticaProducto{
    public static void main(String[]args){
        //Crear ventana
        JFrame ventana = new JFrame("Interfaz Tienda");
        ventana.setSize(300,300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        
        JLabel etiquetaT = new JLabel("\n Tienda la Favorita");
        etiquetaT.setBounds(90,0,200,30);
        ventana.add(etiquetaT);
        
        //Etiqueta nombre
        JLabel etiqueta1 = new JLabel("Producto:");
        etiqueta1.setBounds(20,35,80,20);
        ventana.add(etiqueta1);
        //Campo de texto nombre
        JTextField texto1 = new JTextField();
        texto1.setBounds(90,35,100,20);
        ventana.add(texto1);
        
        //Etiqueta edad
        JLabel etiqueta2 = new JLabel("Cantidad:");
        etiqueta2.setBounds(20,85,80,20);
        ventana.add(etiqueta2);
        //
        JTextField texto2 = new JTextField();
        texto2.setBounds(90,85,100,20);
        ventana.add(texto2);
        
        //Etiqueta ciudad
        JLabel etiqueta3 = new JLabel("Precio:");
        etiqueta3.setBounds(20,135,80,20);
        ventana.add(etiqueta3);
        //
        JTextField texto3 = new JTextField();
        texto3.setBounds(90,135,100,20);
        ventana.add(texto3);
        
        //Boton guardar
        JButton boton = new JButton("CALCULAR");
        boton.setBounds(30,200,100,15);
        ventana.add(boton);
        //Boton limpiar
        JButton boton2 = new JButton("LIMPIAR");
        boton2.setBounds(150,200,100,15);
        ventana.add(boton2);
        
        //Evento del boton
        boton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            String producto = texto1.getText();
            int cantidad = Integer.parseInt(texto2.getText());
            int precio = Integer.parseInt(texto3.getText());
            int suma = cantidad * precio;
            
            
            //JOptionPane.showMessageDialog(null, "El total por tu(s) " + cantidad + " productos es de $" + suma );
              JOptionPane.showMessageDialog(null, "El total por tu(s) " + cantidad + " " + producto + " es de $" + suma);
            
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