package com.homecredit.bankingapp;

import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountServiceArrImpl;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        Scanner s = new Scanner(System.in);

        AccountServiceArrImpl acc = new AccountServiceArrImpl();

        do {
            System.out.println();
            System.out.println("=========================================================");
            System.out.println("1.Create Account");
            System.out.println("2.Update Account");
            System.out.println("3.Close Account");
            System.out.println("4.Get Account by ID ");
            System.out.println("5.Get All Account");
            System.out.println("6.Print Different Statics");
            System.out.println("7.Bulk Export");
            System.out.println("8.Bulk Import");
            System.out.println("0.Exit App");
            System.out.println("=========================================================");
            System.out.println("Enter Your Choice : ");


                choice = s.nextInt();


try {
        switch (choice) {
            case 1:
                acc.createAccount();
                break;
            case 2:
                try {
                    acc.updateAccount();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                System.out.println("Enter the Id to delete data:");
                int n = s.nextInt();
                try {
                    acc.deleteAccount(n);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println("Enter Account id");
                int accId = s.nextInt();
                System.out.printf("%1s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
                System.out.println();
                acc.getAccount(accId);
                break;
            case 5:
                System.out.printf("%1s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
                System.out.println();
                acc.viewAll();
                break;

            case 6:
                break;
            case 7:
                break;
            case 8:
                break;


        }
          } catch (InputMismatchException ie)
        {
            System.out.println("ENTER RIGHT FORMAT OF DATA ");
        }
            catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        } while (choice != 0);
    }
}

