//package com.homecredit.bankingapp.service;
//
//import com.homecredit.bankingapp.exception.AccountException;
//import com.homecredit.bankingapp.exception.AccountValidationException;
//import com.homecredit.bankingapp.exception.NoAccountFoundException;
//import com.homecredit.bankingapp.model.Account;
//
//import java.util.*;
//
//public class AccountServiceLnkListImpl implements AccountService {
//    private List<Account> accounts = new LinkedList<>();
//    Scanner s = new Scanner(System.in);
//    Scanner s1 = new Scanner(System.in);
//
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
//    public boolean update(int accountId, Account account) throws AccountException{
//        ListIterator<Account> li = accounts.listIterator();
//        while (li.hasNext()) {
//            Account m = li.next();
//            if (m.getAccountId() == accountId) {
//                li.set(account);
//                return true;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//
//    }
//
//
//    @Override
//    public boolean delete(int accountId) throws AccountException {
//        if(accounts.size() == 0 ) {
//            throw new AccountException("No Records Available");
//        }
//        for(Account acc : accounts) {
//            if(acc.getAccountId() == accountId){
//                accounts.remove(acc);
//                return true;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public Account get(int accountId) throws AccountException {
//        for(Account acc : accounts){
//            if(acc.getAccountId() == accountId){
//                return acc;
//            }
//        }
//        throw new NoAccountFoundException("No account found for given account id - " + accountId);
//    }
//
//    @Override
//    public Collection<Account> getAll() throws AccountException {
//        if(accounts.size()==0){
//            throw new AccountException("No account is present in data base");
//        }
//
//        return accounts;
//    }
//
//    @Override
//    public boolean isValid(int accountId) throws AccountException {
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
//}
