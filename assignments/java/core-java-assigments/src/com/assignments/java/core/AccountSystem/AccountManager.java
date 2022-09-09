package com.assignments.java.core.AccountSystem;



import java.util.Scanner;

public class AccountManager {
    public static void main(String[] args) {
        int choice;
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        AccountService acc = new AccountService();
        do {
            System.out.println("1.Create Account");
            System.out.println("2.Get All Account");
            System.out.println("3.Update Account");
            System.out.println("4.Close Account");
            System.out.println("5.Get Account Details by ID:");
            System.out.println("6.Press 0 for Exit");
            System.out.print("Enter Your Choice : ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
//                    System.out.print("Enter Id : ");
//                    int no = s.nextInt();
                    System.out.print("Enter Name : ");
                    String na = s1.nextLine();
                    System.out.print("Enter Balance : ");
                    double bal = s.nextDouble();
                    System.out.print("Enter Type : ");
                    String type = s1.nextLine();
                    System.out.print("Enter Active : ");
                    boolean active = s.nextBoolean();
                    Account account = new Account(na, bal, type, active);
                    String status = acc.createAccount(account);
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    System.out.println(status);
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    break;

                case 2:
                    acc.viewAll();
                    break;
                case 3:
                    System.out.println("What is the Id:");

                    int no1 = s.nextInt();
                    System.out.print("Enter Name : ");
                    String na2 = s1.nextLine();
                    System.out.print("Enter Balance : ");
                    double ba2 = s.nextDouble();
                    System.out.print("Enter Type : ");
                    String type1 = s1.nextLine();
                    System.out.print("Enter Active : ");
                    boolean active1 = s.nextBoolean();
                    Account account1 = new Account(no1, na2, ba2, type1, active1);
                    acc.updateAccount(no1,account1);
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    System.out.println("Account Updated Successfully");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    break;

                case 4:
                    System.out.println("Enter the Id to delete data:");
                    int n = s.nextInt();
                    acc.deleteAccount(n);
                    break;

                case 5:
                    System.out.println("Enter the Id to view Account details:");
                    int n1 = s.nextInt();
                    acc.viewOne(n1);
                    break;

                    default:
                        System.out.println("Wrong Input!! Try Again");

            }
        } while (choice != 0);
    }
}
