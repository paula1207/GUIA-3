/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ean.expressioneval;

/**
 *
 * @author danie
 */
import java.util.EmptyStackException;
    public class Pila<T> {
    private Nodo<T> cima;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        
        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public void apilar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
    }

    public T desapilar() {
        if (cima == null) throw new EmptyStackException();
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public T revisarSiguiente() {
        if (cima == null) throw new EmptyStackException();
        return cima.dato;
    }
}
