//package com.homecredit.bankingapp.service;
//
//import com.homecredit.bankingapp.exception.AccountException;
//import com.homecredit.bankingapp.exception.AccountValidationException;
//import com.homecredit.bankingapp.exception.NoAccountFoundException;
//import com.homecredit.bankingapp.model.Account;
//
//import java.util.*;
//
//public class AccountServiceTreeMapImpl implements AccountService{
//    TreeMap<Integer, Account> accounts = new TreeMap<>();
//    Scanner s = new Scanner(System.in);
//    Scanner s1 = new Scanner(System.in);

//    @Override
//    public boolean create(Account account) throws AccountException {
//        if(account.getName() == null || account.getName().isEmpty())
//        {
//            throw new AccountValidationException("Account Name can't be empty");
//        }
//            accounts.put(account.getAccountId(), account);
//        return true;
//    }
//
//    @Override
//    public boolean update(int accountId, Account account) throws AccountException {
//        if (accounts.containsKey(accountId)) {
//            accounts.put(accountId, account);
//            return true;
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public boolean delete(int accountId) throws AccountException{
//        if (accounts.containsKey(accountId)) {
//            accounts.remove(accountId);
//            return true;
//        }
//
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public Collection<Account> getAll() throws AccountException{
//        Set<Map.Entry<Integer, Account>> s = accounts.entrySet();
//        for (Map.Entry<Integer, Account> entry : s) {
//            System.out.println("Account ID : " + entry.getKey()
//                    + "\t Value : "
//                    + entry.getValue());
//        }
//        return null ;
//    }
//
//    @Override
//    public Account get(int accountId) throws AccountException {
//        if (accounts.containsKey(accountId)) {
//            Account a1 = accounts.get(accountId);
//            return a1;
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public boolean isValid(int accountId) throws AccountException {
//        if (accounts.containsKey(accountId)) {
//            return true;
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//
//    }
//
//    public Account inputMethod(int no) {
//
//        System.out.print("Enter Name : ");
//        String na = s.nextLine();
//        System.out.print("Enter Type : ");
//        String type = s1.nextLine();
//        System.out.print("Enter Balance : ");
//        double bal = s.nextDouble();
//        System.out.print("Enter Branch : ");
//        String branch = s1.nextLine();
//        Account account = new Account(no, na, type, bal, branch);
//        return account;
//    }
//}
