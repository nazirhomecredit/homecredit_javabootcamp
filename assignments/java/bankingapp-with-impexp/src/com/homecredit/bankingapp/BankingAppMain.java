package com.homecredit.bankingapp;


import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountService;
import com.homecredit.bankingapp.service.AccountServiceImpl;

import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BankingAppMain {
    private static AccountService accountServices;
    public static void main(String[] args) {


        int input;
        int accId;
        accountServices = new AccountServiceImpl();

        do {
            Scanner Sc = new Scanner(System.in);
            Scanner S = new Scanner(System.in);
            System.out.println("1.CREATE ACCOUNT");
            System.out.println("2.UPDATE ACCOUNT");
            System.out.println("3.DELETE ACCOUNT");
            System.out.println("4.GET ACCOUNT");
            System.out.println("5.GET ALL ACCOUNT");
            System.out.println("6.PRINT STATISTICS");
            System.out.println("7.IMPORT");
            System.out.println("8.EXPORT");
            System.out.println("9.FOR EXIT");
            System.out.print("Enter Your Choice : ");
            input = Sc.nextInt();
            switch (input) {
                case 1:
                    try{
                        System.out.print("Enter Id : ");
                        int no = Sc.nextInt();
                        System.out.print("Enter Name : ");
                        String na = S.nextLine();
                        System.out.print("Enter Balance : ");
                        double bal = Sc.nextDouble();
                        System.out.print("Enter Type : ");
                        String type = S.nextLine();
                        System.out.print("Enter Branch : ");
                        String branch = S.nextLine();
                        Account account = new Account(no,na,type,bal,branch);
                        if(accountServices.create(account)){
                            System.out.println("-------------------------------------------------------------------------------");
                            System.out.println("Account Creation Done SuccessFully");
                            System.out.println("-------------------------------------------------------------------------------");
                        }
                    }catch (Exception exception){
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("Invalid Input ");
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter Account Id : ");
                        int no = Sc.nextInt();
                        if (accountServices.isValid(no)) {
                            if(accountServices.update(no , accountServices.inputMethod(no)));
                            {
                                System.out.println("Account Updated with account id  :"+no);
                            }
                        }
                        System.out.println("-------------------------------------------------------------------------------");

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;
                case 3:
                    try{
                        System.out.println("Enter Account id");
                        accId = Sc.nextInt();
                        System.out.println("-------------------------------------------------------------------------------");
                        if(accountServices.delete(accId)){
                            System.out.println("Account deleted from the Record with account id "+accId);
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;
                case 4:
                    try{
                        System.out.println("Enter Account id");
                        accId = Sc.nextInt();
                        System.out.printf("%3s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE" , "BRANCH");
                        System.out.println();
                        Account tempAcc = accountServices.get(accId);
                        System.out.format("%4s %10s %13s %15s %25s ",tempAcc.getAccountId(),tempAcc.getName(),tempAcc.getBalance(),tempAcc.getType(), tempAcc.getBranch());
                        System.out.println();
                        System.out.println("-------------------------------------------------------------------------------");
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;

                case 5:
                    try{
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("%3s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
                        System.out.println();

                        Collection<Account> accounts = accountServices.getAll();
                        for(Account acc: accounts){
                        System.out.format("%3s %10s %13s %15s %25s ",acc.getAccountId(),acc.getName(),acc.getBalance(),acc.getType(),acc.getBranch());
                        System.out.println();
                        }
                        System.out.println("-------------------------------------------------------------------------------");

                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;
                case 6:
                    printStatistics();
                    break;
                case 7:
                    accountServices.bulkImport();
                    break;
                case 8:
                    accountServices.bulkExport();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("OOPS! wrong Input");
                    System.out.println("-------------------------------------------------------------------------------");


            }

        }
        while (input != 0);
    }

    private static void printStatistics() {
        System.out.println("No of accounts which has balance more than 1 lac - " + accountServices.getNoOFAccountBalanceMoreThan1Lac());
        System.out.println("Show no of account by account type - " + accountServices.getNoOfAccountByAccountType() );
        System.out.println("Show no of account by account type with Sorting - " + accountServices.getNoOfAccountByAccountTypeWithSorting() );
        System.out.println("Show avg balance by account type - " + accountServices.getAvgBalanceByAccountType());
        System.out.println("List account ids whose account name contains given name - " + accountServices.getListOfAccountIdsWithGivenName("Sanskar"));
    }

}
