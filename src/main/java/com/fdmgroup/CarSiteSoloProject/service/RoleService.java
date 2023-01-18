package com.fdmgroup.CarSiteSoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.Role;
import com.fdmgroup.CarSiteSoloProject.repository.RoleRepository;
@Service
public class RoleService {
	
	   @Autowired
	    private RoleRepository repo;


	    public Role findByRoleName(String string) {
	        return repo.findByRoleName(string);
	    }
}
