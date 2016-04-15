package hu.hnk.loginservices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.hnk.beershop.model.Role;
import hu.hnk.beershop.model.User;
import hu.hnk.beershop.service.interfaces.UserService;

/**
 * @author Nandi
 *
 */
@Service("loginManager")
@EJB(name = "hu.hnk.beershop.UserService", beanInterface = UserService.class)
public class LoginService implements Serializable, UserDetailsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2856400278569714670L;
	
	/**
	 * A felhaszn�l� szolg�ltat�sok kezel�je.
	 */
	@EJB
	UserService userService;

	/**
	 * A bejelentkez� felhaszn�l� megadott felhaszn�l�neve.
	 */
	private String username;
	
	/**
	 * A bejelentkez� felhaszn�l� megadott jelszava.
	 */
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Felhszn�l� bet�lt�se felhaszn�l�n�v alapj�n.
	 * @param username a bet�ltend� felhaszn�l� felhaszn�l�neve.
	 * @return a bet�lt�tt felhaszn�l�.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;

		try {
			user = userService.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			 List<GrantedAuthority> authorities =
			 buildUserAuthority(user.getRoles());
//			Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
//			auth.add(new GrantedAuthority() {
//				
//				@Override
//				public String getAuthority() {
//					return "ROLE_USER";
//				}
//			});
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
					true, true, true, authorities);
		} catch (Exception e) {
			System.out.println(e);
			throw new UsernameNotFoundException(e.getMessage());
		}

	}
	
	/**
	 * A felhaszn�l� jogk�reit bet�lt� met�dus.
	 * @param userRoles a felhaszn�l� jogk�rei.
	 * @return a bet�lt�tt jogk�r�k.
	 */
	private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
