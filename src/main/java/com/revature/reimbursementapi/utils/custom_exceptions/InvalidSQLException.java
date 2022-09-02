package com.revature.reimbursementapi.utils.custom_exceptions;

public class InvalidSQLException extends RuntimeException{

    public InvalidSQLException() {
    }

    public InvalidSQLException(String message) {
        super(message);
    }

    public InvalidSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSQLException(Throwable cause) {
        super(cause);
    }

    public InvalidSQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
