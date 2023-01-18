package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.FavouritedCars;
import com.fdmgroup.CarSiteSoloProject.repository.FavouriteCarsRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FavouritedCarsServiceTest {

	@InjectMocks
	private FavouritedCarsService favCarServiceMock;

	@MockBean
	CarAdService caradservicemock;

	@Mock
	private FavouritedCars favCar;

	@Mock
	private CarAd carAd;

	@MockBean
	private FavouriteCarsRepository mockRepo;

	private FavouritedCars car;
	private List <FavouritedCars> favcarList;
	private CarAd carad2;

	@BeforeEach
	void init() {
		car = new FavouritedCars();
		carad2 = new CarAd();
		favcarList = new ArrayList<>();
	}

	@Test
	void test_savefavCar() {
		favCarServiceMock.save(favCar);
		verify(mockRepo, times(1)).save(favCar);
	}

	@Test
	void test_findfavcarbyCustomerID() {
		when(caradservicemock.findCarById(1)).thenReturn(carad2);
		favCarServiceMock.findFavouritedCarsByCustomerID(1);
		verify(mockRepo, times(1)).findAll();
	}

	@Test
	void test_unfavouriteCar() {
		when(mockRepo.findByCustomerID(1)).thenReturn(favcarList);
		favCarServiceMock.UnfavouriteCar(1,2);
		verify(mockRepo, times(1)).delete(favCar);
	}
}
