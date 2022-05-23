import java.util.ArrayList;
import java.util.Stack;

public class PostFixEvaluator implements Evaluator {

    @Override
    public double evaluate(String expressionString) {
        Stack<Double> stack = new Stack<>();
        String[] token = expressionString.split(" ");
        double result = 0;

        for (int i = 0; i < token.length; i ++) {
            if (!Operator.isOperator(token[i]) && !(token[i].charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol()) && !(token[i].charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol())) {
                stack.push(Double.parseDouble(token[i]));
            }
            else if (Operator.isOperator(token[i])) {
                double val1 = stack.pop();
                double val2 = stack.pop();

                if (token[i].charAt(0) == Operator.ADDITION.getSymbol()) {
                    result = add(val1, val2);
                } else if (token[i].charAt(0) == Operator.SUBTRACTION.getSymbol()) {
                    result = subtract(val2, val1);
                } else if (token[i].charAt(0) == Operator.MULTIPLICATION.getSymbol()) {
                    result = multiply(val1, val2);
                } else if (token[i].charAt(0) == Operator.DIVISION.getSymbol()) {
                    result = divide(val2, val1);
                }
                stack.push(result);
            }

        }
        return result;
    }

    public double add(double num1, double num2) {
        return num1 + num2;
    }
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }
    public double divide(double num1, double num2) {
        return num1 / num2;
    }


    public static void main(String[] args) {
        PostFixEvaluator pf = new PostFixEvaluator();
        System.out.println(pf.evaluate("6 5 2 3 + 8.2 * + /"));

    }
}

