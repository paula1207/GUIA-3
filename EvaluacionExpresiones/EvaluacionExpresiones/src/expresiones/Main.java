/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

/**
 *
 * @author danie
 */
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
      public static void main(String[] args) {
        System.out.println("Iniciando programa...");

        
        Stack<Integer> pila = new Stack<>();
        pila.push(10);
        pila.push(20);
        pila.push(30);
        System.out.println("Elemento desapilado: " + pila.pop()); 

        
        Queue<String> cola = new LinkedList<>();
        cola.add("Hola");
        cola.add("Mundo");
        cola.add("Java");
        System.out.println("Elemento desencolado: " + cola.poll()); 
    }
}
