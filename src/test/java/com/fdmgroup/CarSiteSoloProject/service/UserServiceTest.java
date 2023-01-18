package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CarSiteSoloProject.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	   @MockBean
	    private UserRepository userRepository;
	    @InjectMocks
	    private UserService userService;

	    @Test
	    void test_findByUsername() {
	        userService.findByUsername(anyString());
	        verify(userRepository, times(1)).findByUsername(anyString());
	    }

	    @Test
	    void test_saveUser() {
	        userService.saveUser(any());
	        verify(userRepository, times(1)).save(any());
	    }
}
