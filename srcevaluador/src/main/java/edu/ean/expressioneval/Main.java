package edu.ean.expressioneval;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        String expression;
        boolean condition = true;
        // loop to read the expression from the command line
        Scanner scanner = new Scanner(System.in);
        while (condition) {
            System.out.print("Ingresa una expresión aritmética: ");
            expression = scanner.nextLine();
            if (expression.isEmpty()) {
                condition = false;
            } else {
                System.out.println("Expresión ingresada: " + expression);
                System.out.println("Parentesis balanceados: " + ShuntingYard.areParenthesesBalanced(expression));
                
                Cola<String> postfix = ShuntingYard.convertToPostfix(expression);
                System.out.println("Expresion en notación postfija: " + String.join(" ", postfix.colaComoArreglo()));

                System.out.println("Resultado de la expresión aritmética: " + ShuntingYard.evaluatePostfix(postfix));
            }
        }
        scanner.close();
    }
}