/**
 * 
 */
package hu.hnk.beershop.exception;

/**
 * @author Nandi
 *
 */
public class DailyMoneyTransferLimitExceeded extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstuktor, mely l�trehoz egy DailyMoneyTransferLimitExceeded kiv�telt.
	 */
	public DailyMoneyTransferLimitExceeded() {
	}

	/**
	 * Konstuktor, mely l�trehoz egy DailyMoneyTransferLimitExceeded kiv�telt
	 * a megadott �zenettel.
	 * 
	 * @param message
	 *            a r�szletes �zenet.
	 */
	public DailyMoneyTransferLimitExceeded(String message) {
		super(message);
	}

	/**
	 * Konstuktor, mely l�trehoz egy DailyMoneyTransferLimitExceeded kiv�telt
	 * a megadott okoz�val.
	 * 
	 * @param cause
	 *            az okoz�
	 */
	public DailyMoneyTransferLimitExceeded(Throwable cause) {
		super(cause);
	}

	/**
	 * Konstuktor, mely l�trehoz egy DailyMoneyTransferLimitExceeded kiv�telt
	 * a megadott �zenettel �s okoz�val.
	 * 
	 * @param message
	 *            a r�szletes �zenet.
	 * @param cause
	 *            az okoz�.
	 */
	public DailyMoneyTransferLimitExceeded(String message, Throwable cause) {
		super(message, cause);
	}

}
