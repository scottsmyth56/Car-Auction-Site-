package com.fdmgroup.CarSiteSoloProject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.CarSiteSoloProject.model.Role;
import com.fdmgroup.CarSiteSoloProject.model.User;

public class UserPrincipal implements UserDetails {

	private User user;

	public UserPrincipal(User user) {
		this.user = user;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<Role> roles = new ArrayList<>();
		roles.add(user.getRole());
		return roles;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
