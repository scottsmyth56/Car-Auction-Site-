package com.fdmgroup.CarSiteSoloProject.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name ="USERS_LIST")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String password;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Role role;
	
	    public User(String username, String password, Role role) {
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }
	    
	    public User() {}


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

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

	
		@Override
		public int hashCode() {
			return Objects.hash(password, role, username);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return Objects.equals(password, other.password) && Objects.equals(role, other.role)
					&& Objects.equals(username, other.username);
		}



	    
	 
	    	
	   

}
