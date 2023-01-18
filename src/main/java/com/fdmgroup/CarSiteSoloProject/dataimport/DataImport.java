package com.fdmgroup.CarSiteSoloProject.dataimport;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.Role;
import com.fdmgroup.CarSiteSoloProject.model.User;
import com.fdmgroup.CarSiteSoloProject.repository.CustomerRepository;
import com.fdmgroup.CarSiteSoloProject.repository.DealerRepository;
import com.fdmgroup.CarSiteSoloProject.repository.RoleRepository;
import com.fdmgroup.CarSiteSoloProject.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private DealerRepository dealerRepo;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (!userRepository.findByUsername("admin").isPresent()) {
			Role roleAdmin = new Role("Admin");
			roleRepo.save(roleAdmin);
			Role roleCustomer = new Role("Customer");
			roleRepo.save(roleCustomer);
			Role roleDealer = new Role("Dealer");
			roleRepo.save(roleDealer);

			Dealer dealer = new Dealer("Fast Cars Auto", "Dublin", "Premium Car Dealer","FastCars@gmail.com");
			Dealer dealer2 = new Dealer("Used Autos Naas", "Naas",  "Used Car Dealer","UsedCarsAuto@gmail.com");
			dealerRepo.save(dealer);
			dealerRepo.save(dealer2);
			
			Customer customer1 = new Customer("Scott","Smyth","ScottSmyth@gmail.com","+3530877171773");
			customerRepo.save(customer1);

			User admin = new User("admin", encoder.encode("123"), roleAdmin);
			userRepository.save(admin);

			User customer = new User("ScottSmyth@gmail.com", encoder.encode("123"), roleCustomer);
			userRepository.save(customer);

			User Dealer = new User("FastCars@gmail.com", encoder.encode("123"), roleDealer);
			userRepository.save(Dealer);

			User Dealer2 = new User("UsedCarsAuto@gmail.com", encoder.encode("123"), roleDealer);
			userRepository.save(Dealer2);
		}

	}
}
