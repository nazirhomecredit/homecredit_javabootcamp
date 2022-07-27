package com.company;

import java.util.Random;


public class AccountService {

    Random random = new Random();
    private static int counter = 0;
    private Account[] accounts = new Account[10];
    // create account
    public String createAccount(Account account) {

        account.id = random.nextInt(50);
        accounts[counter] = account;
        counter++;
        return "Account Created Successfully with this ID" + " " + account.id ;
    }

    // update account
    public boolean updateAccount(int accountId, Account account) {
//        // Approach #1
//        Account accountForUpdate = this.getAccount(accountId);
//        accountForUpdate.setBalance(account.getBalance());
//        accountForUpdate.setActive(account.isActive());
//        accountForUpdate.setType(account.getType());
        // Approach #2
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getId() == accountId) {
                accounts[i] = account;
            }
        }
        return true;
    }

    // delete account
    public boolean deleteAccount(int accountId) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getId() == accountId) {
                accounts[i] = null;
            }
        }
        return true;
    }

    // get account
    public Account getAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public void viewAll() {
        System.out.println("*=============================================");
        for (Account account : accounts) {
            if (account != null)
                System.out.println(account.getId() + " " + account.getName() + " "
                        + account.getBalance() + " " + account.getType() + " " + account.getActive());
        }
        System.out.println("=============================================");
    }

    public boolean viewOne(int id) {
        System.out.println("================================================");
        for (Account account : accounts) {
            if (account != null) {
                if (account.getId() == id) {
                    System.out.println(account.getId() + " " + account.getName() + " "
                            + account.getBalance() + " " + account.getType() + " " + account.getActive());
                }

            }

        }
        System.out.println("================================================");
        return true;
    }
    public Account[] getAllAccounts ( int id)
    {
        return this.accounts;
    }
}