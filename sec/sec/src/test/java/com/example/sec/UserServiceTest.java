package com.example.sec;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.sec.model.User;
import com.example.sec.repository.RoleRepository;
import com.example.sec.repository.UserRepository;
import com.example.sec.service.UserService;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	
	
	@Mock
	RoleRepository rRepo;
	
	@Mock
	UserRepository uRepo;
	
	@InjectMocks
	UserService uService;
	
	@Test
	public void saveUserTest() {
		
		
		User u=new User();
		u.setUserId(1);
		
		Mockito.when(rRepo.findByRoleName(Mockito.anyString())).thenReturn(null);
		
		Mockito.when(uRepo.saveAndFlush(Mockito.any(User.class))).thenReturn(u);
		
		Assert.assertEquals(u, uService.saveUser("Sam","aaaa","aaaa"));
	}
}
