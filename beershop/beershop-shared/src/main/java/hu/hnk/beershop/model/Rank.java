package hu.hnk.beershop.model;

/**
 * A rank enumer�ci� amely az egyes felhaszn�l�k rangjai lehetnek.
 * 
 * @author Nandi
 *
 */
public enum Rank {
	/**
	 * Amat�r.
	 */
	Amatuer(10),

	/**
	 * Kezd�.
	 */
	Beginner(20),

	/**
	 * Profi.
	 */
	Expert(30);

	Rank(final Integer value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private int value;

}
