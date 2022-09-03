package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.dao.AccountDao;
import com.homecredit.bankingapp.dao.AccountDaoJdbcImpl;
import com.homecredit.bankingapp.model.Account;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountServiceImpl {

	AccountDao accountDao;

    Comparator<Account> ACCOUNT_NAME_ASC_SORT = new Comparator<Account>() {
        @Override
        public int compare(Account o1, Account o2) {
            return o1.getName().compareTo(o2.getName());

        }
    };

    public AccountServiceImpl(){
        accountDao = new AccountDaoJdbcImpl();
    }

    public boolean create(Account account) {
        return accountDao.create(account);
    }

    public Account get(int id) {
        return accountDao.get(id);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public boolean update(Account account) {
        return accountDao.update(account);
    }

    public boolean delete(int id) {
        return accountDao.delete(id);
    }

    public boolean validate(Account acc, String msg, Predicate<Account> condition,
                            Function<String, Boolean> operation) {
        if (!condition.test(acc)) {
            return operation.apply(msg);
        }
        return true;
    }

    public long getNoOFAccountBalanceMoreThan1Lac() {
        long count = accountDao.getAll().stream().filter(acc -> acc.getBalance() > 100000).count();
        return count;
    }

    public Map<String , Long> getNoOfAccountByAccountType(){
        return accountDao.getAll().stream().map(Account::getType).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String , Long> getNoOfAccountByAccountTypeWithSorting() {
        return accountDao.getAll()
                .stream()
                .sorted(Comparator.comparing(Account::getType))
                .collect(Collectors.groupingBy(Account::getType, LinkedHashMap::new, Collectors.counting()));
    }

    public Map<String , Double> getAvgBalanceByAccountType() {
        return accountDao.getAll()
                .stream()
                .sorted(Comparator.comparing(Account::getType)).collect(Collectors.groupingBy(Account::getType, LinkedHashMap::new, Collectors.averagingDouble(Account::getBalance)));
    }

    public List<Integer> getListOfAccountIdsWithGivenName(String name){
        List<Integer> accIds = accountDao.getAll()
                .stream()
                .filter(acc -> acc.getName().equals(name))
                .map(acc -> acc.getAccountId())
                .collect(Collectors.toList());
        return accIds;
    }

	public synchronized void bulkImport() {
		int counter = 0;
		try (Scanner in = new Scanner(new FileReader(".\\input\\account-input.txt"))) {
			while (in.hasNextLine()) {
				String acc = in.nextLine();
				Account account = new Account();
				StringTokenizer tokenizer = new StringTokenizer(acc, ",");

				// Acc ID Auto Generated
//				account.setAccountId(Integer.parseInt(tokenizer.nextToken()));
				// Name
				account.setName(tokenizer.nextToken());
				// Type
				account.setType(tokenizer.nextToken());
				// Balance
				account.setBalance(Double.parseDouble(tokenizer.nextToken()));
				// Branch
				account.setBranch(tokenizer.nextToken());

				accountDao.create(account);
				counter++;
			}
			System.out.format("%d Account Details are imported successfully.",counter);
		} catch (IOException e) {
			System.out.println("Error occurred while importing account data. " + e.getMessage());
		}
	}

	public void bulkExport() {
		try (FileWriter out = new FileWriter(".\\output\\account-output.txt")) {
			accountDao
					.getAll().stream().map(acc -> acc.getAccountId() + "," + acc.getName() + "," + acc.getType() + ","
					+ acc.getBalance() + "," + acc.getBranch()  + "\n")
					.forEach(rec -> {
						try {
							out.write(rec);
						} catch (IOException e) {
							System.out
									.println("Error occurred while writing account data into file. " + e.getMessage());
							e.printStackTrace();
						}
					});
			System.out.format("%d Accounts are exported successfully.", accountDao.getAll().size());
		} catch (IOException e) {
			System.out.println("Error occurred while exporting account data. " + e.getMessage());
		}
	}
}
