/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.centraldepacientes;

/**
 *
 * @author Italtel - Paula
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CentraldePacientes extends JFrame {
    private ListaPacientes listaPacientes;
    private JTextField txtId, txtNombre, txtEdad, txtClinica;
    private JTextArea txtAreaResultados;
    private static final String ARCHIVO_PACIENTES = "pacientes.txt";

    public CentraldePacientes() {
        listaPacientes = new ListaPacientes();

        // Cargar pacientes al iniciar
        listaPacientes.cargarPacientes(ARCHIVO_PACIENTES);

        // Configuración de la ventana
        setTitle("Central de Pacientes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panel.add(txtEdad);

        panel.add(new JLabel("Clínica:"));
        txtClinica = new JTextField();
        panel.add(txtClinica);

        JButton btnAgregar = new JButton("Agregar Paciente");
        JButton btnBuscar = new JButton("Buscar Paciente");
        JButton btnEliminar = new JButton("Eliminar Paciente");
        JButton btnMostrar = new JButton("Mostrar Pacientes");

        panel.add(btnAgregar);
        panel.add(btnBuscar);
        panel.add(btnEliminar);
        panel.add(btnMostrar);

        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);

        // Agregar componentes a la ventana
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Acciones de los botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPaciente();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPaciente();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPacientes();
            }
        });

        // Guardar pacientes al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listaPacientes.guardarPacientes(ARCHIVO_PACIENTES);
            }
        });
    }

    private void agregarPaciente() {
        try {
            // Validar campos vacíos
            if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtEdad.getText().isEmpty() || txtClinica.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String clinica = txtClinica.getText();

            // Verificar si el ID ya existe
            if (listaPacientes.buscarPaciente(id) != null) {
                JOptionPane.showMessageDialog(this, "Error: Ya existe un paciente con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear y agregar el paciente
            Paciente paciente = new Paciente(id, nombre, edad, clinica);
            listaPacientes.agregarPaciente(paciente);
            JOptionPane.showMessageDialog(this, "Paciente agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: ID y Edad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPaciente() {
        try {
            // Validar campo vacío
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtId.getText());
            Paciente paciente = listaPacientes.buscarPaciente(id);
            if (paciente != null) {
                txtAreaResultados.setText("Paciente encontrado:\n" + paciente.toString());
            } else {
                txtAreaResultados.setText("Paciente no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPaciente() {
        try {
            // Validar campo vacío
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtId.getText());

            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar al paciente con ID " + id + "?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                listaPacientes.eliminarPaciente(id);
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar campos
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPacientes() {
        String pacientes = listaPacientes.mostrarPacientes();
        if (pacientes.isEmpty()) {
            txtAreaResultados.setText("No hay pacientes registrados.");
        } else {
            txtAreaResultados.setText("Lista de Pacientes:\n" + pacientes);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtClinica.setText("");
        txtAreaResultados.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CentraldePacientes().setVisible(true);
            }
        });
    }
}