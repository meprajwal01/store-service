package com.tiger.storeservice.exc;

public class StoreNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoreNotFoundException(String msg) {
		super(msg);
	}

}
