package com.bank.ErrorHandler;

public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "Forbidden Exception";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
