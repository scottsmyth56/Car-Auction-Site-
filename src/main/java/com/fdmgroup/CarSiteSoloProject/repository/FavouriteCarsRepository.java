package com.fdmgroup.CarSiteSoloProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.CarSiteSoloProject.model.FavouritedCars;

@Repository
public interface FavouriteCarsRepository extends JpaRepository<FavouritedCars, Integer> {

	List<FavouritedCars> findByCustomerID(int customerID);

	FavouritedCars findByCustomerIDAndCarAdID(int customerID, int carID);

	
	
}
