package hu.hnk.interfaces;

import hu.hnk.beershop.model.Cargo;

/**
 * A sz�ll�t�sokat kezel� adathozz�f�r�si oszt�ly interf�sze.
 * 
 * @author Nandi
 *
 */
public interface CargoDao {

	/**
	 * @param cargo
	 * @return
	 */
	public Cargo save(Cargo cargo);
	
}
