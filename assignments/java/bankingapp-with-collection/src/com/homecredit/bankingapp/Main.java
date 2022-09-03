package com.homecredit.bankingapp;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.util.HelpingFun;
import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        Set<Integer> set = new HashSet<Integer>();
        int input;

//          AccountService accountServices= new AccountServiceHshMapImpl();
//            AccountService accountServices = new AccountServiceLnkListImpl();
//            AccountService accountServices = new AccountServiceTreMapImpl();
          AccountService accountServices = new AccountServiceArrListImpl();
//          AccountService accountServices= new AccountServiceHshSetImpl();
//          AccountService accountServices= new AccountServiceTreSetImpl();


        do {
            System.out.println();
            System.out.println("*****************************************************************");
            System.out.println("1.CREATE ACCOUNT");
            System.out.println("2.UPDATE ACCOUNT");
            System.out.println("3.DELETE ACCOUNT");
            System.out.println("4.SINGLE ACCOUNT DETAILS");
            System.out.println("5.ALL ACCOUNT DETAILS");
            System.out.println("6.PRINT DIFFERENT STATISTICS");
            System.out.println("7.BULK EXPORT");
            System.out.println("8.BULK IMPORT");
            System.out.println("0.EXIT FROM APP");
            System.out.println("*******************************************************************");

            System.out.print("Enter Your Choice : ");

                input = Sc.nextInt();

                switch (input) {
                    case 1:
//            try {
//                Scanner s = new Scanner(System.in);
//                Scanner s1 = new Scanner(System.in);
//
//                System.out.print("Enter name : ");
//                String name = s1.nextLine();
//                System.out.print("Enter Initial balance : ");
//                double balance = s.nextDouble();
//                System.out.print("Enter Account Type : SAVING : CURRENT : LOAN  :");
//                String accountType = s1.nextLine();
//                System.out.print("Enter Branch : ");
//                String branch = s1.nextLine();
//
//                HelpingFun hf = new HelpingFun();
//                int id = hf.getRandomNumber(100, 999);
//                while (!set.isEmpty() && set.contains(id)) {
//                    id = hf.getRandomNumber(100, 999);
//                }
//                set.add(id);
//
//
//                Account account = new Account(id, name, accountType, balance, branch);
//
//
//                boolean isCreated = accountServices.create(account);
//
//
//                if (isCreated) {
//                    System.out.println("-------------------------------------------------------------------------------");
//                    System.out.println("Account Creation Done SuccessFully With Id : " + id);
//                    System.out.println("-------------------------------------------------------------------------------");
//                }

//            }
//
//        catch (InputMismatchException ee){
//            System.out.println("-------------------------------------------------------------------------------");
//            System.out.println("Not Valid input");
//            System.out.println("-------------------------------------------------------------------------------");
//        }
//        catch (Exception e) {
//            System.out.println("Please retry" + e.getMessage());
//            e.printStackTrace();

//        }
                        ((AccountServiceArrListImpl) accountServices).defaultAcc();

                    break;
                case 2:
                    try {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("Enter Account Id");
                        int id =Sc.nextInt();
                        if(accountServices.update(id,set)){
                            System.out.println("Account Updated with account id  :"+id);
                        }
                        System.out.println("-------------------------------------------------------------------------------");

                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;

                case 3:
                    try{
                        System.out.println("Enter Account id");
                       int accId = Sc.nextInt();
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
                        int accId = Sc.nextInt();
                        System.out.printf("%3s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
                        System.out.println();

                        accountServices.get(accId);
                        System.out.println();
                        System.out.println("-------------------------------------------------------------------------------");
                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;
//
                case 5:
                    try{
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("%3s %10s %15s %20s %20s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
                        System.out.println();

                        accountServices.getAll();
                        System.out.println("All Accounts are Fetched ");
                        System.out.println("-------------------------------------------------------------------------------");

                    }catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    break;

                case 6:
                    ((AccountServiceArrListImpl) accountServices).printStats();
                    break;
                case 7:
                    ((AccountServiceArrListImpl) accountServices).bulkExport();
                    break;
                case 8:
                    ((AccountServiceArrListImpl) accountServices).bulkImport();


            }

        }
        while (input != 0);
    }
    }

