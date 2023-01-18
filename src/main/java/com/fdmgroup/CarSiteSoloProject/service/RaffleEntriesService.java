package com.fdmgroup.CarSiteSoloProject.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.RaffleEntries;
import com.fdmgroup.CarSiteSoloProject.repository.RaffleEntriesRepository;

@Service
public class RaffleEntriesService {

	@Autowired
	private RaffleEntriesRepository repo;
	
	public void save(RaffleEntries entry) {
		repo.save(entry);
		
	}

	public List<RaffleEntries> findAllByDealerID(int dealerID) {
		
		return repo.findAllByDealerID(dealerID);
	}

	public RaffleEntries findWinner(List<RaffleEntries> allList) {
		Random rand = new Random();
	    RaffleEntries winner = allList.get(rand.nextInt(allList.size()));
		return winner;
	
	}

	
	
}
