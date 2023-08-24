package com.example.restapidevelopment.exception;

public class UnAuthenticException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthenticException(String error){
        super(error);
    }
}
