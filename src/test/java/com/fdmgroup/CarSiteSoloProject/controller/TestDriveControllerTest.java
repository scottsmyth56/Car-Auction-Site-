package com.fdmgroup.CarSiteSoloProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.fdmgroup.CarSiteSoloProject.model.TestDrive;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CarSiteSoloProject.class)
public class TestDriveControllerTest {
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CarAdService caradserviceMock;

	@MockBean
	DealerService dealerserviceMock;
	
	@Autowired
	CustomerService service;
	
	@MockBean
	CustomerService customerserviceMock;
	
	
	
	private TestDrive testDrive;
	private CarAd carad;
	private CarAd carad2;
	private Dealer dealer;
	private Customer customer;
	private RaffleEntries winner;
	private List <Dealer> dealerList;
	
	@BeforeEach
	void init() {
	dealerList = new ArrayList<>();
	carad2 = new CarAd();
	carad = new CarAd();
	testDrive = new TestDrive();
	customer = new Customer();
	customer.setCustomerID(1);
	}
	
	@Test
	@WithMockUser(roles = { "Customer" }, username = "testuser")
	void test_bookTestDrive() throws Exception {
		when(customerserviceMock.findCustomerByEmail("testuser")).thenReturn(customer);
		when(caradserviceMock.findCarById(1)).thenReturn(carad);
		when(customerserviceMock.findCustomerbyId(1)).thenReturn(customer);
		

		mockmvc.perform(get("/bookTestDrive/2/2"))
		.andExpect(status().isOk())
		.andExpect(view().name("bookTestDrive"))
		.andExpect(model().attribute("dealer", dealer))
		.andExpect(model().attribute("car", carad))
		.andExpect(model().attribute("customer", customer));
	}
	@Test
	@WithMockUser(roles = { "Customer" }, username = "testuser")
	void test_saveBooking() throws Exception {
		when(dealerserviceMock.findDealerbyId(1)).thenReturn(dealer);
		when(caradserviceMock.findCarById(1)).thenReturn(carad);
		when(customerserviceMock.findCustomerbyId(1)).thenReturn(customer);
	

		mockmvc.perform(post("/saveBooking")
				.param("dealerID", String.valueOf(1))
				.param("carAdID", String.valueOf(2))
				.param("customerID", String.valueOf(2))
				.param("bookingDate", "2023-12-12"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));

}

}
