package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.model.Account;
import javafx.util.Pair;


import javax.security.auth.login.AccountException;
import java.util.*;

public class AccountServiceArrImpl {

    Random random = new Random();
    private static int counter = 0;
    private Account[] accounts = new Account[10];

    Scanner s = new Scanner(System.in);
    Scanner s1 = new Scanner(System.in);


    // create account
    public void createAccount() {
        try {

            System.out.print("Enter Name : ");
            String na = s1.nextLine();
            System.out.println("Enter Branch : ");
            String bran = s1.nextLine();
            System.out.print("Enter Type : ");
            String type = s1.nextLine();
            System.out.print("Enter Balance : ");
            double bal = s.nextDouble();

            int id = random.nextInt(50);

            accounts[counter] = new Account(id, na, bal, type, bran);
        } catch (InputMismatchException ie) {
            System.out.println("PROVIDE VALID INPUT ");
            System.out.println(ie.getMessage());
        }

        System.out.println("ACCOUNT CREATED SUCCESSFULLY WITH THIS ID" + " " + accounts[counter++].getId());
    }


    // update account
    public void updateAccount() throws Exception {
        System.out.println("Enter Id : ");
        int id=s.nextInt();
        boolean isExist=false;
        for (int i = 0; i <counter; i++) {
            if (accounts[i] != null && accounts[i].getId() == id) {
                System.out.println("1. Update Name: ");
                System.out.println("2. Update Branch: ");
                System.out.println("3. Balance: ");

                int choice=s.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Enter New Name: ");
                        String n= s1.nextLine();
                        accounts[i].setName(n);
                        break;
                    case 2:
                        System.out.println("Enter New Branch: ");
                        String b= s1.nextLine();
                        accounts[i].setName(b);
                        break;
                    case 3:
                        System.out.println("Enter new Balance: ");
                        double ba=s.nextDouble();
                        accounts[i].setBalance(ba);
                        break;
                    default:
                            throw new AccountException("ENTER VALID INPUT .");

                }

                isExist=true;
                break;
            }
        }
        if(!isExist)
            throw new AccountException("THIS ID DOES NOT EXIST ");
        else
            System.out.println("ACCOUNT UPDATED SUCCESSFULLY ");




    }

    // delete account
    public void deleteAccount(int accountId) throws Exception {
boolean isExist=false;
        for (int i = 0; i < counter; i++) {
            if (accounts[i] != null && accounts[i].getId() == accountId) {
isExist=true;
                if(counter==1)
                    counter=0;
                else
                {
                    Account ac=accounts[counter-1];
                    accounts[i]=ac;
                    counter--;
                    break;
                }

            }
        }
        if(!isExist)
            throw new AccountException("THIS ID DOES NOT EXIST ");
        else
            System.out.println("ACCOUNT DELETED SUCCESSFULLY ");

    }

    // get account
    public Account getAccount(int accountId) {

        for (int i = 0; i < counter; i++) {
            if (accounts[i].getId() == accountId) {
                System.out.format("%3s %8s %13s %18s %23s", accounts[i].getId(), accounts[i].getName(), accounts[i].getBalance(), accounts[i].getType(), accounts[i].getBranch());
                System.out.println();
            }
        }
        return null;
    }

    public void viewAll() {

        for (int i = 0; i < counter; i++) {

            System.out.format("%3s %8s %13s %18s %23s", accounts[i].getId(), accounts[i].getName(), accounts[i].getBalance(), accounts[i].getType(), accounts[i].getBranch());
            System.out.println();
        }

    }



    private void printMoreThan1L() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%3s %10s %15s %20s %14s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
        System.out.println();
        for (int i=0;i<counter;i++) {
            if (accounts[i].getBalance() > 100000) {
                System.out.format("%1s %8s %15s %15s %20s", accounts[i].getId(), accounts[i].getName(),
                        accounts[i].getBalance(), accounts[i].getType(), accounts[i].getBranch());
                System.out.println();

            }

        }
        System.out.println("--------------------------------------------------------------------------------");

    }

    private void ByAccType() {
        int loan = 0, saving = 0, current = 0;
        for (int i=0;i<counter;i++) {
            if (accounts[i].getType().equals("LOAN")) {
                loan++;
            } else if (accounts[i].getType().equals("SAVING"))
                saving++;
            else
                current++;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%4s %10s %13s ", "SAVING", "CURRENT", "LOAN");
        System.out.println();
        System.out.format("%3s %10s %15s", saving, current, loan);
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
    }


    private void sortByAccType() {
        int loan = 0, saving = 0, current = 0;
        for (int i=0;i<counter;i++) {
            if (accounts[i].getType().equals("LOAN")) {
                loan++;
            } else if (accounts[i].getType().equals("SAVING"))
                saving++;
            else
                current++;
        }

        PriorityQueue<Pair<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.add(new Pair<>("LOAN", loan));
        pq.add(new Pair<>("SAVING", saving));
        pq.add(new Pair<>("CURRENT", current));


        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        while (!pq.isEmpty()) {
            Pair<String, Integer> pa = pq.poll();
            System.out.println(pa.getKey() + "   =========>   " + pa.getValue());
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
    }


    private void avgBalAccType() {

        double savingSum = 0, loanSum = 0, currentSum = 0;
        int loanCount = 0, savingCount = 0, currentCount = 0;

        for (int i=0;i<counter;i++) {
            if (accounts[i].getType().equals("SAVING")) {
                savingSum += accounts[i].getBalance();
                savingCount++;
            } else if (accounts[i].getType().equals("LOAN")) {
                loanSum += accounts[i].getBalance();
                loanCount++;
            } else {
                currentSum += accounts[i].getBalance();
                currentCount++;
            }
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%4s %10s %13s ", "SAVING", "CURRENT", "LOAN");
            System.out.println();
            System.out.format("%3s %10s %10s", savingSum / savingCount, currentSum / currentCount, loanSum / loanCount);
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");

        }
    }

    private void idsContainsName( String nam) {

        System.out.println("    ACCOUNT ID   ");
        for (int i=0;i<counter;i++) {
            if (accounts[i].getName().contains(nam)) {
                System.out.println("       " + accounts[i].getId());
            }
        }


    }

    public void printStats() {
        System.out.println();
        System.out.println("ENTER 1. No of accounts which has balance more than 1 lac ");
        System.out.println("ENTER 2. Show no of account by account type ");
        System.out.println("ENTER 3. Show no of accounts by account type with sorting ");
        System.out.println("ENTER 4. Show avg balance by account type ");
        System.out.println("ENTER 5. List account ids whose account name contains given name ");

        Scanner Sc = new Scanner(System.in);
        int stats = Sc.nextInt();


        switch (stats) {
            case 1:
                printMoreThan1L();
                break;

            case 2:
                ByAccType();
                break;

            case 3:
                sortByAccType();
                break;
            case 4:
                avgBalAccType();
                break;
            case 5:
                System.out.println("ENTER NAME: ");
                String nam = Sc.nextLine();
                idsContainsName(nam);
                break;


        }
    }


}
