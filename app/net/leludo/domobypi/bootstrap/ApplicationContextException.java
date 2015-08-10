package net.leludo.domobypi.bootstrap;

/**
 * Exception related to application context problems
 *
 */
@SuppressWarnings("serial")
public class ApplicationContextException extends Exception {

	/**
	 * Constructor
	 * 
	 * @param message
	 *            The message of the exception
	 * @param e
	 *            The cause of the exception
	 */
	public ApplicationContextException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            The message of the exception
	 */
	public ApplicationContextException(String message) {
		super(message);
	}
}
