package com.homecredit.bankingapp;


import com.homecredit.bankingapp.config.AccountConfig;
import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingapp.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BankingAppMain {
	private static Scanner in;
	private static AccountService accountService;
	private static ApplicationContext ctx;

	public static void main(String[] args) {

		in = new Scanner(System.in);
		ctx = new AnnotationConfigApplicationContext(AccountConfig.class);

		accountService = ctx.getBean("accountService",AccountService.class);

		ExecutorService executor = Executors.newCachedThreadPool();

		System.out.print("Welcome to Banking Management App!");

		while (true) {

			System.out.println("\n");
			System.out.println("1. Add Account");
			System.out.println("2. View Account");
			System.out.println("3. Update Account");
			System.out.println("4. Delete Account");
			System.out.println("5. View All Account");
			System.out.println("6. Print Statistics");
			System.out.println("7. Import");
			System.out.println("8. Export");
			System.out.println("9. Exit");

			System.out.print("Enter the option: ");
			int option = 0;
			// Get option from user
			try {
				option = Integer.parseInt(in.next());
			} catch (NumberFormatException e) {
				System.out.println("Invalid option. Please enter valid option.");
				continue;
			}
			int accId;
			try {
				switch (option) {
					case 1:
						addAccount();
						System.out.println("Account has been added successfully!");
						break;
					case 2:
						System.out.print("Please enter account id: ");
						accId = in.nextInt();
						Account acc = accountService.get(accId);
						printHeader();
						printDetail(acc);
						break;
					case 3:
						System.out.print("Please enter account id: ");
						accId = in.nextInt();
						Account accForUpdate = accountService.get(accId);
						captureAccDetail(accForUpdate);
						accountService.update(accForUpdate);
						System.out.println("Account has been updated successfully!");
						break;
					case 4:
						System.out.print("Please enter account id: ");
						accId = in.nextInt();
						accountService.delete(accId);
						System.out.println("Account has been deleted successfully!");
						break;
					case 5:
						List<Account> accounts = accountService.getAll();
						printHeader();
						for (Account account : accounts) {
							printDetail(account);
						}
						break;
					case 6:
						printStatistics();
						break;
					case 7:
						Callable<Boolean> importThread = new Callable<Boolean>() {
							@Override
							public Boolean call() throws Exception {
								System.out.println(Thread.currentThread() + " Waiting for 5s to start import process.");
								Thread.sleep(5000);
								accountService.bulkImport();
								return true;
							}
						};

						Future<Boolean> importFuture = executor.submit(importThread);
						System.out.println(Thread.currentThread().getName() + " Import process triggered");

						break;
					case 8:
						Callable<Boolean> exportThread = new Callable<Boolean>() {
							@Override
							public Boolean call() throws Exception {
								System.out.println(Thread.currentThread() + " Waiting for 5s to start export process.");
								Thread.sleep(5000);
								accountService.bulkExport();
								return true;
							}
						};

						Future<Boolean> exportFuture = executor.submit(exportThread);
						System.out.println(Thread.currentThread().getName() + " Export process triggered");
						break;
					case 9:
						System.out.println("Thank you!!!");
						executor.shutdown();
						in.close();
						System.exit(0);
						break;

					default:
						break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter valid input.");
			}
		}

	}

	private static void printStatistics() {

		System.out.println("No of accounts which has balance more than 1 lac - " + accountService.getNoOFAccountBalanceMoreThan1Lac());
		System.out.println("Show no of account by account type - " + accountService.getNoOfAccountByAccountType() );
		System.out.println("Show no of account by account type with Sorting - " + accountService.getNoOfAccountByAccountTypeWithSorting() );
		System.out.println("Show avg balance by account type - " + accountService.getAvgBalanceByAccountType());
		System.out.println("List account ids whose account name contains given name - " + accountService.getListOfAccountIdsWithGivenName("Sanskar"));

	}

	private static void printHeader() {
		System.out.format("\n%5s %15s %5s %15s %15s", "AccID", "Name", "Type", "Balance", "Branch");
	}

	private static void printDetail(Account account) {
		if (account == null) {
			return;
		}

		System.out.format("\n%5d %15s %5s %15f %15s", account.getAccountId(), account.getName(), account.getType(),
				account.getBalance(), account.getBranch());
	}

	private static void addAccount() throws NumberFormatException {
		Account account = new Account();

		captureAccDetail(account);

		accountService.create(account);
	}

	private static void captureAccDetail(Account account) throws NumberFormatException {
		System.out.print("Enter Name: ");
		account.setName(in.next());

		System.out.print("Enter Account Type: ");
		account.setType(in.next());

		System.out.print("Enter Account Balance: ");
		account.setBalance(in.nextDouble());

		System.out.print("Enter Branch: ");
		account.setBranch(in.next());
	}

}
