//package com.homecredit.bankingapp.service;
//
//import com.homecredit.bankingapp.exception.AccountException;
//import com.homecredit.bankingapp.exception.AccountValidationException;
//import com.homecredit.bankingapp.exception.NoAccountFoundException;
//import com.homecredit.bankingapp.model.Account;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Scanner;
//
//public class AccountServiceHashSetImpl implements AccountService {
//        HashSet<Account> accounts =new HashSet<>();
////    private static int counter = 0;
//    Scanner s = new Scanner(System.in);
//    Scanner s1 = new Scanner(System.in);
//    @Override
//    public boolean create(Account account) throws AccountException {
//        if(account.getName() == null || account.getName().isEmpty())
//        {
//            throw new AccountValidationException("Account Name can't be empty");
//        }
//        accounts.add(account);
//        return true;
//    }
//
//    @Override
//    public boolean update(int accountId, Account account) throws AccountException {
//        Iterator<Account> li = accounts.iterator();
//        while (li.hasNext()) {
//            Account m = li.next();
//            if (m.getAccountId() == accountId) {
//                accounts.remove(li);
//                accounts.add(account);
//                return true;
//            }
//        }
//       throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public boolean delete(int accountId) throws AccountException{
//        Iterator<Account> i = accounts.iterator();
//        while (i.hasNext()) {
//            Account m = i.next();
//            if (m.getAccountId() == accountId) {
//                i.remove();
//                return true;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public Collection<Account> getAll() {
//
//        Iterator<Account> i = accounts.iterator();
//        while (i.hasNext()) {
//            Account m = i.next();
//            System.out.println(m);
//        }
//        return null;
//    }
//
//    @Override
//    public Account get(int accountId) throws AccountException {
//        Iterator<Account> i = accounts.iterator();
//        while (i.hasNext()) {
//            Account m = i.next();
//            if (m.getAccountId() == accountId) {
//                return m;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public boolean isValid(int accountId) throws AccountException{
//        for (Account acc : accounts) {
//            if (acc != null && acc.getAccountId() == accountId) {
//                return true;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
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
//
//}
