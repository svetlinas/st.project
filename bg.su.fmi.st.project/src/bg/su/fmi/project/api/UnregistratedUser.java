package bg.su.fmi.project.api;

import java.io.Serializable;

/**
 * Public Interface for the unregistrated users.
 * 
 * @author kkuncheva
 */
public interface UnregistratedUser extends Serializable {

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public String getConfirmPassword();
	
	public void setConfirmPassword(String confirmPassword);

	public String getEmail();

	public void setEmail(String email);

	public String getName();
	
	public void setName(String name);

}
