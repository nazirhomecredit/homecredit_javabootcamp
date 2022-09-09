package com.homecredit.bankingapp.model;

public class Account {

private int accountId;
private String name;
private String branch;
private String type;
private double balance;



    public Account(int accountId, String name, String branch, String type, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.branch = branch;
        this.type = type;
        this.balance = balance;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
