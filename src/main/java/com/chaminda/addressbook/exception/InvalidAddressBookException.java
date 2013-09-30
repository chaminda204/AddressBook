/**
 * 
 */
package com.chaminda.addressbook.exception;

/**
 * @author chaminda
 *   <p/>
 *       date 17/09/2013
 */
public class InvalidAddressBookException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor with error message.
	 * 
	 * @param message
	 *            -Error description of the exception.
	 */
	public InvalidAddressBookException(final String message) {
		super(message);
	}

}
