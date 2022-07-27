package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

            System.out.println("Enter a number : ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    System.out.println(n + " is not prime");
                    return;
                }
            }
            System.out.println(n + " is prime");


    }
}
