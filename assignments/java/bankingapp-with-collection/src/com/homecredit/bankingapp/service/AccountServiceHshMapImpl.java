//package com.homecredit.bankingapp.service;
//
//import com.homecredit.bankingapp.exception.AccountValidationException;
//import com.homecredit.bankingapp.exception.NoAccountFoundException;
//import com.homecredit.bankingapp.model.Account;
//
//import java.util.*;
//
//
//public class AccountServiceHshMapImpl implements AccountService {
//    private HashMap<Integer, Account> accounts = new HashMap<>();
//
//
//    @Override
//    public boolean create(Account account) throws AccountValidationException {
//        if (account.getName() == null || account.getName().isEmpty()) {
//            throw new AccountValidationException("Account Name can't be empty");
//        }
//        if (account.getType() == null || account.getType().isEmpty()) {
//            throw new AccountValidationException("Account Type can't be empty");
//        }
//        if (account.getBalance() < 0) {
//            throw new AccountValidationException("Initial Amount Can not be negative ");
//        }
//        if (account.getBranch() == null || account.getBranch().isEmpty()) {
//            throw new AccountValidationException("Account Branch can't be empty");
//        }
//
//        accounts.put(account.getAccountId(), account);
//        return true;
//    }
//
//    @Override
//    public boolean update(int accountId, Set<Integer> set) throws Exception {
//
//        if (!accounts.containsKey(accountId)) {
////        if(this.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
////        if(AccountUtils.isValidAccount(accountId, Collections.unmodifiableList(accounts))) {
//            throw new NoAccountFoundException("No account found for given account id - " + accountId);
//        } else {
//             Account value=accounts.get(accountId);
//
//                    System.out.println("What data you want to change: ");
//                    System.out.println("Press 1 to update name :");
//                    System.out.println("Press 2 to update branch :");
//                    System.out.println("Press 3 to update account type :");
//                    Scanner sc = new Scanner(System.in);
//                    Scanner sc1 = new Scanner(System.in);
//
//                    int choice = sc1.nextInt();
//                    switch (choice) {
//                        case 1:
//                            System.out.println("Enter your new name: ");
//                            String name = sc.nextLine();
//                            value.setName(name);
//                            break;
//                        case 2:
//                            System.out.println("Enter your new branch: ");
//                            String branch = sc.nextLine();
//                            value.setBranch(branch);
//                            break;
//                        case 3:
//                            System.out.println("Enter your new Account type: ");
//                            String accType = sc.nextLine();
//                            value.setType(accType);
//                            break;
//                        default:
//                            break;
//
//                    }
//
//                }
//
//        return true;
//
//    }
//
//
//    @Override
//    public boolean delete(int accountId) throws Exception {
//
//            if (accounts.containsKey(accountId)) {
//                accounts.remove(accountId);
//                return true;
//            }
//        else
//           throw new Exception("Account not found with id : " + accountId);
//
//    }
//
//    @Override
//    public void get(int accountId) throws Exception {
//
//            if (accounts.containsKey(accountId)) {
//                Account acc=accounts.get(accountId);
//                    System.out.format("%1s %8s %13s %18s %23s", acc.getAccountId(), acc.getName(),
//                            acc.getBalance(), acc.getType(), acc.getBranch());
//
//            }
//            else
//               throw new Exception("Account not found with id : " + accountId);
//
//
//        }
//
//        @Override
//        public void getAll () throws Exception {
//            for (Map.Entry<Integer, Account> acc : accounts.entrySet()) {
//                System.out.format("%1s %8s %13s %18s %23s", acc.getValue().getAccountId(), acc.getValue().getName(),
//                        acc.getValue().getBalance(), acc.getValue().getType(), acc.getValue().getBranch());
//                System.out.println();
//            }
//
//        }
//
//}
