package com.homecredit.bankingapp.exception;

public class AccountValidationException extends  AccountException {

    public AccountValidationException() {

    }

    public AccountValidationException(String errorMessge) {
        super(errorMessge);
    }

    public AccountValidationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
