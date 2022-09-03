package com.homecredit.bankingappp.dao;


import com.homecredit.bankingappp.exception.ApplicationException;
import com.homecredit.bankingappp.model.Account;

import java.util.List;

public interface AccountDao {

	 boolean create(Account account) throws ApplicationException;

	 boolean update(Account account) throws ApplicationException;

	 boolean delete(int id) throws ApplicationException;

	 Account get(int accountId) throws ApplicationException;

	 List<Account> getAll() throws ApplicationException;
}
