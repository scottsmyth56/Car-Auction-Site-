package com.fdmgroup.CarSiteSoloProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.RaffleEntries;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.RaffleEntriesService;



@Controller
public class DealerController {

	@Autowired
	CarAdController caradController;

	@Autowired
	DealerService dealerService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RaffleEntriesService raffleService;
	
	@Autowired
	OnlineBidsController BidController;
	
	private Authentication authentication;

	@GetMapping("EnterRaffle/{id}")
	public String enterRaffle(ModelMap model, @PathVariable int id) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();

		RaffleEntries entry = new RaffleEntries(); 
		entry.setCustomerID(customerID);
		entry.setDealerID(id);
		raffleService.save(entry);
		
		return caradController.viewDealerRange(model, id);
	}
	
	@GetMapping("/runRaffle")
	public String runRaffle(ModelMap model) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int dealerID = dealerService.findDealerByEmail(currentUserName).getDealerID();
		List <RaffleEntries> allList = raffleService.findAllByDealerID(dealerID);	
		RaffleEntries winner = raffleService.findWinner(allList);
		Customer customerWinner = customerService.findCustomerbyId(winner.getCustomerID());
		
		model.addAttribute("customer",customerWinner);
		return BidController.dealerViewOwnCarAds(model);
		
		
	}
	
	@PostMapping("/viewDealers")
	public String viewAllDealers(ModelMap model) {		
		List<Dealer> list = dealerService.findAllDealers();
		model.addAttribute("dealers",list);
		return "ViewDealers";
		
		
	}

}
