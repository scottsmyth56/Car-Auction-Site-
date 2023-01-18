package com.fdmgroup.CarSiteSoloProject.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.repository.CarAdRepository;


@Service
public class CarAdService {

	@Autowired
	CarAdRepository repo;

	public void createCar(CarAd carad) {
		repo.save(carad);
	}

	public List<CarAd> findAllCars() {
		return repo.findAll();
	}

	public CarAd findCarById(int id) {
		Optional<CarAd> car = repo.findById(id);
		return car.orElse(new CarAd());
	}

	public byte[] encodeImageToBase64(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String fileExtension = originalFilename.substring(originalFilename.indexOf(".") + 1);

		if (!"png".equals(fileExtension) && !"jpeg".equals(fileExtension) && !"jpg".equals(fileExtension)) {
			throw new RuntimeException("Only jpg/jpeg and png files are accepted");
		}

		byte[] bytes;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return Base64.getEncoder().encode(bytes);
	}

	public void saveCar(CarAd car) {
		repo.save(car);

	}

	public void deleteCar(int id) {
		repo.deleteById(id);

	}
	
	public List<CarAd> findAllActiveCarAds(){
		return repo.findAll().stream().filter(CarAd::isActive).collect(Collectors.toList());
	}

	public List<CarAd> findByDealerID(int dealerID) {
		
		List <CarAd> list = repo.findAllByDealerID(dealerID);	
		return list;
	}
	public List<CarAd> filterByMake(String input) {
		return repo.findByMakeContainsIgnoreCase(input);
	}
	public List<CarAd> filterByModel(String input) {
		return repo.findByModelContainsIgnoreCase(input);
	}
	public List<CarAd> filterByColour(String input) {
		return repo.findByColourContainsIgnoreCase(input);
	}
	public List<CarAd> filterCars(String input) {
		
		List<CarAd> filteredCars = filterByMake(input);
		filteredCars.addAll(filterByModel(input));
		filteredCars.addAll(filterByColour(input));
		filteredCars = filteredCars.stream().distinct().collect(Collectors.toList());
		return filteredCars;
		

	}

}
