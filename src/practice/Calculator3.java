package practice;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-iii/description/
 */
public class Calculator3 {
    public int calculate(String s) {
        if (s == null) return 0;
        s = s.trim();
        int len = s.length();
        if (len == 0) return 0;

        Stack<String> stack = new Stack<>();
        int num = 0;
        boolean numPushed = true;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                numPushed = false;
            }

            if (c == ')' || i == len - 1) {
                Stack<String> stack1 = new Stack<>();
                if(!numPushed) {
                    stack.push(String.valueOf(num));
                    numPushed = true;
                    // System.out.println("pushing to stack a: " + num);
                    num = 0;
                }

                String s1 = stack.pop();
                while (!s1.equals("(")) {
                    if (s1.charAt(0) == 'n') {
                        s1 = s1.replace('n', '-');
                    }
                    stack1.push(s1);
                    // System.out.println("pushing to stack1: " + s1);
                    if (stack.isEmpty()) {
                        break;
                    }
                    s1 = stack.pop();
                }
                int eval = calculateBasic(stack1);
                // System.out.println("calculateBasic result: " + eval);
                if (eval < 0) {
                    String n = String.valueOf(eval).replace('-', 'n');
                    // System.out.println("pushing result back to stack: " + n);
                    stack.push(n);
                } else {
                    stack.push(String.valueOf(eval));
                }
                numPushed = true;
                num = 0;
                // System.out.println("pushing to stack b: " + eval);

            } else {
                if (!Character.isDigit(c)) {
                    if (!numPushed) {
                        stack.push(String.valueOf(num));
                        // System.out.println("pushing to stack c: " + num);
                        numPushed = true;
                        num = 0;
                    }
                    stack.push(String.valueOf(c));
                    // System.out.println("pushing to stack d: " + String.valueOf(c));
                }
            }
        }

        Stack<String> finalStack = new Stack<>();
        while(!stack.isEmpty()) {
            String s1 = stack.pop();
            if (s1.charAt(0) == 'n') {
                s1 = s1.replace('n', '-');
            }
            finalStack.push(s1);
        }
        return calculateBasic(finalStack);
    }

    // calcualting basic expression without ( or )
    public int calculateBasic(Stack<String> strStack) {
        Stack<Integer> stack = new Stack<>();

        char sign = '+';
        int num = 0;

        while(!strStack.empty()) {
            String s = strStack.pop();
            // System.out.println("pop " + s);

            try {
                num = Integer.parseInt(s);
            } catch (NumberFormatException e) {
            }

            char c = s.charAt(0);
            if ((!Character.isDigit(c) && s.length() == 1) || strStack.isEmpty()) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                sign = c;
                num = 0;
            }
        }

        int ret = 0;
        while (!stack.isEmpty()) {
            ret += stack.pop();
        }
        return ret;
    }
}
