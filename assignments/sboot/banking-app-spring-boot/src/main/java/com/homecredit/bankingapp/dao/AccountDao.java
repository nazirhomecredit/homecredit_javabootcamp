package com.homecredit.bankingapp.dao;


import com.homecredit.bankingapp.model.Account;

import java.util.List;

public interface AccountDao {

	 boolean create(Account account);

	 boolean update(Account account);

	 boolean delete(int id);

	 Account get(int accountId);

	 List<Account> getAll();
}
