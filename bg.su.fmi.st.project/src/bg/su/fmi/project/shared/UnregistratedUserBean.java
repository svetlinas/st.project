package bg.su.fmi.project.shared;

//import javax.persistence.Entity;

import bg.su.fmi.project.api.UnregistratedUser;

//@Entity
public class UnregistratedUserBean implements UnregistratedUser {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -1396586627225261370L;
	
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	private String email;
	
	private String name;
	
	
	public UnregistratedUserBean() {
	}


	public UnregistratedUserBean(String username, String password,
			String confirmPassword, String email, String name) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
