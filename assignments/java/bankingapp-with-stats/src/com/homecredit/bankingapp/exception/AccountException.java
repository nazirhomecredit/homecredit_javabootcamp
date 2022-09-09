package com.homecredit.bankingapp.exception;

public class AccountException extends  Exception {

    public AccountException(String customException){
        super(customException);
    }

    public AccountException(String customException, Throwable throwableMessage){

        super(customException,throwableMessage);
    }
}
