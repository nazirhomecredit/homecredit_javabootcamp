package com.homecredit.bankingapp.exception;

public class ApplicationException extends Exception {
    public ApplicationException()
    {
        super();
    }
    public ApplicationException(String msj)
    {
        super(msj);
    }

    public ApplicationException(String msj,Throwable cause)
    {
        super(msj,cause);
    }


}
