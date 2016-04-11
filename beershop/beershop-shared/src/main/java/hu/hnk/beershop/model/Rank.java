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
	/**
	 * A rank �rt�ke.
	 */
	private Long value;
	/**
	 * Konstuktor, mely l�trehoz egy Rang objektumot a megadott �rt�kkel.
	 * @param value a rank �rt�ke
	 */
	Rank(Long value) {
		this.value = value;
	}
	/**
	 * Megadja egy v�lasztott �rt�k rangj�t.
	 * @param value a megadott �rt�k
	 * @return a hozz� tartoz� Rank
	 */
	public static Rank getValue(Long value) {
		for (Rank item : Rank.values()) {
			if(item.value == value) {
				return item;
			}
		}
		return null;
	}

}
