package com.saurav.autosuggest.exceptions;


public class IllegalInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalInputException(String message) {
        super(message);
    }
}