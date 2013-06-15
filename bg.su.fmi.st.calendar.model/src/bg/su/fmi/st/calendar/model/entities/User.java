package bg.su.fmi.st.calendar.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "USER")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Group {
		ADMINISTRATOR, USER;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@XmlElement(name = "username")
	private String username;

	@XmlElement(name = "password")
	private String password;

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "name")
	private String name;

	@ElementCollection(targetClass = Group.class)
	@CollectionTable(name = "USERGROUP", joinColumns = @JoinColumn(name = "username", nullable = false, referencedColumnName = "username"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"username", "groupname" }) })
	@Enumerated(EnumType.STRING)
	@Column(name = "groupname", length = 64, nullable = false)
	@XmlTransient
	private List<Group> groups;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000)
	protected byte[] picture;

	public User() {
	}

	public User(String username, String password, String email, String name,
			byte[] picture) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.picture = picture;
	}

	@Override
	public String toString() {
		return String.format("User id: %s; Username: %s; Name: %s; e-mail: %s",
				this.id, this.username, this.name, this.email);
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

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public long getId() {
		return id;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
