package com.homecredit.bankingapp.model;

public class Account {

    int id;
    String name;
    double balance;
    String type;
    String branch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Account(int id, String name, double balance, String type, String branch) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.branch = branch;
    }
}
