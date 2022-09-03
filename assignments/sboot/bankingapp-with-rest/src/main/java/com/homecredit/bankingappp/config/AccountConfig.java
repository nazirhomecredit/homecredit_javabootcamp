package com.homecredit.bankingappp.config;



import com.homecredit.bankingappp.dao.AccountDaoJdbcTempImpl;

import com.homecredit.bankingappp.dao.AccountDao;

import com.homecredit.bankingappp.service.AccountService;
import com.homecredit.bankingappp.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AccountConfig {

    @Bean("accountService")
    public AccountService accountService() {
        return new AccountServiceImpl();
    }

    @Bean("accDao")
    public AccountDao accDao() {
        return new AccountDaoJdbcTempImpl();
    }

//    @Bean("datasource")
//    public MysqlDataSource dataSource() {
//        MysqlDataSource	datasource = new MysqlDataSource();
//        datasource.setServerName("localhost");
//        datasource.setDatabaseName("jdbctraining");
//        datasource.setUser("training");
//        datasource.setPassword("training");
//        return datasource;
//    }
//
//    @Bean("connection")
//    public Connection connection() {
//        Connection conn = null;
//        try {
//            conn = dataSource().getConnection();
//            System.out.println("Connection created successfully. " + conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }

}
