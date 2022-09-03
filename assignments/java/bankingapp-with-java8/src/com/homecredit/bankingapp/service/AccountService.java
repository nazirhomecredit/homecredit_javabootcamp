package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AccountService {
	boolean create(Account account);
	boolean update(int accountId , Account account);
	boolean delete(int accountId);
	Account get(int accountId) ;
	boolean isValid(int accountId);
	Account inputMethod(int no);
	Collection<Account> getAll();
	long getNoOFAccountBalanceMoreThan1Lac();
	Map<String, Long> getNoOfAccountByAccountType();
	Map<String, Long>  getNoOfAccountByAccountTypeWithSorting();
	Map<String, Double> getAvgBalanceByAccountType();
	List<Integer> getListOfAccountIdsWithGivenName(String name);
	void bulkImport();
	void bulkExport();



}
