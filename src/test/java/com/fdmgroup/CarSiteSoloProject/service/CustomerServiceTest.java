package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

	
	@InjectMocks
	private CustomerService mockcustomerService;
	
	
	@Mock
	private Customer mockCustomer;
	
	
	@MockBean
	private CustomerRepository mockcustomerRepo;
	
	
	private Customer customer;
	@BeforeEach
	void init() {
		customer = new Customer();
	}
	
	@Test
	void test_findCustomerById() {
		mockcustomerService.findCustomerbyId(1);
		verify(mockcustomerRepo, times(1)).findById(1);
	}
	
	@Test
	void test_findCustomerByEmail() {
		mockcustomerService.findCustomerByEmail("email");
		verify(mockcustomerRepo, times(1)).findByEmail("email");
	}
	@Test
	void test_findAllCustomers() {
		mockcustomerService.findAllCustomers();
		verify(mockcustomerRepo, times(1)).findAll();
	}
	@Test
	void test_saveCustomer() {
		mockcustomerService.saveCustomer(customer);
		verify(mockcustomerRepo, times(1)).save(customer);
	}



}
