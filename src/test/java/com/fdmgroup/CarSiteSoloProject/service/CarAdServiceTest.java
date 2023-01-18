package com.fdmgroup.CarSiteSoloProject.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
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
import com.fdmgroup.CarSiteSoloProject.repository.CarAdRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CarAdServiceTest {

	@InjectMocks
	private CarAdService mockcaradservice;

	@Mock
	private CarAd mockCar;

	@MockBean
	private CarAdRepository mockCarRepo;

	private MockMultipartFile mockBadFile;
	private MockMultipartFile mockGoodFile;
	private CarAd carad;

	@BeforeEach
	void init() {
		mockBadFile = new MockMultipartFile("channelLogo.pdf", "channelLogo.pdf", "byte", "ChannelLogo".getBytes());
		mockGoodFile = new MockMultipartFile("channelLogo.jpg", "channelLogo.jpg", "byte", "ChannelLogo".getBytes());
		carad = new CarAd();
	}
	
	@Test
	void test_createCar() {
	 mockcaradservice.createCar(carad);
	 verify(mockCarRepo, times(1)).save(carad);
	}
	
	@Test
	void test_saveCar() {
	 mockcaradservice.saveCar(carad);
	 verify(mockCarRepo, times(1)).save(carad);
	}
	@Test
	void test_deleteCar() {
	 mockcaradservice.deleteCar(1);
	 verify(mockCarRepo, times(1)).deleteById(1);
	}
	
	@Test
	void test_FindAllCars() {
		mockcaradservice.findAllCars();
		verify(mockCarRepo, times(1)).findAll();
	}
	
	@Test
	void test_findCarById() {
		mockcaradservice.findCarById(1);
		verify(mockCarRepo, times(1)).findById(1);
	}
	@Test
	void test_findActiveCarAds() {
    List<CarAd> cars = new ArrayList<>();
   
    for (int i = 0; i < 2; i++) {
        cars.add(new CarAd());
    }
    
    when(mockCarRepo.findAll()).thenReturn(cars);
    List<CarAd> allActiveCars = mockcaradservice.findAllActiveCarAds();
    for (CarAd item :allActiveCars) {
        assertTrue(item.isActive());
    }
	}
	
	@Test
	void test_findByDealerID() {
		mockcaradservice.findByDealerID(1);
		verify(mockCarRepo,times(1)).findAllByDealerID(1);
	}
	@Test
	void test_filterByMake() {
		mockcaradservice.filterByMake(anyString());
		verify(mockCarRepo, times(1)).findByMakeContainsIgnoreCase(anyString());
	}
	@Test
	void test_filterByModel() {
		mockcaradservice.filterByModel(anyString());
		verify(mockCarRepo, times(1)).findByModelContainsIgnoreCase(anyString());
	}
	@Test
	void test_filterByColour() {
		mockcaradservice.filterByColour(anyString());
		verify(mockCarRepo, times(1)).findByColourContainsIgnoreCase(anyString());
	}
	
	
    @Test
    void test_encodeImageBase64Converts() {
        byte[] bytes = mockcaradservice.encodeImageToBase64(mockGoodFile);
        String encodedString = new String(bytes, StandardCharsets.UTF_8);
        assertTrue(encodedString.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?"));
    }

    @Test
    void test_encodeImageToBase64ThrowsErrorIfNotCorrectFileExtension() throws RuntimeException {
        Exception exception = assertThrows(RuntimeException.class, ()
                -> mockcaradservice.encodeImageToBase64(mockBadFile));

        String expectedMessage = "Only jpg/jpeg and png files are accepted";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
	

}
