package com.sample.mail.demo.exception;

public class NoMailFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoMailFoundException(final String message) {
        super(message);
    }

}
