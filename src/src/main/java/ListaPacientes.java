/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centraldepacientes;

/**
 *
 * @author Italtel - Paula
 */
import java.io.*;
import java.util.*;

public class ListaPacientes {
    private Nodo cabeza;

    // Constructor
    public ListaPacientes() {
        this.cabeza = null;
    }

    // Método para agregar un paciente
    public void agregarPaciente(Paciente paciente) {
        Nodo nuevoNodo = new Nodo(paciente);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    // Método para buscar un paciente por ID
    public Paciente buscarPaciente(int id) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.paciente.getId() == id) {
                return temp.paciente;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    // Método para eliminar un paciente por ID
    public void eliminarPaciente(int id) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.paciente.getId() == id) {
            cabeza = cabeza.siguiente;
            return;
        }
        Nodo temp = cabeza;
        while (temp.siguiente != null && temp.siguiente.paciente.getId() != id) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
        }
    }

    // Método para mostrar todos los pacientes
    public String mostrarPacientes() {
        StringBuilder sb = new StringBuilder();
        Nodo temp = cabeza;
        while (temp != null) {
            sb.append(temp.paciente.toString()).append("\n");
            temp = temp.siguiente;
        }
        return sb.toString();
    }

    // Método para guardar pacientes en un archivo
    public void guardarPacientes(String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            Nodo temp = cabeza;
            while (temp != null) {
                Paciente paciente = temp.paciente;
                writer.write(paciente.getId() + "," + paciente.getNombre() + "," + paciente.getEdad() + "," + paciente.getClinica());
                writer.newLine();
                temp = temp.siguiente;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar pacientes desde un archivo
    public void cargarPacientes(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int edad = Integer.parseInt(datos[2]);
                String clinica = datos[3];
                Paciente paciente = new Paciente(id, nombre, edad, clinica);
                agregarPaciente(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}