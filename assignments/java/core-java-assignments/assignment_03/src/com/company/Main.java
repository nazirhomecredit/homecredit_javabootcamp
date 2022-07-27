package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a number a: ");
        int a= sc.nextInt();
        System.out.println("Enter a number b : ");
        int b=sc.nextInt();

        int t=a;
        a=b;
        b=t;
        System.out.println("Swapped values of a : " + a);
        System.out.println("Swapped values of b : "+ b);

    }
}
