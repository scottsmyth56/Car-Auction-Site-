package com.fdmgroup.CarSiteSoloProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.FavouritedCars;
import com.fdmgroup.CarSiteSoloProject.repository.FavouriteCarsRepository;

@Service
public class FavouritedCarsService {

	@Autowired
	private FavouriteCarsRepository repo;

	@Autowired
	private CarAdService caradService;
	
	public void save(FavouritedCars favcar) {
		repo.save(favcar);

	}

	public List<CarAd> findFavouritedCarsByCustomerID(int customerID) {
		
		List <FavouritedCars> list = repo.findAll();
		List <CarAd> carList = new ArrayList<>();
		
		for(FavouritedCars car : list ) {
			if(car.getCustomerID() == customerID) {
				CarAd carAd = caradService.findCarById(car.getCarAdID());
				carList.add(carAd);
			}
		}
		
		return carList;
		
	}

	public void UnfavouriteCar(int customerID, int carID) {
		
		List <FavouritedCars> list = repo.findByCustomerID(customerID);
		
		for(FavouritedCars item : list) {
			if(item.getCarAdID()==carID) {
				repo.delete(item);
			}
		}
		
	}

}
