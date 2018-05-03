package practice;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BalancedBrackets {
    
	   public static boolean isBalanced(String expression) {
	        if (expression == null || expression.length() == 0 || expression.length() % 2 == 1) {
	            return false;
	        }
	        int i = 0;
	        Stack<Character> stack = new Stack<>();
	        while (i < expression.length()) {
	            Character c;
	            if (stack.isEmpty()) {
	                c = expression.charAt(i);
	                stack.push(c);
	            } else {
	                c = stack.peek();
	                Character c1 = expression.charAt(i);
	                if (isMatch(c, c1)) {
	                    stack.pop();
	                } else {
	                    stack.push(c1);
	                }                
	            }
	            i++;
	        }
	        
	        return stack.isEmpty();
	    }
	    
	    private static boolean isMatch(Character c, Character c1) {
	        if (c.equals(c1)) {
	            return false;
	        }
	        
	        if (c.equals(Character.valueOf('['))) {
	            return c1.equals(Character.valueOf(']'));
	        }
	        if (c.equals(Character.valueOf('{'))) {
	            return c1.equals(Character.valueOf('}'));
	        }
	        if (c.equals(Character.valueOf('('))) {
	            return c1.equals(Character.valueOf(')'));
	        }
	        return false;
	    }
	  
	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int t = in.nextInt();
	        for (int a0 = 0; a0 < t; a0++) {
	            String expression = in.next();
	            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
	        }
	    }
}
