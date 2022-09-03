package com.homecredit.bankingapp.dao;

import java.util.List;

import com.homecredit.bankingapp.model.Account;

public interface AccountDao {

	 boolean create(Account account);

	 boolean update(Account account);

	 boolean delete(int id);

	 Account get(int accountId);

	 List<Account> getAll();
}
