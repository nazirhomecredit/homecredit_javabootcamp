package com.homecredit.bankingapp.service;

import com.homecredit.bankingapp.exception.*;
import com.homecredit.bankingapp.model.Account;

import java.util.Collection;
import java.util.Set;

public interface AccountService {
    public boolean create(Account account) throws Exception ;
    public boolean update(int accountId, Set<Integer> set) throws Exception;
    public boolean delete(int accountId) throws Exception;
    public void get(int accountId) throws Exception;
    public void getAll() throws Exception;
}
