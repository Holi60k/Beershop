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
	Amatuer(10L),

	/**
	 * Kezd�.
	 */
	Beginner(20L),

	/**
	 * Profi.
	 */
	Expert(30L);

	private Long value;

	Rank(Long value) {
		this.value = value;
	}

	public static Rank getValue(Long value) {
		for (Rank item : Rank.values()) {
			if(item.value == value) {
				return item;
			}
		}
		return null;
	}

}
