package com.fdmgroup.CarSiteSoloProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.CarSiteSoloProject.CarSiteSoloProject;
import com.fdmgroup.CarSiteSoloProject.model.CarAd;
import com.fdmgroup.CarSiteSoloProject.model.Customer;
import com.fdmgroup.CarSiteSoloProject.model.User;
import com.fdmgroup.CarSiteSoloProject.repository.UserRepository;
import com.fdmgroup.CarSiteSoloProject.service.CarAdService;
import com.fdmgroup.CarSiteSoloProject.service.CustomerService;
import com.fdmgroup.CarSiteSoloProject.service.DealerService;
import com.fdmgroup.CarSiteSoloProject.service.RoleService;
import com.fdmgroup.CarSiteSoloProject.service.UserService;
import com.fdmgroup.CarSiteSoloProject.model.Dealer;
import com.fdmgroup.CarSiteSoloProject.model.Role;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CarSiteSoloProject.class)
public class LoginAndRegisterControllerTest {
	
	@Autowired
	 private MockMvc mockMvc;
	
	@MockBean
	 private UserService userServiceMock;
	 
	@MockBean
	 private UserRepository userRepoMock;
	
	@MockBean
	private CustomerService customerservicemock;
	
	@MockBean
	private DealerService dealerservicemock;
	
	@MockBean
	private RoleService roleservicemock;
	
	@MockBean
	private CarAdService carservicemock;

    private User Customer;
    private Customer c;
    private User admin;
    private Dealer d;
    private User Dealer;
    private Optional<User> optUser;
    private Optional<User> optEmptyUser;
    private List<Dealer> dealerList;
    private List<CarAd> carAdList;
    private Role CustomerRole;
    private Role DealerRole;
    private Role AdminRole;
    private Role role;
    
    

    @BeforeEach
    void prepare() {
    	Customer = new User();
    	Dealer = new User();
    	Dealer.setRole(DealerRole);
    	Customer.setRole(CustomerRole);
    	role = new Role();
    	DealerRole = new Role("Dealer");
    	CustomerRole = new Role("Customer");
    	
    	dealerList = new ArrayList<>();
    	
    	c = new Customer();
    	d = new Dealer();
    	
    	 optUser = Optional.of(new User());
         optEmptyUser = Optional.empty();
         
    }
    @Test
    void test_goToLogin() throws Exception {
    	mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));

	}
    @Test
    void test_goToRegisterCustomer() throws Exception {
    	mockMvc.perform(get("/registerCustomer"))
		.andExpect(status().isOk())
		.andExpect(view().name("registerCustomer"));

	}
    @Test
    void test_goToRegisterDealer() throws Exception {
    	mockMvc.perform(get("/registerDealer"))
		.andExpect(status().isOk())
		.andExpect(view().name("registerDealer"));

	}
    @Test
    @WithMockUser(roles = { "Customer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_redirectToIndexwhencustomerlogsin() throws Exception {    	
    	when(userServiceMock.findByUsername("testuser")).thenReturn(new User());
    	when(customerservicemock.findCustomerByEmail("testuser")).thenReturn(c);
        mockMvc.perform(get("/redirect"))
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("role", "Customer"))
        .andExpect(model().attribute("customer", c));
}
    @Test
    @WithMockUser(roles = {  "Customer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_registerCustomerWithAlreadyExistingUser() throws Exception {
        when(userRepoMock.findByUsername("testuser")).thenReturn(optUser);
        mockMvc.perform(post("/registerCustomer")
        		.param("firstName", "testuser")
        		.param("lastName", "ggg")
        		.param("username", "fff")
        		.param("phoneNum", "jfhf"))
                .andExpect(view().name("registerCustomer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "The user with username testuser already exists"));
    }
    @Test
    @WithMockUser(roles = { "Dealer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_redirectToDealerIndexwhenDealerlogsin() throws Exception {    	
    	when(userServiceMock.findByUsername("testuser")).thenReturn(new User());
    	when(dealerservicemock.findDealerByEmail("testuser")).thenReturn(d);
        mockMvc.perform(get("/redirect"))
        .andExpect(view().name("DealerIndex"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("role", "Dealer"))
        .andExpect(model().attribute("dealer", d));
}
    @Test
    @WithMockUser(roles = { "Dealer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_registerDealerWithAlreadyExistingUser() throws Exception {
        when(userRepoMock.findByUsername("testuser")).thenReturn(optUser);
        mockMvc.perform(post("/registerDealer")
        		.param("firstName", "testuser")
        		.param("lastName", "ggg")
        		.param("username", "fff")
        		.param("phoneNum", "jfhf"))
                .andExpect(view().name("registerDealer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "The user with username testuser already exists"));
    }
    
    @Test
    @WithMockUser(roles = { "Dealer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_registerDealer() throws Exception {
        when(userRepoMock.findByUsername("testuser")).thenReturn(optEmptyUser);
        when(roleservicemock.findByRoleName("Dealer")).thenReturn(DealerRole);

        mockMvc.perform(post("/registerDealer")
        		.param("firstName", "testuser")
        		.param("lastName", "ggg")
        		.param("username", "fff")
        		.param("phoneNum", "jfhf"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
            
    }
    @Test
    @WithMockUser(roles = { "Dealer" }, username = "testuser"/*, value = "george.lucas@fdmgroup.com", password = "123"*/)
    void test_registerCustomer() throws Exception {
        when(userRepoMock.findByUsername("testuser")).thenReturn(optEmptyUser);
        when(roleservicemock.findByRoleName("Customer")).thenReturn(CustomerRole);

        mockMvc.perform(post("/registerCustomer")
        		.param("firstName", "testuser")
        		.param("lastName", "ggg")
        		.param("username", "fff")
        		.param("phoneNum", "jfhf"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
            
    }
    @Test
    @WithMockUser(roles = { "Dealer" })
    void test_DealerViewAllCars() throws Exception {
        when(carservicemock.findAllCars()).thenReturn(carAdList);   
       mockMvc.perform(get("/DealerViewAllCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("DealerIndex"))
                .andExpect(model().attribute("Cars", carAdList));
    }
    @Test
    @WithMockUser(roles = { "Cusomer" })
    void test_CustomerViewAllCars() throws Exception {
        when(carservicemock.findAllCars()).thenReturn(carAdList);
       mockMvc.perform(get("/CustomerViewAllCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("Cars", carAdList));
    }
	
}

    	
    
    
    



