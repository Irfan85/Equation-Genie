package com.company;

import java.util.Stack;
import java.util.StringTokenizer;

public class EquationCompiler {
    private Stack<Character> operatorStack;
    private String output;

    public EquationCompiler() {
        operatorStack = new Stack<>();
        output = "";
    }

    // Converts infix to postfix
    public String compile(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input);

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            //System.out.println(operatorStack);
            //System.out.println(output);

            if (token.length() == 1 && isOperator(token.charAt(0))) {
                if (operatorStack.size() > 0 && token.charAt(0) == ')') {
                    while (operatorStack.peek() != '(')
                        output += " " + operatorStack.pop();
                    operatorStack.pop();
                } else if (token.charAt(0) == '(')
                    operatorStack.push('(');
                else if (operatorStack.size() == 0 || hasHigherPrecedence(token.charAt(0), operatorStack.peek())) {
                    operatorStack.push(token.charAt(0));
                } else {
                    while (operatorStack.size() > 0 && !hasHigherPrecedence(token.charAt(0), operatorStack.peek())) {
                        output += " " + operatorStack.pop();
                    }
                    operatorStack.push(token.charAt(0));
                }
            } else {
                if (output.equals(""))
                    output += token;
                else
                    output += " " + token;
            }
        }

        while (operatorStack.size() > 0) {
            output += " " + operatorStack.pop();
        }

        return output;
    }

    public String decompile(String postfix) {
        Stack<String> operandStack = new Stack<>();

        StringTokenizer stringTokenizer = new StringTokenizer(postfix);

        String token;
        while (stringTokenizer.hasMoreTokens()) {
            //System.out.println(operandStack);
            token = stringTokenizer.nextToken();


            if (token.length() == 1 && isOperator(token.charAt(0))) {
                char operator = token.charAt(0);
                String operand02 = operandStack.pop();
                String operand01 = operandStack.pop();

                operandStack.push("( " + operand01 + " " + operator + " " + operand02 + " )");
            } else {
                operandStack.push(token);
            }
        }

        return operandStack.pop();
    }

    public String evaluate(String infixEquation) {
        Stack<Double> operandStack = new Stack<>();

        infixEquation = removeUnnecessaryParentheses(infixEquation);
        //System.out.println(">>" + infixEquation);

        if (infixEquation.contains("=") || infixEquation.contains("<") || infixEquation.contains(">")) {
            char verificationCharacter = '=';
            if (infixEquation.contains("<"))
                verificationCharacter = '<';
            else if (infixEquation.contains(">"))
                verificationCharacter = '>';

            return String.valueOf(verify(verificationCharacter, infixEquation));
        }

        String postfix = compile(infixEquation);
        StringTokenizer stringTokenizer = new StringTokenizer(postfix);

        String token;
        while (stringTokenizer.hasMoreTokens()) {
            //System.out.println(operandStack);
            token = stringTokenizer.nextToken();


            if (token.length() == 1 && isOperator(token.charAt(0))) {
                char operator = token.charAt(0);
                double operand02 = operandStack.pop();
                double operand01 = operandStack.pop();

                double result = calculate(operand01, operand02, operator);

                operandStack.push(result);
            } else {
                operandStack.push(Double.parseDouble(token));
            }
        }

        return String.valueOf(operandStack.pop());
    }

    public boolean verify(char verificationCharacter, String infixEquation) {
        String leftHandSide = infixEquation.substring(0, infixEquation.indexOf(verificationCharacter));
        String rightHandSide = infixEquation.substring(infixEquation.indexOf(verificationCharacter) + 1);

        //System.out.println(leftHandSide);
        //System.out.println(rightHandSide);

        String leftResult = evaluate(leftHandSide);
        String rightResult = evaluate(rightHandSide);

        boolean result = false;

        switch (verificationCharacter) {
            case '=':
                result = leftResult.equals(rightResult);
                break;
            case '>':
                result = leftResult.compareTo(rightResult) > 0;
                break;
            case '<':
                result = leftResult.compareTo(rightResult) < 0;
                break;
        }

        return result;
    }

    private double calculate(double operand01, double operand02, char operator) {
        double result = 0;

        switch (operator) {
            case '+':
                result = operand01 + operand02;
                break;
            case '-':
                result = operand01 - operand02;
                break;
            case '*':
                result = operand01 * operand02;
                break;
            case '/':
                result = operand01 / operand02;
                break;
            case '%':
                result = operand01 % operand02;
                break;
            case '^':
                result = Math.pow(operand01, operand02);
                break;
        }

        return result;
    }

    private boolean isOperator(Character c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '<' || c == '>' || c == '=' || c == '(' || c == ')';
    }

    // Checks whether operator01 has higher precedence than operator02
    private boolean hasHigherPrecedence(Character operator01, Character operator02) {
        if (operator02 == '(')
            return true;
        if ((operator01 == '+' || operator01 == '-') && (operator02 == '=' || operator02 == '>' || operator02 == '<'))
            return true;
        else if ((operator01 == '^') && (operator02 == '*' || operator02 == '/' || operator02 == '%' || operator02 == '+' || operator02 == '-' || operator02 == '=' || operator02 == '>' || operator02 == '<'))
            return true;
        else if ((operator01 == '*' || operator01 == '/' || operator01 == '%') && (operator02 == '+' || operator02 == '-' || operator02 == '=' || operator02 == '>' || operator02 == '<'))
            return true;

        return false;
    }

    private String removeUnnecessaryParentheses(String infixEquation) {
        if (infixEquation.charAt(0) == '(' && infixEquation.charAt(infixEquation.length() - 1) == ')') {
            int parenthesesCount = numberOfParentheses(infixEquation.substring(1, infixEquation.length() - 1));
            if (parenthesesCount == 0 || parenthesesCount % 2 != 0)
                return infixEquation;

            return removeUnnecessaryParentheses(infixEquation.substring(1, infixEquation.length() - 1));
        }

        return infixEquation;
    }

    private int numberOfParentheses(String infixEquation) {
        int count = 0;

        for (char c : infixEquation.toCharArray()) {
            if (c == '(' || c == ')')
                count++;
        }

        return count;
    }
}
