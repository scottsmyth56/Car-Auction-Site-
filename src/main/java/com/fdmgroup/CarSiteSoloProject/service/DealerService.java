package com.fdmgroup.CarSiteSoloProject.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.repository.DealerRepository;


@Service
public class DealerService {
	
	
	@Autowired
	private DealerRepository repo;

	public Dealer findDealerbyId(int id) {
		Optional<Dealer> t1 = repo.findById(id);
		return t1.orElse(new Dealer());
	}

	public Dealer findDealerByEmail(String email) {
		Optional<Dealer> t1 = repo.findDealerByEmail(email);
		return t1.orElse(new Dealer());
	}

	public void saveDealer(Dealer dealer) {
		repo.save(dealer);
	}

	public List<Dealer> findAllDealers() {
		return repo.findAll();
	}

	public Dealer findFeaturedDealer() {
		
		List<Dealer> list = findAllDealers();
		Random rand = new Random();
	    Dealer randomDealer = list.get(rand.nextInt(list.size()));
		return randomDealer;
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

}
