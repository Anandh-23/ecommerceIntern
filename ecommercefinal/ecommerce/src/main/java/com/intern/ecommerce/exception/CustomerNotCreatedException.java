package com.intern.ecommerce.exception;

public class CustomerNotCreatedException extends Exception{
    public CustomerNotCreatedException() {
        super();
    }

    public CustomerNotCreatedException(String message) {
        super(message);
    }

    public CustomerNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected CustomerNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
