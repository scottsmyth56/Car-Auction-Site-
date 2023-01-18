package com.fdmgroup.CarSiteSoloProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;

public interface CarAdRepository extends JpaRepository<CarAd, Integer> {

	List<CarAd> findAllByDealerID(int dealerID);

	List<CarAd> findByMakeContainsIgnoreCase(String input);

	List<CarAd> findByModelContainsIgnoreCase(String input);

	List<CarAd> findByColourContainsIgnoreCase(String input);


}
