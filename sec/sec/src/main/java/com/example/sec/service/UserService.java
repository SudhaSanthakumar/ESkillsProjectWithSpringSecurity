package com.example.sec.service;


import com.example.sec.model.Message;
import com.example.sec.model.User;
import com.example.sec.repository.MessageRepository;
import com.example.sec.repository.RoleRepository;
import com.example.sec.repository.UserRepository;
import com.example.sec.model.Role;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Autowired
	private MessageRepository msgRepo;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;


	public User findByUserName(String userName, String password) {
		
		User user=userRepo.findByUserName(userName);
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	

	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}


	public Optional<User> findUserById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id);
	}


	public void saveMessage(int id, String message) {
		Optional<User> u= userRepo.findById(id);
		System.out.println("list size before+               "+u.get().getMessages().size());
		Message mm=new Message(message);
		mm.setUser(u.get());
		u.get().getMessages().add(mm);
		
		userRepo.saveAndFlush(u.get());
		Optional<User> u1= userRepo.findById(id);
		Set<Message> m=u1.get().getMessages();
		System.out.println("list size after+               "+m.size());
		
	}


	public User saveUser(String userName, String password, String confirmPassword) {
		// TODO Auto-generated method stub
		Set<Role> roles=new HashSet<Role>();
		roles.add(roleRepo.findByRoleName("USER"));
		User u=new User(userName,password,confirmPassword,roles);
		User reg=userRepo.saveAndFlush(u);
		
		return reg ;
	}


	public User saveUser(String userName, String password, String confirmPassword, String roleName) {
		// TODO Auto-generated method stub
		Set<Role> roles=new HashSet<Role>();
		roles.add(roleRepo.findByRoleName(roleName.toUpperCase()));
		User u=new User(userName,password,confirmPassword,roles);
		User reg=userRepo.saveAndFlush(u);
		return reg;
		
	}



	public Optional<Message> findMessageById(int id) {
		// TODO Auto-generated method stub
		return msgRepo.findById(id);
	}



	public void autoLogin(String userName, String password) {
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
		
		authenticationManager.authenticate(token);
		
		if(token.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
				
	}



	
	
	
}
