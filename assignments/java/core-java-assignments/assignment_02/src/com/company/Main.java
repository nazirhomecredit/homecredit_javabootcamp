package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter a number : ");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        if(( n&1) !=0){
            System.out.println(n+" is odd");
        } else
            System.out.println(n+ " is even" );



    }
}
