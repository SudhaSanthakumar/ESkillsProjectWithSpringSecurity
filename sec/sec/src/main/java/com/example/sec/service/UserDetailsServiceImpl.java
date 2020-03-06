package com.example.sec.service;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.sec.model.User;
import com.example.sec.model.UserDetailsImpl;
import com.example.sec.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		// TODO Auto-generated method stub
		System.out.println("username in user details service impl"+username);
		User user=userRepo.findByUserName(username);
		
		return new UserDetailsImpl(user);
	}

}
