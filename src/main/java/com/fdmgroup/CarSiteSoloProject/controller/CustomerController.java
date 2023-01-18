package com.fdmgroup.CarSiteSoloProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.CarSiteSoloProject.repository.CustomerRepository;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;

@Controller
public class CustomerController {
	

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CustomerService customerservice;

	
	

}
