package com.fdmgroup.CarSiteSoloProject.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.CarSiteSoloProject.model.OnlineBids;

public interface OnlineBidsRepository extends JpaRepository<OnlineBids, Integer> {

	List <OnlineBids> findAllBidsByCarAdID(int id);
}
