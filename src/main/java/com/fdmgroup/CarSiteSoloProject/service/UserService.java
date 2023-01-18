package com.fdmgroup.CarSiteSoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.User;
import com.fdmgroup.CarSiteSoloProject.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;

	
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username).orElse(new User());
	    }

	    public void saveUser(User user) {
	        userRepository.save(user);
	    }
}
