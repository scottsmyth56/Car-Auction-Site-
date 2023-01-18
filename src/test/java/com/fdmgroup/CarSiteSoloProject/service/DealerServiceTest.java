package com.fdmgroup.CarSiteSoloProject.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.repository.CarAdRepository;
import com.fdmgroup.CarSiteSoloProject.repository.DealerRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DealerServiceTest {


	@InjectMocks
	private DealerService mockdealerService;

	@Mock
	private Dealer mockDealer;

	@MockBean
	private DealerRepository mockdealerRepo;

	private MockMultipartFile mockBadFile;
	private MockMultipartFile mockGoodFile;
	private Dealer dealer;
	private List<Dealer> expectedList;
	
	@BeforeEach
	void init() {
		mockBadFile = new MockMultipartFile("channelLogo.pdf", "channelLogo.pdf", "byte", "ChannelLogo".getBytes());
		mockGoodFile = new MockMultipartFile("channelLogo.jpg", "channelLogo.jpg", "byte", "ChannelLogo".getBytes());
		dealer = new Dealer();
		expectedList = new ArrayList<>();
	}
	
	@Test
	void test_findDealerById() {
		mockdealerService.findDealerbyId(1);
		 verify(mockdealerRepo, times(1)).findById(1);	
		 }
	@Test
	void test_findDealerByEmail() {
		mockdealerService.findDealerByEmail("email");
		 verify(mockdealerRepo, times(1)).findDealerByEmail("email");	
		 }
	@Test
	void test_saveDealer() {
		mockdealerService.saveDealer(dealer);;
		 verify(mockdealerRepo, times(1)).save(dealer);	
		 }
	@Test
	void test_findAllDealers() {
		mockdealerService.findAllDealers();
		 verify(mockdealerRepo, times(1)).findAll();	
		 }
	

	@Test
    void test_encodeImageBase64Converts() {
        byte[] bytes = mockdealerService.encodeImageToBase64(mockGoodFile);
        String encodedString = new String(bytes, StandardCharsets.UTF_8);
        assertTrue(encodedString.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?"));
    }

    @Test
    void test_encodeImageToBase64ThrowsErrorIfNotCorrectFileExtension() throws RuntimeException {
        Exception exception = assertThrows(RuntimeException.class, ()
                -> mockdealerService.encodeImageToBase64(mockBadFile));

        String expectedMessage = "Only jpg/jpeg and png files are accepted";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
	
	
	
	
	
	
}
