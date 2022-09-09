package com.assignments.java.core;

public class PrimeNumber {
    public static void main(String[] args) {
        isPrime(7);
        isPrime(4);
        isPrime(13);
        isPrime(2);
        isPrime(8);

    }
    public static void isPrime(int n) {
        int flag = 0;

        if( n == 0 || n==1) {
            System.out.println(n + " is not prime");
        }
        else{
            for(int i =2 ;i<=n/2 ;i++){
                if(n%i==0){
                    System.out.println( n + " is not prime");
                    flag=1;
                    break;
                }
            }
        }
        if(flag == 0){
            System.out.println(n + " is a prime");
        }
    }
}
