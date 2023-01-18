package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CarSiteSoloProject.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

	@MockBean
	private RoleRepository roleRepository;
	@InjectMocks
	private RoleService servicemock;

	@Test
	void test_findRoleByName() {
		servicemock.findByRoleName("someName");
		verify(roleRepository, times(1)).findByRoleName("someName");
	}
}
