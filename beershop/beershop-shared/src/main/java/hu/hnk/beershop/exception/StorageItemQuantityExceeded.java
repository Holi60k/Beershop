/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class StorageItemQuantityExceeded extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy StorageQuantityExceeded kiv�telt.
	 */
	public StorageItemQuantityExceeded() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy StorageQuantityExceeded kiv�telt a megadott �zenettel.
	 * @param message a r�szletes �zenet.
	 */
	public StorageItemQuantityExceeded(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy StorageQuantityExceeded kiv�telt a megadott okoz�val.
	 * @param cause az okoz�
	 */
	public StorageItemQuantityExceeded(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy StorageQuantityExceeded kiv�telt a megadott �zenettel �s okoz�val.
	 * @param message a r�szletes �zenet.
	 * @param cause az okoz�.
	 */
	public StorageItemQuantityExceeded(String message, Throwable cause) {
		super(message, cause);
	}

}
