package hu.hnk.beershop.service.interfaces;

import hu.hnk.beershop.model.User;

/**
 * A felhaszn�l� tev�kenys�geket korl�toz� interf�sz. Minden felhaszn�l�nak be
 * kell tartania bizonyos szab�lyokat, rangt�l f�gg�en v�s�rolhat napi szinten
 * kezdetben csak napi 3 v�s�rl�st bonyol�that le, a k�vetkez� szinten m�r napi
 * 5 darabot, ett�l nagyobb szinteken m�r napi 20-at. Az egyenlegfelt�lt�s is
 * hasonl� rendszer szerint m�k�dik. A legend�s s�r�ket csak az
 * <p>
 * Expert
 * </p>
 * rang� felhaszn�l�k rakhatj�k a kosarukba illetve v�s�rolhatj�k ut�na meg.
 * 
 * @author Nandi
 *
 */
public interface RestrictionCheckerService {

	/**
	 * Ellen�rzi hogy a param�terk�nt megadott felhaszn�l� utalhat-e m�g mag�nak
	 * p�nt a mai nap folyam�n.
	 * 
	 * @param user
	 *            az ellen�rizend� felhaszn�l�.
	 * @return igaz ha m�g utalhat, hamis ha m�r nem
	 */
	public boolean checkIfUserCanTransferMoney(User user);

	/**
	 * Ellen�rzi hogy a param�terk�nt megadott felhaszn�l� v�s�rolhat-e m�g
	 * mag�nak s�rt a nap folyam�n.
	 * 
	 * @param user
	 *            az ellen�rizend� felhaszn�l�.
	 * @return igaz ha m�g v�s�rolhat, hamis ha m�r nem
	 */
	public boolean checkIfUserCanBuyMoreBeer(User user);

	/**
	 * Ellen�rzi hogy a param�terk�nt megadott felhaszn�l� megv�s�rolhatja-e m�r
	 * a legend�sk�nt felt�ntetett s�rt.
	 * 
	 * @param user
	 *            az ellen�rizend� felhaszn�l�.
	 * @return igaz ha megv�s�rolhatja, hamis ha m�r nem
	 */
	public boolean checkIfUserCanBuyLegendBeer(User user);

	/**
	 * Ellen�rzi hogy a param�terk�nt megadott felhaszn�l�nak rendelkez�s�re �ll
	 * a megfelel� sz�m� egyenleg a fizet�shez.
	 * 
	 * @param user
	 *            az ellen�rizend� felhaszn�l�.
	 * @return igaz ha m�g utalhat, hamis ha m�r nem
	 */
	public boolean checkIfUserCanPayBeers(User user);

}
