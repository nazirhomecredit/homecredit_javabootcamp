package com.homecredit.bankingapp.exception;

public class NoAccountFoundException extends  AccountException {

    public NoAccountFoundException() {

    }

    public NoAccountFoundException(String errorMessge) {
        super(errorMessge);
    }

    public NoAccountFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
