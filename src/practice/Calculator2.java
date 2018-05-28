package practice;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/description/
 */
public class Calculator2 {

    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        s = s.trim();
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        char sign = '+';
        int num = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (!Character.isDigit(c) || i == (len - 1)) {
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
        while (!stack.empty()) {
            ret += stack.pop();
        }

        return ret;
    }
}
