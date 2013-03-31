package bg.su.fmi.project.shared;

//import javax.persistence.Entity;

import bg.su.fmi.project.api.RegistratedUser;

//@Entity
public class RegistratedUserBean implements RegistratedUser {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 4039618844319881297L;

	private String username = "";

	private String password = "";

	public RegistratedUserBean() {
	}

	public RegistratedUserBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

}
