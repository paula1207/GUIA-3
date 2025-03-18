package edu.ean.expressioneval;

/*
 * Shunting Yard algorithm implementation.
 * based on https://en.wikipedia.org/wiki/Shunting_yard_algorithm
 */
public class ShuntingYard {

    public static int evaluatePostfix(Cola<String> postfix) {
        Pila<Integer> stack = new Pila<>();

        while (!postfix.estaVacia()) {
            String token = postfix.desencolar();

            if (token.matches("\\d+")) {
                stack.apilar(Integer.parseInt(token));
            } else {
                int b = stack.desapilar();
                int a = stack.desapilar();
                int result = applyOperator(a, b, token);
                stack.apilar(result);
            }
        }
        return stack.desapilar();
    }

    private static int applyOperator(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    private static int getPrecedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    public static boolean areParenthesesBalanced(String infix) {
        Pila<Character> stack = new Pila<>();
        for (char ch : infix.toCharArray()) {
            if (ch == '(') {
                stack.apilar(ch);
            } else if (ch == ')') {
                if (stack.estaVacia()) {
                    return false;
                }
                stack.desapilar();
            }
        }
        return stack.estaVacia();
    }

    public static Cola<String> convertToPostfix(String infix) {
        Cola<String> outputQueue = new Cola<>();
        Pila<Character> operatorStack = new Pila<>();
        StringBuilder numberBuffer = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isDigit(ch)) { 
                numberBuffer.append(ch);
            } else {
                if (numberBuffer.length() > 0) { 
                    outputQueue.encolar(numberBuffer.toString());
                    numberBuffer.setLength(0); 
                }

                if (ch == '(') {
                    operatorStack.apilar(ch);
                } else if (ch == ')') {
                    while (!operatorStack.estaVacia() && operatorStack.revisarSiguiente() != '(') {
                        outputQueue.encolar(Character.toString(operatorStack.desapilar()));
                    }
                    operatorStack.desapilar(); 
                } else if ("+-*/^".indexOf(ch) != -1) { 
                    while (!operatorStack.estaVacia() && getPrecedence(operatorStack.revisarSiguiente()) >= getPrecedence(ch)) {
                        outputQueue.encolar(Character.toString(operatorStack.desapilar()));
                    }
                    operatorStack.apilar(ch);
                }
            }
        }

        if (numberBuffer.length() > 0) {
            outputQueue.encolar(numberBuffer.toString());
        }

        while (!operatorStack.estaVacia()) {
            outputQueue.encolar(Character.toString(operatorStack.desapilar()));
        }

        return outputQueue;
    }
}
