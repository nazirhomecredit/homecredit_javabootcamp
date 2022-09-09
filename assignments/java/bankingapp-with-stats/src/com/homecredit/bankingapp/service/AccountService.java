package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.AccountException;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountService {
       boolean create(Account account) throws AccountException;
       boolean update(int accountId , Account account) throws AccountException;
       boolean delete(int accountId) throws AccountException;
       Account get(int accountId) throws AccountException;
       boolean isValid(int accountId) throws AccountException;
       Account inputMethod(int no);
       Collection<Account> getAll() throws AccountException;
       long getNoOFAccountBalanceMoreThan1Lac();
       Map<String, Long> getNoOfAccountByAccountType();
       Map<String, Long>  getNoOfAccountByAccountTypeWithSorting();
       Map<String, Double> getAvgBalanceByAccountType();
       List<Integer> getListOfAccountIdsWithGivenName(String name);




}
