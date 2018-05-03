package practice;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class StringRotateLeft {
    
    public static void rotateLeft(int[] a, int n, int k) {
        if (n != k) {
            int count = 0;
            int i = 0;
            int ai = a[i];
            while (count < n) {
                int ii = (i < k) ? (n + (i - k)) : (i - k);
                int aii = a[ii];
                a[ii] = ai;
                i = ii;
                ai = aii;
                count ++;
            }            
        }
        
        for(int j = 0; j < n; j++) {
            System.out.print(a[j] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        StringRotateLeft.rotateLeft(a, n, k);
    }
}
