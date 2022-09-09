package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.ApplicationException;
import com.homecredit.bankingapp.model.Account;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface AccountService {

    boolean create(Account account)  throws ApplicationException;
    boolean update(Account account)  throws ApplicationException;
    boolean delete(int accountId)  throws ApplicationException;
    Account get(int accountId)   throws ApplicationException;
    List<Account> getAll()  throws ApplicationException;
    long getNoOFAccountBalanceMoreThan1Lac()  throws ApplicationException;
    Map<String, Long> getNoOfAccountByAccountType()  throws ApplicationException;
    Map<String, Long>  getNoOfAccountByAccountTypeWithSorting()  throws ApplicationException;
    Map<String, Double> getAvgBalanceByAccountType()  throws ApplicationException;
    List<Integer> getListOfAccountIdsWithGivenName(String name)  throws ApplicationException;
    void bulkImport()  throws ApplicationException;
    void bulkExport()  throws ApplicationException;
    boolean validate(Account acc, String msg, Predicate<Account> condition,
                     Function<String, Boolean> operation);

}
