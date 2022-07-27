package com.company;

public class Account {
    // properties
    int id;
    String name;
    double balance;
    String type;
    boolean active;
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public double getBalance() {
        return balance;
    }


    public String getType() {
        return type;
    }


    public boolean getActive() {
        return active;
    }


    // default
    public Account() {
    }

    // overloaded constructor
    public Account( String n, double bal, String type, boolean active) {
        this.name = n;
        this.balance = bal;
        this.type = type;
        this.active = active;
    }

    public Account( int id , String n, double bal, String type, boolean active) {
        this.id = id;
        this.name = n;
        this.balance = bal;
        this.type = type;
        this.active = active;
    }

    // behaviours
    public void openAccount() {

        this.active = true;
        this.balance = 0;
    }

    public void closeAccount() {
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public double showBalance() {
        if (isActive()) {
            return this.balance;
        }
        return 0.0;
    }

    public void withdraw(double amount) {
        if (isActive() && amount < this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal Success");
        } else {
            System.out.println("Account Inactive, Cant withdraw");
        }
    }

    public void deposit(double amount) {
        if (isActive()) {
            this.balance += amount;
        } else {
            System.out.println("Account Inactive ,Cant deposite");
        }
    }

}