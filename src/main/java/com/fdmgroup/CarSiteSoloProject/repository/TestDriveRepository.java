package com.fdmgroup.CarSiteSoloProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.CarSiteSoloProject.model.TestDrive;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Integer> {

}
