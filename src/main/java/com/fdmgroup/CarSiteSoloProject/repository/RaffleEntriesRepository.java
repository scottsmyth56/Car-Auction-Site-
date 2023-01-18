package com.fdmgroup.CarSiteSoloProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.CarSiteSoloProject.model.RaffleEntries;

public interface RaffleEntriesRepository extends JpaRepository<RaffleEntries, Integer> {

	List<RaffleEntries> findAllByDealerID(int dealerID);

}
