package bg.su.fmi.project.api;

import java.io.Serializable;

/**
 * Public Interface for the registrated users.
 * 
 * @author kkuncheva
 */
public interface RegistratedUser extends Serializable {

	/**
	 * Gets the username.
	 * @return the username
	 */
	public String getUsername();

	/**
	 * Sets the username.
	 * @param username the username
	 */
	public void setUsername(String username);

	/**
	 * Gets the password of the user.
	 * @return the password
	 */
	public String getPassword();

	/**
	 * Sets the password of the user.
	 * @param password the password.
	 */
	public void setPassword(String password);

}
