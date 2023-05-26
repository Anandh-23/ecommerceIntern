package com.intern.ecommerce.exception;

public class CartNotUpdatedException extends Exception{
    public CartNotUpdatedException() {
        super();
    }

    public CartNotUpdatedException(String message) {
        super(message);
    }

    public CartNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotUpdatedException(Throwable cause) {
        super(cause);
    }

    protected CartNotUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
