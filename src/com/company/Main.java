package com.company;

public class Main {
    public static void main(String[] args) {
//        // Converts infix to postfix
//        String postfix = eqc.compile("2 + 3 = ( 5 + 0 )");
//        System.out.println(postfix);
//
//        // Converts postfix to infix
//        String infix = eqc.decompile("x y ^ 5 z * / 10 + 22 + 5 *");
//        System.out.println(infix);
//
//        // Evaluates the equation
//        String result = eqc.evaluate("2 + 3 - ( 5 + 0 )");
//        System.out.println(result);

        new MainFrame();
    }

    public static void process(String operation, String input) {
        if (operation != null && !input.equals("")) {
            EquationCompiler eqc = new EquationCompiler();

            switch (operation) {
                case "infToPos":
                    InteractionPanel.showOutput(eqc.compile(input));
                    break;
                case "posToInf":
                    InteractionPanel.showOutput(eqc.decompile(input));
                    break;
                case "eval":
                    InteractionPanel.showOutput(eqc.evaluate(input));
                    break;
                default:
                    InteractionPanel.showOutput("ERROR");
                    break;
            }
        }
    }

}
