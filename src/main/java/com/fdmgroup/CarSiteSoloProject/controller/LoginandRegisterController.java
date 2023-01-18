package com.fdmgroup.CarSiteSoloProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.Role;
import com.fdmgroup.CarSiteSoloProject.model.User;
import com.fdmgroup.CarSiteSoloProject.security.DefaultUserDetailsService;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.RoleService;
import com.fdmgroup.CarSiteSoloProject.service.UserService;

@Controller
public class LoginandRegisterController {

		@Autowired
		private DefaultUserDetailsService DefaultuserService;

		@Autowired
		private UserService userService;

		@Autowired
		private PasswordEncoder encoder;

		@Autowired
		private RoleService roleService;

		@Autowired
		private CustomerService customerService;

		@Autowired
		private DealerService dealerService;
		
		@Autowired
		private CarAdService caradservice;

		@GetMapping("/login")
		public String login() {
			return "login";
		}

		@GetMapping("/registerCustomer")
		public String registerCustomer() {
			return "registerCustomer";
		}
		@GetMapping("/registerDealer")
		public String registerDealer() {
			return "registerDealer";
		}

		@GetMapping("/redirect")
		public String redirect(ModelMap model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentUserName = authentication.getName();

			User currentUser = userService.findByUsername(currentUserName);

			List<Role> currentRoles = new ArrayList<>();

			currentRoles.add(currentUser.getRole());

			ArrayList<String> roleNames = new ArrayList<>();
			for (Role role : currentRoles) {
				roleNames.add(role.getRoleName());
			}

			model.addAttribute("username", currentUser.getUsername());
			model.addAttribute("roles", roleNames);

			if (roleNames.contains("Admin")) {
				return "adminStartPage";
			}

			if (roleNames.contains("Dealer")) {
				model.addAttribute("dealer", dealerService.findDealerByEmail(currentUserName));
				model.addAttribute("role", "Dealer");

				return DealerViewAllCars(model);
			}

			if (roleNames.contains("Customer")) {

				Customer currentCustomer = customerService.findCustomerByEmail(currentUserName);
				model.addAttribute("customer", currentCustomer);
				model.addAttribute("role", "Customer");

				return CustomerIndex(model);
			}
			System.out.println(
					"User role of logged in user not existent. Available user roles are Customer, Dealer and Admin.");
			return null;
		}

		@PostMapping("/registerCustomer")
		public String registerSubmitCustomer(ModelMap model,
				@RequestParam(name = "firstName") String firstName,
				@RequestParam(name = "lastName") String lastName,
				@RequestParam(name = "username") String username,
				@RequestParam(name = "password") String password,
				@RequestParam(name = "phoneNum") String phoneNum) {
			
			
			Optional<User> userFromDatabase = DefaultuserService.findByUsername(username);
			if (userFromDatabase.isPresent()) {
				model.addAttribute("message", "This user name already exists");
				return "registerCustomer";
			}
			
			Role role = new Role();
			role.setRoleName("Customer");

			User user = new User();
			user.setUsername(username);
			user.setRole(role);
			user.setPassword(encoder.encode(password));
			DefaultuserService.save(user);
			
			
			Customer c = new Customer();
			c.setEmail(username);
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setPhoneNum(phoneNum);
			customerService.saveCustomer(c);

			return "login";
		}
		@PostMapping("/registerDealer")
		public String registerSubmitDealer(ModelMap model,
				@RequestParam(name = "dealerName") String dealerName,
				@RequestParam(name = "location") String location,
				@RequestParam(name = "about") String about,
				@RequestParam(name = "username") String username,
				@RequestParam(name = "password") String password,
				@RequestParam(name = "file") MultipartFile file) {
			
			
			Optional<User> userFromDatabase = DefaultuserService.findByUsername(username);
			if (userFromDatabase.isPresent()) {
				model.addAttribute("message", "This user name already exists");
				return "register";
			}
			
			Role role = new Role();
			role.setRoleName("Dealer");

			User user = new User();
			user.setUsername(username);
			user.setRole(role);
			user.setPassword(encoder.encode(password));
			DefaultuserService.save(user);
			
			
			Dealer d = new Dealer();
			d.setDealerName(dealerName);
			d.setEmail(username);
			d.setLocation(location);
			d.setAbout(about);
			//d.setFile(file);
			d.setLogo(dealerService.encodeImageToBase64(file));
			dealerService.saveDealer(d);
d.getLogo();
			return "login";
		}
	
	public String DealerViewAllCars(ModelMap model) {
        List<CarAd> list = caradservice.findAllActiveCarAds();
        model.addAttribute("Cars", list);
        return "DealerIndex";
}
	
	public String CustomerIndex(ModelMap model) {

			Dealer Featureddealer = dealerService.findFeaturedDealer();
			List<CarAd> list = caradservice.findAllActiveCarAds();
			
			model.addAttribute("Cars", list);
			model.addAttribute("dealer",Featureddealer);
			
			return "index";
	}
	
	}
	

