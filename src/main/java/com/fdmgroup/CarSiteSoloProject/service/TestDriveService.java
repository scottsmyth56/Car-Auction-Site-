package com.fdmgroup.CarSiteSoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.TestDrive;
import com.fdmgroup.CarSiteSoloProject.repository.TestDriveRepository;

@Service
public class TestDriveService {
	
	@Autowired
	TestDriveRepository testDriveRepo;

	public void save(TestDrive booking) { 
		testDriveRepo.save(booking);
	}


	
}
