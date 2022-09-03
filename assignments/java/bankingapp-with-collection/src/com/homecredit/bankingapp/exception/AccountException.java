package com.homecredit.bankingapp.exception;

public class AccountException extends  Exception {

    public AccountException() {
    }

    public AccountException(String errorMessge) {
        super(errorMessge);
    }

    public AccountException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
