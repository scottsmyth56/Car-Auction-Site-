package com.fdmgroup.CarSiteSoloProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.fdmgroup.CarSiteSoloProject.model.Dealer;
@Repository
public interface DealerRepository extends JpaRepository<Dealer, Integer> {
	Optional<Dealer> findDealerByEmail(String email);
}
