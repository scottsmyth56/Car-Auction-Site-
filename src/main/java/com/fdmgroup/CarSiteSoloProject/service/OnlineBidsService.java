package com.fdmgroup.CarSiteSoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import com.fdmgroup.CarSiteSoloProject.model.OnlineBids;
import com.fdmgroup.CarSiteSoloProject.repository.OnlineBidsRepository;
import java.util.stream.*;

@Service
public class OnlineBidsService {

	@Autowired
	private OnlineBidsRepository repo;

	public List<OnlineBids> findAllBidsByCarAdID(int id) {
		List<OnlineBids> list = repo.findAllBidsByCarAdID(id);
		return list;
	}

	public OnlineBids findWinningBidByCarID(int id) {
		List<OnlineBids> list = findAllBidsByCarAdID(id);
		if (list.isEmpty()) {
			OnlineBids bids = new OnlineBids();
			return bids;
		} else {
			OnlineBids winningBid = list.stream().max(Comparator.comparing(OnlineBids::getBidPrice)).get();
			return winningBid;
		}
	}

	public void saveBid(OnlineBids bid) {
		repo.save(bid);

	}

	public OnlineBids findBidByID(int bidid) {
		return repo.findById(bidid).orElse(new OnlineBids());
	}

	public void removeBid(OnlineBids bid) {
		repo.delete(bid);

	}

}
