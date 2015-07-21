package net.leludo.domobypi.bootstrap;

/**
 * Exception dédiée à la gestion des propriétés applicative
 *
 */
@SuppressWarnings("serial")
public class ApplicationContextException extends Exception {

	public ApplicationContextException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * Conctructeur
	 * @param message message de l'exception
	 */
	public ApplicationContextException(String message) {
		super(message) ;
	}
}
