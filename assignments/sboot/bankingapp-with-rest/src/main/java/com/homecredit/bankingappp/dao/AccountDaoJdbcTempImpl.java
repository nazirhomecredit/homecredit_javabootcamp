package com.homecredit.bankingappp.dao;

import com.homecredit.bankingappp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoJdbcTempImpl implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemp;


    @Override
    public boolean create(Account account) {
        String query = "INSERT INTO account(name, type, balance, branch) values(\""
                + account.getName() + "\",\"" + account.getType() + "\",\"" + account.getBalance() + "\",\"" + account.getBranch() + "\")";
        jdbcTemp.execute(query);
        return true;
    }

    @Override
    public boolean update(Account account) {
        String query = "UPDATE account SET name = \"" + account.getName() + "\", type = \"" + account.getType()
                + "\",balance = \"" + account.getBalance() + "\", branch = \"" + account.getBranch() + "\" WHERE id = " + account.getAccountId();
        jdbcTemp.execute(query);
        return true;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM account WHERE id = " + id;
        jdbcTemp.execute(query);
        return true;
    }

    @Override
    public Account get(int accountId) {
        String query = "SELECT * FROM account WHERE id = " + accountId;
        return jdbcTemp.query(query,new AccountRowMapper()).get(0);
    }

    @Override
    public List<Account> getAll() {
        String query = "SELECT * FROM account";
        List<Account> accounts = jdbcTemp.query(query, new AccountRowMapper());
        return accounts;
    }

    public class AccountResultSetExtractor implements ResultSetExtractor<Account> {

        @Override
        public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            double balance = rs.getDouble("balance ");
            String branch = rs.getString("branch");
            Account acc = new Account(id, name, type, balance, branch);
            return acc;
        }
    }

    public class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            double balance = rs.getDouble("balance");
            String branch = rs.getString("branch");
            Account acc = new Account(id, name, type, balance, branch);
            return acc;
        }
    }
}
