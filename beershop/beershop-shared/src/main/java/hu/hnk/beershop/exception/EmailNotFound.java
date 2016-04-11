/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class EmailNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy EmailNotFound kiv�telt.
	 */
	public EmailNotFound() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy EmailNotFound kiv�telt a megadott �zenettel.
	 * @param message a r�szletes �zenet.
	 */
	public EmailNotFound(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy EmailNotFound kiv�telt a megadott okoz�val.
	 * @param cause az okoz�
	 */
	public EmailNotFound(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy EmailNotFound kiv�telt a megadott �zenettel �s okoz�val.
	 * @param message a r�szletes �zenet.
	 * @param cause az okoz�.
	 */
	public EmailNotFound(String message, Throwable cause) {
		super(message, cause);
	}

}
