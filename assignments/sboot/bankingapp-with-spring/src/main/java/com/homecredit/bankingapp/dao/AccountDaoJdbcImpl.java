package com.homecredit.bankingapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.homecredit.bankingapp.model.Account;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountDaoJdbcImpl implements AccountDao {

//	MysqlDataSource datasource = null;

	@Autowired
	Connection conn;

	Statement stmt = null;
	ResultSet rs = null;

	public AccountDaoJdbcImpl() {
//		datasource = new MysqlDataSource();
//		datasource.setServerName("localhost");
//		datasource.setDatabaseName("jdbctraining");
//		datasource.setUser("training");
//		datasource.setPassword("training");
//
//		try {
//			conn = datasource.getConnection();
//			System.out.println("Connection created successfully. " + conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public boolean create(Account account) {
		// INSERT account data
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query = "INSERT INTO account(name, type, balance, branch) values(\""
					+ account.getName() + "\",\"" + account.getType() + "\",\"" + account.getBalance() + "\",\"" + account.getBranch() + "\")";

			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean update(Account account) {
		// UPDATE account data
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query = "UPDATE account SET name = \"" + account.getName() + "\", type = \"" + account.getType()
					+ "\",balance = \"" + account.getBalance() + "\", branch = \"" + account.getBranch() + "\" WHERE id = " + account.getAccountId();
			System.out.println(query);
			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean delete(int id) {
		// DELETE account data
		boolean status = false;
		try {
			stmt = conn.createStatement();

			String query = "DELETE FROM account WHERE id = " + id;

			status = stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public Account get(int accountId) {
		// SELECT account data
		Account acc = null;
		String query = "SELECT * FROM account WHERE id = " + accountId;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				double balance = rs.getDouble("balance");
				String branch = rs.getString("branch");
				acc = new Account(id, name, type, balance, branch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}

	public List<Account> getAll() {
		// SELECT All accounts
		List<Account> accounts = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM account");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Double balance = rs.getDouble("balance");
				String branch = rs.getString("branch");
				accounts.add(new Account(id, name, type, balance, branch));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}
}