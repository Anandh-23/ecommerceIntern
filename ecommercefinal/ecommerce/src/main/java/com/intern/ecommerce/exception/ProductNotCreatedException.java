package com.intern.ecommerce.exception;

public class ProductNotCreatedException extends Exception{
    public ProductNotCreatedException() {
        super();
    }

    public ProductNotCreatedException(String message) {
        super(message);
    }

    public ProductNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected ProductNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
