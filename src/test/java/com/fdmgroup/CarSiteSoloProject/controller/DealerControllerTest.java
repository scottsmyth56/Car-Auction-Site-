package com.fdmgroup.CarSiteSoloProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.fdmgroup.CarSiteSoloProject.CarSiteSoloProject;
import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.RaffleEntries;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.RaffleEntriesService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CarSiteSoloProject.class)
public class DealerControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CarAdService caradserviceMock;

	@MockBean
	DealerService dealerserviceMock;
	
	@MockBean
	CustomerService customerserviceMock;
	
	@MockBean
	RaffleEntriesService raffleServiceMock;

	private CarAd carad;
	private CarAd carad2;
	private Dealer dealer;
	private Customer customer;
	private RaffleEntries winner;
	private List <CarAd> expectedList;
	private List <RaffleEntries> raffleexpectedList;
	private List <Dealer> dealerList;
	
	@BeforeEach
	void init() {
	expectedList = new ArrayList();
	raffleexpectedList = new ArrayList();
	dealerList = new ArrayList<>();
	carad2 = new CarAd();
	carad = new CarAd();
	dealer = new Dealer();
	customer = new Customer();	
	winner = new RaffleEntries();
	customer.setCustomerID(1);
	customer.setEmail("a");
	customer.setFirstName("a");
	customer.setLastName("a");
	customer.setPhoneNum("a");
	ModelMap customerModel = new ModelMap();
    customerModel.addAttribute("customer", customer);
	}
	
	
	@Test
	@WithMockUser(roles = {"Customer"},username = "testuser")
	void test_enterRaffle() throws Exception {
		when(customerserviceMock.findCustomerByEmail("testuser")).thenReturn(customer);
		mockmvc.perform(get("/EnterRaffle/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("DealerPageCustomerView"));
		
	}
	
	@Test
	@WithMockUser(roles = {"Dealer"},username = "testuser")
	void test_runRaffle() throws Exception {
		RaffleEntries winner = 	new RaffleEntries();

		when(dealerserviceMock.findDealerByEmail("testuser")).thenReturn(dealer);
		when(raffleServiceMock.findAllByDealerID(1)).thenReturn(raffleexpectedList);
		when(raffleServiceMock.findWinner(raffleexpectedList)).thenReturn(winner);
		when(customerserviceMock.findCustomerbyId(1)).thenReturn(customer);
		customer.setCustomerID(1);
		customer.setEmail("a");
		customer.setFirstName("a");
		customer.setLastName("a");
		customer.setPhoneNum("a");
		
		mockmvc.perform(get("/runRaffle"))
		.andExpect(status().isOk())	
		.andExpect(view().name("DealerOnlineBidding"));
		//.andExpect(model().attribute("customer", customer));
	
		
	}
	@Test
    @WithMockUser(roles = { "Customer" }, username = "testuser")
    void test_DealerViewAllCars() throws Exception {
        when(dealerserviceMock.findAllDealers()).thenReturn(dealerList);   
        		mockmvc.perform(post("/viewDealers"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewDealers"))
                .andExpect(model().attribute("dealers", dealerList));
    }
	
	
	
}

