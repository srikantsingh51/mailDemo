package com.sample.mail.demo.exception;

public class EmailSendException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailSendException(final String message) {
        super(message);
    }

}
