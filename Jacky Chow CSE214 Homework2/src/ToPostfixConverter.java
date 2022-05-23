import java.util.*;

public class ToPostfixConverter implements Converter {
    static int counter = 0;

    @Override
    public String convert(ArithmeticExpression expression) {
        // appendix A
        StringBuilder sb = new StringBuilder();
        Stack<Operator> stack = new Stack<>();

        String token;
        int index = 0;

        while (true) {
            token = nextToken(expression.getExpression(), index);

            if (token.equals(""))
                break;

            if (!(Operator.isOperator(token)) && !(token.charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol()) && !(token.charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol())) {
                sb.append(token).append(" ");

            } else if (token.charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol()) {
                stack.push(Operator.of(token));

            } else if (token.charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                Operator temp = stack.pop();
                while (temp != Operator.LEFT_PARENTHESIS) {
                    sb.append(temp.getSymbol()).append(" ");
                    temp = stack.pop();
                }
            }
            //When an operand is read
            else if (Operator.isOperator(token)) {
                Operator op = Operator.of(token);
                //If the stack is empty, or its top element is a left parenthesis, push the input operator onto the stack.
                if (stack.empty() || stack.peek() == Operator.LEFT_PARENTHESIS) {
                    stack.push(op);
                }
                //If the input operator has higher precedence than the top of the stack, push the input operator onto the stack.
                else if (op.getRank() < stack.peek().getRank()) {
                    stack.push(op);
                }
                //If the input operator has the same precedence as the top of the stack, use the associative rule from algebra:
                // pop and add the top of the stack to the output, and then push the input onto the stack.
                else if (op.getRank() == stack.peek().getRank()) {
                    sb.append(stack.pop());
                    stack.push(op);
                }
                //If the input operator has lower precedence than the top of the stack, pop the stack and add the popped element to the output. Then, test the input operator against the new top element.
                else if (op.getRank() > stack.peek().getRank()) {
                    sb.append(stack.pop());
                    if (stack.empty()) stack.push(op);
                    if (op.getRank() < stack.peek().getRank()) stack.push(op);
                    if (op.getRank() == stack.peek().getRank()) {
                        sb.append(stack.pop());
                        stack.push(op);
                    }
                }
            }
            index += token.length() + counter;
            counter = 0;
        }
        while (!stack.empty()) {
                sb.append(stack.pop().getSymbol());
        }
        return sb.toString();
    }

    @Override
    public String nextToken(String s, int start) {

        if (start < 0 || start >= s.length()) {
            return "";
        }

        TokenBuilder tb = new TokenBuilder();
        while (s.charAt(start) == ' ') {
            counter++;
            if (++start >= s.length())
                return "";
        }

        if (Operator.isOperator(s.charAt(start)))
            return "" + s.charAt(start);

        else if (s.charAt(start) == Operator.LEFT_PARENTHESIS.getSymbol() || s.charAt(start) == Operator.RIGHT_PARENTHESIS.getSymbol())
            return "" + s.charAt(start);

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                counter++;
                continue;
            }
            if (Operator.isOperator(c)) {
                break;
            } else if (c == Operator.LEFT_PARENTHESIS.getSymbol() || c == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                break;
            } else {
                tb.append(c);
            }
        }
        return tb.build();
    }

    @Override
    public boolean isOperand(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public static void main(String[] args) {
        String a = "(7.25 + 5/4)";
        ToPostfixConverter tc = new ToPostfixConverter();
        ArithmeticExpression ae = new ArithmeticExpression(a);
        System.out.println(tc.isOperand(a));
        System.out.println(tc.convert(ae));
    }
}
