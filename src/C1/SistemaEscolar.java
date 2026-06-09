package C1;

import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
 
public class SistemaEscolar extends JFrame {
    
    //Componentes
    private JTextField txtNombre, txtApellido, txtEdad, txtGrado;
    private JComboBox<String> comboGrupo;
    private JRadioButton rbMasculino, rbFemenino;
    private JButton btnGuardar, btnLimpiar, btnSalir;
    private JTable tabla;
    private DefaultTableModel modelo;
 
    public SistemaEscolar() {
        setTitle("Sistema Escolar");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
 
        // Titulo
        JLabel lblTitulo = new JLabel("Registro de Alumnos");
        lblTitulo.setBounds(180, 10, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);
 
        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 50, 100, 25);
        add(lblNombre);
 
        txtNombre = new JTextField();
        txtNombre.setBounds(150, 50, 150, 25);
        add(txtNombre);
 
        // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 90, 100, 25);
        add(lblApellido);
 
        txtApellido = new JTextField();
        txtApellido.setBounds(150, 90, 150, 25);
        add(txtApellido);
 
        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(50, 130, 100, 25);
        add(lblEdad);
 
        txtEdad = new JTextField();
        txtEdad.setBounds(150, 130, 150, 25);
        add(txtEdad);
 
        // Grado
        JLabel lblGrado = new JLabel("Grado:");
        lblGrado.setBounds(50, 170, 100, 25);
        add(lblGrado);
 
        txtGrado = new JTextField();
        txtGrado.setBounds(150, 170, 150, 25);
        add(txtGrado);
 
        // Grupo
        JLabel lblGrupo = new JLabel("Grupo:");
        lblGrupo.setBounds(50, 210, 100, 25);
        add(lblGrupo);
 
        comboGrupo = new JComboBox<>(new String[]{"A", "B", "C"});
        comboGrupo.setBounds(150, 210, 150, 25);
        add(comboGrupo);
 
        // Sexo
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(50, 250, 100, 25);
        add(lblSexo);
 
        rbMasculino = new JRadioButton("Masculino");
        rbMasculino.setBounds(150, 250, 100, 25);
        add(rbMasculino);
 
        rbFemenino = new JRadioButton("Femenino");
        rbFemenino.setBounds(260, 250, 100, 25);
        add(rbFemenino);
 
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFemenino);
 
        // Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 300, 100, 30);
        add(btnGuardar);
 
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(170, 300, 100, 30);
        add(btnLimpiar);
 
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(290, 300, 100, 30);
        add(btnSalir);
 
        // Tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Grado");
        modelo.addColumn("Grupo");
        modelo.addColumn("Sexo");
 
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 350, 500, 100);
        add(scroll);
 
        // Eventos
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarAlumno();
            }
        });
 
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
 
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
 
    // Metodo guardar
    private void guardarAlumno() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edad = txtEdad.getText();
        String grado = txtGrado.getText();
        String grupo = comboGrupo.getSelectedItem().toString();
        String sexo = "";
 
        if (rbMasculino.isSelected()) {
            sexo = "Masculino";
        } else if (rbFemenino.isSelected()) {
            sexo = "Femenino";
        }
 
        if (nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || grado.isEmpty() || sexo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
        } else {
            modelo.addRow(new Object[]{nombre, apellido, edad, grado, grupo, sexo});
            JOptionPane.showMessageDialog(this, "Alumno guardado");
            limpiarCampos();
        }
    }
 
    // Metodo limpiar
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtGrado.setText("");
        comboGrupo.setSelectedIndex(0);
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
    }
 
    // Metodo principal
    public static void main(String[] args) {
        SistemaEscolar ventana = new SistemaEscolar();
        ventana.setVisible(true);
    }
}