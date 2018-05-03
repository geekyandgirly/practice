package practice;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TwoStacksQueue {
    private static class MyQueue<T> {
        private final Stack<T> t1;
        private final Stack<T> t2;
        
        public MyQueue() {
            t1 = new Stack<T>();
            t2 = new Stack<T>();
        }
        
        public void enqueue(T t) {
            while (!t2.isEmpty()) {
                t1.push(t2.pop());
            }

            t1.push(t);
        }
        
        public T dequeue() {
            if (!t2.isEmpty()) {
                return t2.pop();
            }
            
            while (!t1.isEmpty()) {
                t2.push(t1.pop());
            }
            return t2.pop();
        } 
        
        public T peek() {
            if (!t2.isEmpty()) {
                return t2.peek();
            }
            
            while (!t1.isEmpty()) {
                t2.push(t1.pop());
            }
            return t2.peek();            
        }
    }
    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}

