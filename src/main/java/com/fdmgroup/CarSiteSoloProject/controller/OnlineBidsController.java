package com.fdmgroup.CarSiteSoloProject.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.OnlineBids;
import com.fdmgroup.CarSiteSoloProject.repository.OnlineBidsRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.OnlineBidsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Controller
public class OnlineBidsController {
	
	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


	@Autowired
	private OnlineBidsRepository biddingRepo;

	@Autowired
	private OnlineBidsService biddingService;

	@Autowired
	private CarAdService caradservice;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DealerService dealerService;

	private Authentication authentication;

	@RequestMapping("/viewCarsOnline")
	public String viewCarsOnline(ModelMap model) {
		List<CarAd> list = caradservice.findAllActiveCarAds();
		model.addAttribute("cars", list);
		return "OnlineBidding";
	}

	@GetMapping("viewBidding/{id}")
	public String viewBidding(ModelMap model, @PathVariable int id) {

		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();

		Customer customer = customerService.findCustomerbyId(customerID);
		CarAd car = caradservice.findCarById(id);
		OnlineBids winningBid = biddingService.findWinningBidByCarID(id);
		List<OnlineBids> Allbids = biddingService.findAllBidsByCarAdID(id);

		model.addAttribute("car", car);
		model.addAttribute("Listofbids", Allbids);
		model.addAttribute("customer", customer);
		model.addAttribute("winningBid", winningBid);

		return "CarBiddingPage";
	}

	@PostMapping("/placeBid")
	public String placeBid(ModelMap model, @RequestParam(name = "dealerID") int dealerID,
			@RequestParam(name = "carAdID") int carAdID, @RequestParam(name = "customerID") int customerID,
			@RequestParam(name = "bidPrice") int bidPrice) {
		String email = customerService.findCustomerbyId(customerID).getEmail();
		OnlineBids bid = new OnlineBids();
		bid.setCustomerID(customerID);
		bid.setBidPrice(bidPrice);
		bid.setDealerID(dealerID);
		bid.setCarAdID(carAdID);
		bid.setActive(true);
		bid.setBidderEmail(email);
		biddingService.saveBid(bid);
		return viewBidding(model, carAdID);

	}

	@RequestMapping("DealerViewOwnCarAds")
	public String dealerViewOwnCarAds(ModelMap model) {

		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int dealerID = dealerService.findDealerByEmail(currentUserName).getDealerID();

		List<CarAd> list = caradservice.findByDealerID(dealerID);
		List<CarAd> activeList = list.stream().filter(CarAd::isActive).collect(Collectors.toList());
		List<CarAd> soldList = list.stream().filter(x -> ! x.isActive()).collect(Collectors.toList());
		
		model.addAttribute("activeList", activeList);
		model.addAttribute("soldList", soldList);

		return "DealerOnlineBidding";
	}

	@GetMapping("DealerviewBidding/{id}")
	public String DealerviewBidding(ModelMap model, @PathVariable int id) {
	
	authentication = SecurityContextHolder.getContext().getAuthentication();
	String currentUserName = authentication.getName();

	CarAd car = caradservice.findCarById(id);
	OnlineBids winningBid = biddingService.findWinningBidByCarID(id);
	List<OnlineBids> Allbids = biddingService.findAllBidsByCarAdID(id);
	
	model.addAttribute("car",car);
	model.addAttribute("Listofbids",Allbids);
	model.addAttribute("winningBid",winningBid);
	
	return "DealerCarBiddingPage";
}
	@PostMapping("/endAuction")
	public String endAuction(ModelMap model,@RequestParam(name = "carAdID") int id) {
		
		CarAd car = caradservice.findCarById(id);
		OnlineBids winningBid = biddingService.findWinningBidByCarID(id);		
		Customer customer = customerService.findCustomerByEmail(winningBid.getBidderEmail());
	
		car.setActive(false);
		caradservice.saveCar(car);
		
		NotifyCustomerOfWin(car,winningBid,customer);
		
		return dealerViewOwnCarAds(model);
		
	}
	@PostMapping("removeBid")
	public String removeBid(ModelMap model, @RequestParam(name = "bidID") int Bidid, @RequestParam(name = "carAdID") int Carid) {
		
		OnlineBids bid = biddingService.findBidByID(Bidid);
		biddingService.removeBid(bid);
		
		return viewBidding(model, Carid);
	}

	private void NotifyCustomerOfWin(CarAd car, OnlineBids winningBid, Customer customer) {
		Dealer dealer=	dealerService.findDealerbyId(winningBid.getDealerID());
		String number = customer.getPhoneNum();
		String confirmation =
				"Hi " + customer.getFirstName() + customer.getLastName() 
				+ "\n You are the Winning Bidder in the Auction for " + car.getMake() +" " + car.getModel() 
				+ "\nFinal Price: " + winningBid.getBidPrice() 
				+ "\nYour car is Available for collection at " + " " + dealer.getDealerName()
				+ "\nContact " + " " + dealer.getEmail() + " to arrange Collection";
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber(number),
				new com.twilio.type.PhoneNumber("+19134446438"), confirmation).create();

		System.out.println(message.getSid());
		
	}
		
	}
