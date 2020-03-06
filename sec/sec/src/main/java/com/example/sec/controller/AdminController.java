package com.example.sec.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.sec.model.Page;
import com.example.sec.model.Role;
import com.example.sec.model.User;
import com.example.sec.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
@SessionAttributes({"user","user_role","user_pages"})
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/admin/home","/admin/Home"})
	public String showAdminHome() {
		return "adminHome";
	}
	@RequestMapping({"/admin/About","admin/about"})
	public String showAbout(ModelMap model) {
		Object user=model.getAttribute("user");
		if(user instanceof User) {
			User u=(User)user;
			return "redirect:showUser/"+u.getUserId();
		}
		return "pageUnderConstruction";
	}

	
	@RequestMapping("/admin/User")
	public String userLogout(ModelMap model) {
		
		List<User> users=userService.findAllUsers();
		model.addAttribute("usersList",users);
		return "usersPage";
	}
	
	@RequestMapping("/admin/addUser")
	public String addUserPage(ModelMap model) {
		
		model.addAttribute("newUser", new User());
		return "showRegistrationForm";
	}
	
	
	
	@RequestMapping("/admin/showUser/{id}")
	public String userLogout(ModelMap model, @PathVariable ("id") int id) {
		System.out.println("id "+id);
		Optional<User> user=userService.findUserById(id);
		System.out.println("user "+user.get().getUserName()	);
		model.addAttribute("retrievedUser",user.get());
		
		model.addAttribute("retrievedUserRoles",user.get().getRole());
		model.addAttribute("retrievedUserMessages",user.get().getMessages());
		return "userProfile";
	}
	
	@RequestMapping (value = "/addNewUser", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest req, HttpServletResponse res, ModelMap model, HttpSession session,@Valid @ModelAttribute("newUser") User user, BindingResult result) {
		System.out.println("un in post "+req.getParameter("userName"));
		System.out.println("pw in post "+req.getParameter("password"));
		
		if(result.hasErrors()) {
			return "showRegistrationForm";
		}
		User u=userService.saveUser(req.getParameter("userName"),req.getParameter("password"), req.getParameter("confirmPassword"), req.getParameter("role"));
		System.out.println("user ++++++++++++++++"+u.getUserName()+"   "+u.getRole());
		
		return "redirect:admin/User";
	}

}
