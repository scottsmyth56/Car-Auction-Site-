package com.fdmgroup.CarSiteSoloProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.CarSiteSoloProject.CarSiteSoloProject;
import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.FavouritedCars;
import com.fdmgroup.CarSiteSoloProject.repository.CarAdRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.FavouritedCarsService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CarSiteSoloProject.class)
public class CarAdControllerTest {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	CarAdService caradserviceMock;

	@MockBean
	DealerService dealerserviceMock;
	
	@MockBean
	CustomerService customerserviceMock;
	
	@MockBean
	FavouritedCarsService favCarServiceMock;

	@MockBean
	CarAdRepository caradRepoMock;
	
	private CarAd carad;
	private CarAd carad2;
	private Dealer dealer;
	private Customer customer;
	private List <CarAd> expectedList;
	
	@BeforeEach
	void init() {
	expectedList = new ArrayList();
	carad2 = new CarAd();
	carad = new CarAd();
	dealer = new Dealer();
	customer = new Customer();	
	}
	
	

	@Test
	@WithMockUser(roles = {"Dealer"})
	void test_goToIndex() throws Exception {
		mockmvc.perform(get("/index"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));

	}
	@Test
	@WithMockUser(roles = {"Dealer"})
	void test_goToDealerIndex() throws Exception {
	when(caradserviceMock.findAllCars()).thenReturn(expectedList);
		mockmvc.perform(get("/goToDealerIndex"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("Cars", expectedList))
		.andExpect(view().name("DealerIndex"));

	}

	@Test
	@WithMockUser(roles = {"Dealer"})
	void test_getAddCarForm() throws Exception {
		mockmvc.perform(get("/addCar"))
         .andExpect(status().isOk())
         .andExpect(view().name("addCarAD"));
}
	@Test
	@WithMockUser(roles = {"Dealer"})
	void test_getUpdateCarForm() throws Exception {
		mockmvc.perform(post("/editCar/1"))
         .andExpect(status().isOk())
         .andExpect(view().name("editCar"));
}
	@Test
    @WithMockUser(roles = { "Dealer" })
    void test_deleteCarAD() throws Exception {
        when(caradserviceMock.findCarById(1)).thenReturn(carad);
        mockmvc.perform(get("/deleteCar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("DealerIndex"));
    }
	@Test
    @WithMockUser(roles = { "Dealer" })
    void test_updateCarAd() throws Exception {
        mockmvc.perform(post("/updateCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("DealerIndex"));
    }

	@Test
	@WithMockUser(roles = {"Customer"})
	void test_CustomerViewCar() throws Exception {
		when(caradserviceMock.findCarById(1)).thenReturn(carad);
		mockmvc.perform(get("/CustomerViewCar/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("customerViewCar"))
		.andExpect(model().attribute("car", carad));

	}
	@Test
    @WithMockUser(roles = { "Cusomer" })
    void test_CustomerViewAllCars() throws Exception {
        when(caradserviceMock.findAllCars()).thenReturn(expectedList);
       mockmvc.perform(get("/CustomerViewAllCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("Cars", expectedList));
    }
	
	@Test
    @WithMockUser(roles = { "Dealer" })
    void test_DealerViewAllCars() throws Exception {
        when(caradserviceMock.findAllCars()).thenReturn(expectedList);   
       mockmvc.perform(get("/DealerViewAllCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("DealerIndex"))
                .andExpect(model().attribute("Cars", expectedList));
    }
	
	@Test
    @WithMockUser(roles = { "Dealer" }, username = "testuser" )
    void test_SaveCar() throws Exception {
		 when(dealerserviceMock.findDealerByEmail("testuser")).thenReturn(dealer);
        mockmvc.perform(post("/saveCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
	
	@Test
	@WithMockUser(roles = {"Customer"})
	void test_viewDealerRange() throws Exception {
		when(dealerserviceMock.findDealerbyId(1)).thenReturn(dealer);
		when(caradserviceMock.findByDealerID(1)).thenReturn(expectedList);
		
		List<CarAd> activeList = new ArrayList<>();
		List<CarAd> soldList = new ArrayList<>();
		
		mockmvc.perform(get("/viewDealerRange/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("DealerPageCustomerView"))
		.andExpect(model().attribute("forSale", activeList))
		.andExpect(model().attribute("soldList", soldList))
		.andExpect(model().attribute("dealer", dealer));
				
	}
	@Test
	@WithMockUser(roles = {"Customer"}, username ="testuser")
	void test_favouriteCar() throws Exception {
		when(customerserviceMock.findCustomerByEmail("testuser")).thenReturn(customer);
		mockmvc.perform(get("/favouriteCar/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	@Test
	@WithMockUser(roles = {"Customer"}, username ="testuser")
	void test_getfavouritedCar() throws Exception {
		when(customerserviceMock.findCustomerByEmail("testuser")).thenReturn(customer);
		when(customerserviceMock.findCustomerbyId(1)).thenReturn(customer);
		when(favCarServiceMock.findFavouritedCarsByCustomerID(1)).thenReturn(expectedList);
		mockmvc.perform(get("/getFavouritedCars"))
		.andExpect(status().isOk())
		.andExpect(view().name("FavouritedCars"))
		.andExpect(model().attribute("cars", expectedList));
	}
	@Test
	@WithMockUser(roles = {"Customer"})
	void test_filtering() throws Exception {
		String input ="testinput";
		when(caradserviceMock.filterCars("input")).thenReturn(expectedList);
		mockmvc.perform(post("/filtered").param("input",input))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("Cars", expectedList));
	}
	@Test
    @WithMockUser(roles = { "Customer" })
    void test_CompareCars() throws Exception {
        when(caradserviceMock.findAllCars()).thenReturn(expectedList);
       mockmvc.perform(get("/CompareCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("CompareCars"))
                .andExpect(model().attribute("cars", expectedList));
    }
	
	@Test
    @WithMockUser(roles = { "Customer" })
    void test_CompareTwoCars() throws Exception {
        when(caradserviceMock.findCarById(1)).thenReturn(carad);
       mockmvc.perform(post("/compareTwoCars").requestAttr("car1", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("CompareCars"))
                .andExpect(model().attribute("car1", carad));
       
    }
	

	
	
}
