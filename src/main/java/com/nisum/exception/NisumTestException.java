/**
 * 
 */
package com.nisum.exception;

/**
 * @author yadickson
 *
 */
public class NisumTestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public NisumTestException() {
		
	}

	/**
	 * @param message
	 */
	public NisumTestException(String message) {
		super(message);		
	}

	/**
	 * @param cause
	 */
	public NisumTestException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NisumTestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NisumTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
