package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.exception.AccountValidationException;
import com.homecredit.bankingapp.exception.NoAccountFoundException;
import com.homecredit.bankingapp.model.Account;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountServiceImpl implements AccountService {

    Map<Integer , Account> accountMap = new HashMap<>();
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
    private ArrayList<Account> accounts = new ArrayList<>();

    Scanner s = new Scanner(System.in);
    Scanner s1 = new Scanner(System.in);


    public boolean create(Account account) throws AccountException {
        if(account.getName() == null || account.getName().isEmpty())
        {
            throw new AccountValidationException("Account Name can't be empty");
        }
        accounts.add(account);
        accountMap.put(account.getAccountId(),account);
        return true;
    }

    public boolean update(int accountId, Account account) throws AccountException{
        ListIterator<Account>li = accounts.listIterator();
        while (li.hasNext()) {
            Account m = li.next();
            if (m.getAccountId() == accountId) {
                li.set(account);
                accountMap.put(account.getAccountId(),account);
                return true;
            }
        }
        throw new NoAccountFoundException("No account found for given account id - " + accountId);

    }


    public boolean delete(int accountId) throws AccountException {
        if(accounts.size() == 0 ) {
            throw new AccountException("No Records Available");
        }
        for(Account acc : accounts) {
            if(acc.getAccountId() == accountId){
                accounts.remove(acc);
                accountMap.remove(accountId);
                return true;
            }
        }
        throw new NoAccountFoundException("No account found for given account id - " + accountId);
    }


    public Account get(int accountId) throws AccountException {
        for(Account acc : accounts){
            if(acc.getAccountId() == accountId){
                return acc;
            }
        }
        throw new NoAccountFoundException("No account found for given account id - " + accountId);
    }

    public Collection<Account> getAll() throws AccountException {
        if(accounts.size()==0){
            throw new AccountException("No account is present in data base");
        }

        return accounts;
    }

    public boolean isValid(int accountId) throws AccountException {
        for (Account acc : accounts) {
            if (acc != null && acc.getAccountId() == accountId) {
                return true;
            }
        }
        throw new NoAccountFoundException("No account found for given account id - " + accountId);
    }


    public long getNoOFAccountBalanceMoreThan1Lac(){
            long count = 0;
            for(Account acc : accounts) {
                if(acc.getBalance() > 100000){
                    count++;
                }
            }
            return count;
    }


    public Map<String, Long>  getNoOfAccountByAccountType() {
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


    public Map<String, Long>  getNoOfAccountByAccountTypeWithSorting(){
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


    public Map<String, Double> getAvgBalanceByAccountType(){
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


    public List<Integer> getListOfAccountIdsWithGivenName( String name){
        List<Integer> accId = new ArrayList<>();
        for(Account acc : accounts){
            if(acc.getName().equals(name)){
                accId.add(acc.getAccountId());
            }
        }
        return accId;
    }

    public synchronized void bulkImport() {
        System.out.format("%n%s - Import started %n", Thread.currentThread().getName());
        int counter = 0;
        try (Scanner in = new Scanner(new FileReader("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-threading\\src\\com\\homecredit\\bankingapp\\service\\account-input.txt"))) {
            System.out.println("Importing file...");
            while (in.hasNextLine()) {
                String acc = in.nextLine();
                System.out.println("Importing accounts - " + acc);
                Account account = new Account();
                StringTokenizer tokenizer = new StringTokenizer(acc, ",");

                // Acc ID
				account.setAccountId(Integer.parseInt(tokenizer.nextToken()));
                // Name
                account.setName(tokenizer.nextToken());
                // Type
                account.setType(tokenizer.nextToken());
                // Balance
                account.setBalance(Double.parseDouble(tokenizer.nextToken()));
                // Branch
                account.setBranch(tokenizer.nextToken());
//                accounts.add(account);
                accountMap.put(account.getAccountId(),account);
                this.create(account);
                counter++;
            }
            System.out.format("%s - %d Account Details are imported successfully.", Thread.currentThread().getName(),
                    counter);
        } catch (Exception e) {
            System.out.println("Error occurred while importing account data. " + e.getMessage());
        }
    }

    public void bulkExport() {
        System.out.format("%n%s - Export started %n", Thread.currentThread().getName());
        try (FileWriter out = new FileWriter("C:\\Training\\homecredit_javabootcamp\\assignments\\java\\bankingapp-with-threading\\src\\com\\homecredit\\bankingapp\\service\\account-output.txt")) {
            accountMap
                    .values().stream().map(acc -> acc.getAccountId() + "," + acc.getName() + "," + acc.getType() + ","
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
            System.out.format("%d Accounts are exported successfully.", accountMap.values().size());
        } catch (IOException e) {
            System.out.println("Error occurred while exporting account data. " + e.getMessage());
        }
    }

    public Account inputMethod(int no) {

        System.out.print("Enter Name : ");
        String na = s.nextLine();
        System.out.print("Enter Type : ");
        String type = s1.nextLine();
        System.out.print("Enter Balance : ");
        double bal = s.nextDouble();
        System.out.print("Enter Branch : ");
        String branch = s1.nextLine();
        Account account = new Account(no, na, type, bal, branch);
        return account;
    }
}
