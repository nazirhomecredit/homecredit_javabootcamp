package com.assignments.java.core;

public class OddEven {
    public static void main(String[] args) {
        oddEven(5);
        oddEven(4);

    }
    public static void oddEven(int n){
        if(n%2==0){
            System.out.println(n + " is even number");
        }
        else {
            System.out.println(n + " is odd number");
        }
    }
}
