package com.fdmgroup.CarSiteSoloProject.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.User;
import com.fdmgroup.CarSiteSoloProject.repository.UserRepository;;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private UserRepository repo;

	@Autowired
	public DefaultUserDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new UserPrincipal(user);

	}

	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	public User save(User user) {
		return repo.save(user);
	}

}
