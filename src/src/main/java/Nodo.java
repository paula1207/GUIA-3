/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centraldepacientes;

/**
 *
 * @author Italtel - Paula
 */

public class Nodo {
    Paciente paciente;
    Nodo siguiente;

    // Constructor
    public Nodo(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }
}


