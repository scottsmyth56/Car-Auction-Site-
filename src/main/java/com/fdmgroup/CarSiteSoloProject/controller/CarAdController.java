package com.fdmgroup.CarSiteSoloProject.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.FavouritedCars;
import com.fdmgroup.CarSiteSoloProject.repository.CarAdRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.FavouritedCarsService;

@Controller
public class CarAdController {

	@Autowired
	private CarAdService caradservice;

	@Autowired
	private DealerService dealerservice;

	@Autowired
	private CarAdRepository caradRepo;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FavouritedCarsService favCarService;

	private Authentication authentication;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("addCar")
	public ModelAndView formJob() {
		return new ModelAndView("addCarAD", "command", new CarAd());
	}

	@PostMapping(value = "saveCar")
	public String addCar(@ModelAttribute CarAd carad) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();

		carad.setExteriorImage(caradservice.encodeImageToBase64(carad.getFileExterior()));
		carad.setExteriorImage2(caradservice.encodeImageToBase64(carad.getFileExterior2()));
		carad.setInteriorImage(caradservice.encodeImageToBase64(carad.getFileInterior()));

		carad.setDealerID(dealerservice.findDealerByEmail(currentUserName).getDealerID());
		carad.setActive(true);
		caradservice.createCar(carad);
		return "index";
	}

	@GetMapping("/DealerViewAllCars")
	public String DealerViewAllCars(ModelMap model) {
		List<CarAd> list = caradservice.findAllActiveCarAds();
		model.addAttribute("Cars", list);
		return "DealerIndex";
	}

	@GetMapping("/CustomerViewAllCars")
	public String CustomerViewAllCars(ModelMap model) {
		List<CarAd> list = caradservice.findAllActiveCarAds();
		model.addAttribute("Cars", list);
		return "index";
	}

	@PostMapping("editCar/{id}")
	public ModelAndView formUpdateCar(@PathVariable int id) {
		CarAd car = caradservice.findCarById(id);
		return new ModelAndView("editCar", "command", car);
	}

	@PostMapping("updateCar")
	public String updateUploadedCar(ModelMap model, @ModelAttribute CarAd car) {
		car.setExteriorImage(caradservice.encodeImageToBase64(car.getFileExterior()));
		car.setExteriorImage2(caradservice.encodeImageToBase64(car.getFileExterior2()));
		car.setInteriorImage(caradservice.encodeImageToBase64(car.getFileInterior()));
		car.setActive(true);
		caradservice.saveCar(car);

		return goToDealerIndex(model);
	}

	@RequestMapping(path = "deleteCar/{id}")
	public String deleteCar(ModelMap model, @PathVariable int id) {
		caradservice.deleteCar(id);
		return goToDealerIndex(model);
	}

	@GetMapping("/goToDealerIndex")
	private String goToDealerIndex(ModelMap model) {
		List<CarAd> list = caradservice.findAllActiveCarAds();
		model.addAttribute("Cars", list);
		return "DealerIndex";
	}

	@GetMapping("/CustomerViewCar/{id}")
	private String CustomerViewCar(ModelMap model, @PathVariable int id) {
		CarAd carad = caradservice.findCarById(id);
		Dealer d = dealerservice.findDealerbyId(carad.getDealerID());
		
		model.addAttribute("dealer",d);
		model.addAttribute("car", carad);
		return "customerViewCar";

	}
	@GetMapping("viewDealerRange/{id}")
	public String viewDealerRange(ModelMap model, @PathVariable int id) {
		
		List<CarAd> list = caradservice.findByDealerID(id);
		Dealer dealer = dealerservice.findDealerbyId(id);
		List<CarAd> activeList = list.stream().filter(CarAd::isActive).collect(Collectors.toList());
		List<CarAd> soldList = list.stream().filter(x -> ! x.isActive()).collect(Collectors.toList());		
		model.addAttribute("forSale",activeList);
		model.addAttribute("soldList", soldList);
		model.addAttribute("dealer",dealer);
		
		return "DealerPageCustomerView";
	}
	
	@GetMapping("favouriteCar/{id}")
	public String favouriteCar(ModelMap model,@PathVariable int id) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();
		
		FavouritedCars favcar = new FavouritedCars();
		favcar.setCarAdID(id);
		favcar.setCustomerID(customerID);
		favCarService.save(favcar);
		
		return CustomerViewAllCars(model);
	}
	@GetMapping("/unfavouriteCar")
	public String unfavouriteCar(ModelMap model, @RequestParam(name = "carID") int carID) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();
	
		favCarService.UnfavouriteCar(customerID,carID);

		return getFavouritedCars(model);
		
		
	}
	@GetMapping("/getFavouritedCars")
	public String getFavouritedCars(ModelMap model) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		int customerID = customerService.findCustomerByEmail(currentUserName).getCustomerID();

		Customer customer = customerService.findCustomerbyId(customerID);
		List <CarAd> list = favCarService.findFavouritedCarsByCustomerID(customerID);
		
		model.addAttribute("cars",list);
		
		return "FavouritedCars";

}
	@PostMapping("/filtered")
	public String filter(ModelMap model,@RequestParam(name = "input") String input) {
		 List<CarAd> filteredCars = caradservice.filterCars(input);
		
		model.addAttribute("Cars",filteredCars);
	
		return "index";
	}
	@GetMapping("/CompareCars")
	public String compareCars(ModelMap model) {
		
		List <CarAd> allCars = caradservice.findAllActiveCarAds();
		model.addAttribute("cars",allCars);
		
		return "CompareCars";
	}
	@PostMapping("/compareTwoCars")
	public String compareCars(ModelMap model, @RequestParam(name = "car1") int car1ID,@RequestParam(name = "car2") int car2ID) {
		
		CarAd car1 = caradservice.findCarById(car1ID);
		CarAd car2 = caradservice.findCarById(car2ID);
		
		model.addAttribute("car1",car1);
		model.addAttribute("car2",car2);
		
		return "CompareCars";
	}
}

