package com.fdmgroup.CarSiteSoloProject.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.TestDrive;
import com.fdmgroup.CarSiteSoloProject.repository.TestDriveRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.TestDriveService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.sql.Date;

@Controller
public class TestDriveController {
	
	 public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	 public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

	@Autowired
	private TestDriveService testDriveService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CarAdService caradService;
	
	@Autowired
	private CarAdController caradcontroller;

	@Autowired
	private DealerService dealerService;

	private Authentication authentication;

	@RequestMapping("/bookTestDrive/{carAdID}/{dealerID}")
	public String bookTestDrive(ModelMap model, @PathVariable("carAdID") int carAdID,
			@PathVariable("dealerID") int dealerID) {

		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();
		CarAd car = caradService.findCarById(carAdID);
		Dealer dealer = dealerService.findDealerbyId(dealerID);
		Customer customer = customerService.findCustomerbyId(customerID);

		model.addAttribute("customer", customer);
		model.addAttribute("dealer", dealer);
		model.addAttribute("car", car);

		return "bookTestDrive";
	}

	@PostMapping(value = "/saveBooking")
	public String saveBooking(ModelMap model, @RequestParam(name = "dealerID") int dealerID,
			@RequestParam(name = "carAdID") int carAdID, @RequestParam(name = "customerID") int customerID,
			@RequestParam(name = "bookingDate") Date bookingDate) {

		TestDrive booking = new TestDrive();
		booking.setCarAdID(carAdID);
		booking.setCustomerID(customerID);
		booking.setDealerID(dealerID);
		booking.setBookingDate(bookingDate);

		testDriveService.save(booking);
		
		Customer customer = customerService.findCustomerbyId(customerID);
		Dealer dealer = dealerService.findDealerbyId(dealerID);
		CarAd car = caradService.findCarById(carAdID);
		
		notifyCustomer(customer,dealer,booking,car);
		
		return caradcontroller.CustomerViewAllCars(model);

	}

	
	private void notifyCustomer(Customer customer,Dealer dealer,TestDrive booking, CarAd car) {
		String number = customer.getPhoneNum();
		String confirmation =
				"** Hi," + customer.getFirstName() 
				+ "\n This is a confirmation message to confirm your Test Drive Booking with " 
				+ dealer.getDealerName() + " ON " + booking.getBookingDate() + "\nAt their Dealership in " 
				+ dealer.getLocation() 
				+ "\nCar you requested to View: " + car.getMake()+ "," + car.getModel() ;
				
				
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber(number),
				new com.twilio.type.PhoneNumber("+19134446438"), confirmation).create();

		System.out.println(message.getSid());
		
	}

}
