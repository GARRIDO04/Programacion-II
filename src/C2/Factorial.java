package C2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Factorial extends JFrame{
    
    private JLabel lblTitulo, lblNum, lblCociente, lblResiduo, lblFactorial;
    private JTextField txtNum, txtCociente, txtResiduo, txtFactorial;
    private JButton btnCalcular, btnLimpiar;
    
    public Factorial(){
        setTitle("FACTORIAL");
        setSize(300,300);
        setLayout(null);
        
        lblTitulo =  new JLabel("==FACTORIAL==");
        lblTitulo.setBounds(100,10,120,30);
        add(lblTitulo);        
        
        lblNum =  new JLabel("Numero:");
        lblNum.setBounds(10,70,80,30);
        add(lblNum);
        txtNum = new JTextField();
        txtNum.setBounds(60,70,80,30);
        add(txtNum);
       
        btnCalcular = new JButton("-Calcular-");
        btnCalcular.setBounds(30,135,100,20);
        add(btnCalcular);
        
        btnLimpiar = new JButton("-Limpiar-");
        btnLimpiar.setBounds(160,135,100,20);
        add(btnLimpiar);
       
        lblCociente = new JLabel("Cociente: ");
        lblCociente.setBounds(10,170,100,20);
        add(lblCociente);
        txtCociente = new JTextField();
        txtCociente.setBounds(80,170,80,20);
        txtCociente.setEditable(false);
        add(txtCociente);
        
        lblResiduo = new JLabel("Residuo: ");
        lblResiduo.setBounds(10,200,80,20);
        add(lblResiduo);
        txtResiduo = new JTextField();
        txtResiduo.setBounds(80,200,80,20);
        txtResiduo.setEditable(false);
        add(txtResiduo);
        
        lblFactorial = new JLabel("Resultado Factorial: ");
        lblFactorial.setBounds(10,230,130,20);
        add(lblFactorial);
        txtFactorial =  new JTextField();
        txtFactorial.setBounds(150,230,80,20);
        txtFactorial.setEditable(false);
        add(txtFactorial);
        
        btnCalcular.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int numero = Integer.parseInt(txtNum.getText());
                    int factorial = 1; 
                    
                    for(int i = 1; i <= numero; i++){
                        factorial = factorial * i;
                    }
                    
                    int cociente = 0;
                    int residuo = 0;
                    cociente = factorial / 4;
                    residuo = factorial % 4;
                    /*if(factorial >= 200){
                        cociente = factorial / 4;
                        residuo = factorial % 4;
                    }*/
                    txtFactorial.setText(String.valueOf(factorial));
                    txtCociente.setText(String.valueOf(cociente));
                    txtResiduo.setText(String.valueOf(residuo));    
                }
        });
        
        btnLimpiar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    txtNum.setText("");
                    txtCociente.setText("");
                    txtResiduo.setText("");
                    txtFactorial.setText("");
                }
        });
        
        setVisible(true);
        
    }
}

class VentanaFactorial{
    public static void main(String[]args){
        Factorial ventana = new Factorial();
    }
}