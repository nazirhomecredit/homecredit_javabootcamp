package com.homecredit.bankingapp.MyException;

public class AccontException extends Exception{

    public AccontException(String customException){
        super(customException);
    }

    public AccontException(String customException, Throwable throwableMessage){

        super(customException,throwableMessage);
    }

}
