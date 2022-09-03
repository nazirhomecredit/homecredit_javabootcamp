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

public class AccountServiceImpl {

	AccountDao accountDao;

    Comparator<Account> ACCOUNT_NAME_ASC_SORT = new Comparator<Account>() {
        @Override
        public int compare(Account o1, Account o2) {
            return o1.getName().compareTo(o2.getName());

        }
    };

    private double calcAverage(List<Double> values) {
        double result = 0;
        for (Double value : values) {
            result += value;
        }
        return result / values.size();
    }

    public static HashMap<String, Long> sortByValue(Map<String, Long> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Long> > list =
                new LinkedList<Map.Entry<String, Long> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Long> >() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

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
        long count = 0;
        List<Account> accounts = accountDao.getAll();
        for(Account acc : accounts) {
            if(acc.getBalance() > 100000){
                count++;
            }
        }
        return count;
    }

    public Map<String , Long> getNoOfAccountByAccountType(){
        List<Account> accounts = accountDao.getAll();
        Map<String, Long> accByAccType = new HashMap<>();
        for(Account acc :accounts) {
            if(accByAccType.containsKey(acc.getType())){
                long count = accByAccType.get(acc.getType());
                accByAccType.put(acc.getType(),++count);
            }
            else {
                accByAccType.put(acc.getType(),1L);
            }

        }
        return accByAccType;
    }

    public Map<String , Long> getNoOfAccountByAccountTypeWithSorting() {
        List<Account> accounts = accountDao.getAll();
        Map<String, Long> accByAccTypeSort = new HashMap<>();
        for(Account acc :accounts) {
            if(accByAccTypeSort.containsKey(acc.getType())){
                long count = accByAccTypeSort.get(acc.getType());
                accByAccTypeSort.put(acc.getType(),++count);
            }
            else {
                accByAccTypeSort.put(acc.getType(),1L);
            }
        }
        Map<String, Long> hm1 = sortByValue(accByAccTypeSort);
        return hm1;
    }

    public Map<String , Double> getAvgBalanceByAccountType() {
        List<Account> accounts = accountDao.getAll();
        Map<String , List<Double>> firstPass = new HashMap<>();
        for(Account acc : accounts) {
            if(firstPass.containsKey(acc.getType())){
                firstPass.get(acc.getType()).add(acc.getBalance());
            }
            else {
                List<Double> balances = new ArrayList<>();
                balances.add(acc.getBalance());
                firstPass.put(acc.getType(),balances);
            }
        }
        Map<String , Double> res = new HashMap<>();
        for(Map.Entry<String, List<Double>> entry : firstPass.entrySet()){
            Double average = calcAverage(entry.getValue());
            res.put(entry.getKey(),average);

        }
        return res;

    }

    public List<Integer> getListOfAccountIdsWithGivenName(String name){
        List<Account> accounts = accountDao.getAll();
        List<Integer> accId = new ArrayList<>();
        for(Account acc : accounts){
            if(acc.getName().equals(name)){
                accId.add(acc.getAccountId());
            }
        }
        return accId;
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
