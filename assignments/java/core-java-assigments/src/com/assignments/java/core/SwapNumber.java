package com.assignments.java.core;

public class SwapNumber {
    public static void main(String[] args) {
        swapNumber(7 , 8);
    }
    public static void swapNumber(int n , int m){
        System.out.println("Before Swap n =" + n + " and m =" + m );
        int temp;
        temp=n;
        n=m;
        m=temp;

        System.out.println("After Swap n=" + n + " and m=" + m );

    }
}
