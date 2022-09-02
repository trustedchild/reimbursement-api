package com.revature.reimbursementapi.utils.custom_exceptions;

public class InvalidUserRoleException extends RuntimeException{
    public InvalidUserRoleException() {
    }

    public InvalidUserRoleException(String message) {
        super(message);
    }

    public InvalidUserRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserRoleException(Throwable cause) {
        super(cause);
    }

    public InvalidUserRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
