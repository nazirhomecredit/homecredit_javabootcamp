package com.homecredit.bankingapp.config;



import com.homecredit.bankingapp.dao.AccountDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.homecredit.bankingapp.dao.AccountDaoJdbcImpl;
import com.homecredit.bankingapp.service.AccountService;
import com.homecredit.bankingapp.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AccountConfig {

    @Bean("accountService")
    public AccountService accountService() {
        return new AccountServiceImpl();
    }

    @Bean("accDao")
    public AccountDao accDao() {
        return new AccountDaoJdbcImpl();
    }

    @Bean("datasource")
    public MysqlDataSource dataSource() {
        MysqlDataSource	datasource = new MysqlDataSource();
        datasource.setServerName(dbConfig().getHostname());
        datasource.setDatabaseName(dbConfig().getDbname());
        datasource.setUser(dbConfig().getUsername());
        datasource.setPassword(dbConfig().getPassword());
        return datasource;
    }

    @Bean("connection")
    public Connection connection() {
        Connection conn = null;
        try {
            conn = dataSource().getConnection();
            System.out.println("Connection created successfully. " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Bean
    public DbConfig dbConfig() {
        return new DbConfig();
    }
}
