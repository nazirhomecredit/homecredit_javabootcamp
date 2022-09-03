package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountValidationException;
import com.homecredit.bankingapp.exception.NoAccountFoundException;
import com.homecredit.bankingapp.model.Account;
import javafx.util.Pair;
import org.omg.PortableInterceptor.INACTIVE;


import java.io.*;
import java.util.*;

public class AccountServiceArrListImpl implements AccountService {



    private ArrayList<Account> accounts = new ArrayList<>();
    public void defaultAcc() {
        Account ac1 = new Account(1, "nazir", "SAVING", 10200, "PNB");
        Account ac2 = new Account(2, "ASHIF", "CURRENT", 56000, "PNB");
        Account ac3 = new Account(3, "DANISH", "LOAN", 4000, "BOI");
        Account ac4 = new Account(4, "MOHAN", "LOAN", 10060, "SBI");
        accounts.add( ac1);
        accounts.add( ac2);
        accounts.add( ac3);
        accounts.add( ac4);

        System.out.println("account created successfully...");
    }

    @Override
    public boolean create(Account account) throws AccountValidationException {
        if (account.getName() == null || account.getName().isEmpty()) {
            throw new AccountValidationException("Account Name can't be empty");
        }
        if (account.getType() == null || account.getType().isEmpty()) {
            throw new AccountValidationException("Account Type can't be empty");
        }
        if (account.getBalance() < 0) {
            throw new AccountValidationException("Initial Amount Can not be negative ");
        }
        if (account.getBranch() == null || account.getBranch().isEmpty()) {
            throw new AccountValidationException("Account Branch can't be empty");
        }

        return accounts.add(account);
    }

