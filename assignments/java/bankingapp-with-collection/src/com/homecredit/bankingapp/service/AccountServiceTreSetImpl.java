package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountValidationException;
import com.homecredit.bankingapp.exception.NoAccountFoundException;
import com.homecredit.bankingapp.model.Account;

import java.util.*;

public class AccountServiceTreSetImpl implements AccountService {
    private static Comparator<Account> SORT_BY_ID_ASC = new Comparator<Account>() {

        @Override
        public int compare(Account o1, Account o2) {
            return o2.getAccountId() - o1.getAccountId();
        }
    };
    private Set<Account> accounts = new TreeSet<>(SORT_BY_ID_ASC);


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
        if (account.getBranch() == null || account.getBranch().isEmpty() ) {
            throw new AccountValidationException("Account Branch can't be empty");
        }
        System.out.println("Account called -> ");

        accounts.add(account);
        return true;
    }

    @Override
    public boolean update(int accountId, Set<Integer> set) throws Exception {

        if (!set.contains(accountId)) {
//        if(this.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
//        if(AccountUtils.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
            throw new NoAccountFoundException("No account found for given account id - " + accountId);
        } else {
            for (Account value : accounts) {
                if (value.getAccountId() == accountId) {
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
                            value.setName(name);
                            break;
                        case 2:
                            System.out.println("Enter your new branch: ");
                            String branch = sc.nextLine();
                            value.setBranch(branch);
                            break;
                        case 3:
                            System.out.println("Enter your new Account type: ");
                            String accType = sc.nextLine();
                            value.setType(accType);
                            break;
                        default:
                            throw new Exception(" Please enter  correct option !!!");
                    }
                    break;

                }
            }
        }
        return true;
    }


    @Override
    public boolean delete(int accountId) throws Exception {

        for(Account account:accounts){
            if(account.getAccountId()==accountId) {
                accounts.remove(account);
                return true;
            }
        }
            throw new Exception("Account not found with id : " + accountId);

    }

    @Override
    public void get(int accountId) throws Exception {

        for(Account acc:accounts) {
            if (acc.getAccountId() == accountId) {
                System.out.format("%1s %8s %13s %18s %23s", acc.getAccountId(), acc.getName(),
                        acc.getBalance(), acc.getType(), acc.getBranch());
                return;
            }
        }
            throw new Exception("Account not found with id : " + accountId);

    }

    @Override
    public void getAll () throws Exception {
        for(Account acc:accounts) {
            System.out.format("%1s %8s %13s %18s %23s", acc.getAccountId(), acc.getName(),
                    acc.getBalance(), acc.getType(), acc.getBranch());
            System.out.println();
        }

    }
}
