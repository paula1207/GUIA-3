/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ean.expressioneval;

import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class Cola<T> { 
    private Nodo<T> frente, fin;

   
    private static class Nodo<E> { 
        E dato;
        Nodo<E> siguiente;

        
        Nodo(E dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

   
    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento); 
        if (fin != null) {
            fin.siguiente = nuevoNodo;
        }
        fin = nuevoNodo;
        if (frente == null) {
            frente = nuevoNodo;
        }
    }

   
    public T desencolar() {
        if (frente == null) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }

    public ArrayList<T> colaComoArreglo() {
        ArrayList<T> lista = new ArrayList<>();
        Nodo<T> actual = frente;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}

