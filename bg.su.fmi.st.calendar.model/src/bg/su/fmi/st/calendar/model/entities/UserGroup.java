//package bg.su.fmi.st.calendar.model.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "USERGROUP")
//public class UserGroup {
//
//	public enum Group {
//		ADMINISTRATOR, USER;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//
//	@Column(name = "USERNAME")
//	private String username;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "GROUPNAME")
//	private Group groupname;
//
//	public UserGroup() {
//	}
//
//	public UserGroup(String username, Group group) {
//		this.username = username;
//		this.groupname = group;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public Group getGroupname() {
//		return groupname;
//	}
//
//	public void setGroupname(Group groupname) {
//		this.groupname = groupname;
//	}
//
//}
