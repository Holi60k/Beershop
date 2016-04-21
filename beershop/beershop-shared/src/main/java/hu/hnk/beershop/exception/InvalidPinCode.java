/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class InvalidPinCode extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy InvalidPinCode kiv�telt.
	 */
	public InvalidPinCode() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy InvalidPinCode kiv�telt a megadott �zenettel.
	 * @param message a r�szletes �zenet.
	 */
	public InvalidPinCode(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy InvalidPinCode kiv�telt a megadott okoz�val.
	 * @param cause az okoz�
	 */
	public InvalidPinCode(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy InvalidPinCode kiv�telt a megadott �zenettel �s okoz�val.
	 * @param message a r�szletes �zenet.
	 * @param cause az okoz�.
	 */
	public InvalidPinCode(String message, Throwable cause) {
		super(message, cause);
	}

}
