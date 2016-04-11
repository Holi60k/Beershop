/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class UsernameNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy UsernameNotFound kiv�telt null �rt�kkel.
	 */
	public UsernameNotFound() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy UsernameNotFound kiv�telt a megadott �zenettel.
	 * @param message a r�szletes �zenet.
	 */
	public UsernameNotFound(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy UsernameNotFound kiv�telt a megadott okoz�val.
	 * @param cause az okoz�.
	 */
	public UsernameNotFound(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy UsernameNotFound kiv�telt a megadott �zenettel �s okoz�val.
	 * @param message a r�szletes �zenet.
	 * @param cause az okoz�.
	 */
	public UsernameNotFound(String message, Throwable cause) {
		super(message, cause);
	}

}
