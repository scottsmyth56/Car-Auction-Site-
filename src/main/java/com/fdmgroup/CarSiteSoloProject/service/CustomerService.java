package com.fdmgroup.CarSiteSoloProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;

	public Customer findCustomerbyId(int id) {
        Optional<Customer> t1 = repo.findById(id);
        return t1.orElse(new Customer());
    }

    public Customer findCustomerByEmail(String email) {
        Optional<Customer> t1 = repo.findByEmail(email);
        return t1.orElse(new Customer());
    }

    public void saveCustomer(Customer customer) {
        repo.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return repo.findAll();
    }
	
}
