package com.tiger.storeservice.exc;

public class StoreAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StoreAlreadyExistException(String msg) {
		super(msg);
	}

}
