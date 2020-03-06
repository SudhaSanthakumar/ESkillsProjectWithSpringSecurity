package com.example.sec.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.sec.model.Message;
import com.example.sec.model.Page;
import com.example.sec.model.User;
import com.example.sec.service.UserService;

@Controller
@SessionAttributes({"user","user_role","user_pages"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/user/home","/user/Home"})
	public String showUserHome(ModelMap model) {
		model.addAttribute("func", "add");
		
		return "userHome";
	}
	
	@RequestMapping({"/user/About","user/about"})
	public String showAbout(ModelMap model) {
		
		Object user=model.getAttribute("user");
		if(user instanceof User) {
			User u=(User)user;
			return "redirect:showUser/"+u.getUserId();
		}
		return "pageUnderConstruction";
	}
	



	@RequestMapping("/user/Message")
	public String userMessage(ModelMap model) {
		User u=((User)model.getAttribute("user"));
		System.out.println("user looged in       "+((User)model.getAttribute("user")).getUserName());
		List<Message> messages=new ArrayList<Message>();
		messages.addAll(userService.findUserById(u.getUserId()).get().getMessages());
		model.addAttribute("user_messages", messages);
		return "message";
	}
	
	@RequestMapping("/user/{msgId}/editMessage")
	public String editMessage(ModelMap model, @PathVariable("msgId") int id, HttpServletRequest request) {
		
		model.addAttribute("func", "edit");
		model.addAttribute("message",userService.findMessageById(id).get());
		
		return "redirect:home";
	}

	@RequestMapping("/user/{id}/addMessage")
	public String userMessage(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
		User u=((User)model.getAttribute("user"));
		System.out.println("user looged in       "+((User)model.getAttribute("user")).getUserName());
		userService.saveMessage(id, request.getParameter("message"));
		List<Message> messages=new ArrayList<Message>();
		messages.addAll(userService.findUserById(id).get().getMessages());
		model.addAttribute("user_messages", messages);
		return "message";
	}
	
	@RequestMapping("/user/showUser/{id}")
	public String userProfile(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
		System.out.println("id "+id);
		Optional<User> user=userService.findUserById(id);
		System.out.println("user "+user.get().getUserName()	);
		model.addAttribute("retrievedUser",user.get());
		
		model.addAttribute("retrievedUserRoles",user.get().getRole());
		model.addAttribute("retrievedUserMessages",user.get().getMessages());
		return "userProfile";
	}

	
}
