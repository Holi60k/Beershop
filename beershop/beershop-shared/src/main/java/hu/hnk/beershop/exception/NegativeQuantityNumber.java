/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class NegativeQuantityNumber extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy NegativeQuantityNumber kiv�telt.
	 */
	public NegativeQuantityNumber() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy NegativeQuantityNumber kiv�telt a megadott �zenettel.
	 * @param message a r�szletes �zenet.
	 */
	public NegativeQuantityNumber(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy NegativeQuantityNumber kiv�telt a megadott okoz�val.
	 * @param cause az okoz�
	 */
	public NegativeQuantityNumber(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy NegativeQuantityNumber kiv�telt a megadott �zenettel �s okoz�val.
	 * @param message a r�szletes �zenet.
	 * @param cause az okoz�.
	 */
	public NegativeQuantityNumber(String message, Throwable cause) {
		super(message, cause);
	}

}