    @Override
    public boolean update(int accountId, Set<Integer> set) throws Exception {

        boolean isUpdateSuccess = false;
        if (!set.contains(accountId)) {
//        if(this.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
//        if(AccountUtils.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
            throw new NoAccountFoundException("No account found for given account id - " + accountId);
        } else {
            for (Account acc : accounts) {
                if (acc.getAccountId() == accountId) {
                    System.out.println("What data you want to change: ");
                    System.out.println("Press 1 to update name :");
                    System.out.println("Press 2 to update branch :");
                    System.out.println("Press 3 to update account type :");
                    Scanner sc = new Scanner(System.in);
                    Scanner sc1 = new Scanner(System.in);

                    int choice = sc1.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter your new name: ");
                            String name = sc.nextLine();
                            acc.setName(name);
                            break;
                        case 2:
                            System.out.println("Enter your new branch: ");
                            String branch = sc.nextLine();
                            acc.setBranch(branch);
                            break;
                        case 3:
                            System.out.println("Enter your new Account type: ");
                            String accType = sc.nextLine();
                            acc.setType(accType);
                            break;
                        default:
                            break;

                    }

                    isUpdateSuccess = true;
                    break;

                }

            }

        }
        return isUpdateSuccess;
    }

    @Override
    public boolean delete(int accountId) throws Exception {

        for (Account acc : accounts) {
            if (acc.getAccountId() == accountId) {
                accounts.remove(acc);
                return true;
            }
        }
        throw new Exception("Account not found with id : " + accountId);

    }

    @Override
    public void get(int accountId) throws Exception {
        for (Account acc : accounts) {
            if (acc.getAccountId() == accountId) {
                System.out.format("%1s %8s %13s %18s %23s", acc.getAccountId(), acc.getName(), acc.getBalance(), acc.getType(), acc.getBranch());
                return;
            }
        }
        throw new Exception("Account not found with id : " + accountId);


    }

    @Override
    public void getAll() {
        for (Account acc : accounts) {

            System.out.format("%1s %8s %13s %18s %23s", acc.getAccountId(), acc.getName(), acc.getBalance(), acc.getType(), acc.getBranch());
            System.out.println();
        }

    }

    private void printMoreThan1L(ArrayList<Account> accounts) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%3s %10s %15s %20s %14s ", "ID", "NAME", "BALANCE", "ACCOUNT TYPE", "BRANCH");
        System.out.println();
        for (Account acc : accounts) {
            if (acc.getBalance() > 100000) {
                System.out.format("%1s %8s %15s %15s %20s", acc.getAccountId(), acc.getName(),
                        acc.getBalance(), acc.getType(), acc.getBranch());
                System.out.println();

            }

        }
        System.out.println("--------------------------------------------------------------------------------");

    }

    private void ByAccType(ArrayList<Account> accounts) {
        int loan = 0, saving = 0, current = 0;
        for (Account acc : accounts) {
            if (acc.getType().equals("LOAN")) {
                loan++;
            } else if (acc.getType().equals("SAVING"))
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


    private void sortByAccType(ArrayList<Account> accounts) {
        int loan = 0, saving = 0, current = 0;
        for (Account acc : accounts) {
            if (acc.getType().equals("LOAN")) {
                loan++;
            } else if (acc.getType().equals("SAVING"))
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


    private void avgBalAccType(ArrayList<Account> accounts) {

        double savingSum = 0, loanSum = 0, currentSum = 0;
        int loanCount = 0, savingCount = 0, currentCount = 0;

        for (Account acc : accounts) {
            if (acc.getType().equals("SAVING")) {
                savingSum += acc.getBalance();
                savingCount++;
            } else if (acc.getType().equals("LOAN")) {
                loanSum += acc.getBalance();
                loanCount++;
            } else {
                currentSum += acc.getBalance();
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

    private void idsContainsName(ArrayList<Account> accounts, String nam) {

        System.out.println("    ACCOUNT ID   ");
        for (Account acc : accounts) {
            if (acc.getName().contains(nam)) {
                System.out.println("       " + acc.getAccountId());
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
                printMoreThan1L(accounts);
                break;

            case 2:
                ByAccType(accounts);
                break;

            case 3:
                sortByAccType(accounts);
                break;
            case 4:
                avgBalAccType(accounts);
                break;
            case 5:
                System.out.println("ENTER NAME: ");
                String nam = Sc.nextLine();
                idsContainsName(accounts, nam);


        }
    }


    public void bulkExport() {
        try (FileWriter out = new FileWriter("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-collection\\Account.txt")) {
            accounts.stream().map(acc -> acc.getAccountId() + "," + acc.getName() + "," + acc.getType() + "," +
                    acc.getBalance() + "," + acc.getBranch() + ","  + "\n")
                    .forEach(rec -> {
                        try {
                            out.write(rec);
                        } catch (Exception e) {
                            System.out.println("Error occured while Exporting file. " + e.getMessage());
                            e.printStackTrace();
                        }
                    });




            System.out.format("%d Employees are exported successfully.", accounts.size());
            System.out.println();
        } catch (IOException ioe) {
            System.out.println("Error occured while exporting employee data. " + ioe.getMessage());
        }
    }



    public void bulkImport() {

        int c = 0;
        try (Scanner in = new Scanner(new FileReader("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-collection\\Account.txt"))) {
            System.out.println("Importing file...");
            while (in.hasNext()) {
                String account = in.nextLine();
                System.out.println("Importing Account - " + account);
                Account a = new Account();
                StringTokenizer tokenizer = new StringTokenizer(account, ",");
                a.setAccountId(Integer.parseInt(tokenizer.nextToken()));
                a.setName(tokenizer.nextToken());
                a.setType(tokenizer.nextToken());
                a.setBalance(Double.parseDouble(tokenizer.nextToken()));
                a.setBranch(tokenizer.nextToken());
                this.create(a);
                accounts.add(a);
                c++;
            }
            System.out.println("Importing successful... " + c);
        } catch (Exception e) {
            System.out.println("Error occured while importing data. " + e.getMessage());

        }

    }

}





