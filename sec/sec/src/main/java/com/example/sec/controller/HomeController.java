package com.example.sec.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.PagesPerMinute;
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

import com.example.sec.model.Page;
import com.example.sec.model.Role;
import com.example.sec.model.User;
import com.example.sec.model.UserDetailsImpl;
import com.example.sec.service.UserService;
import com.example.sec.service.UserValidator;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import com.example.demo.model.Message;
//import com.example.demo.model.Page;
//import com.example.demo.model.Role;
//import com.example.demo.model.User;
//import com.example.demo.service.UserService;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@SessionAttributes({"user_role","user","user_pages"})
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private UserValidator userValidator;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String showHome(ModelMap model, HttpSession session) {
		String roleOfUser="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails u=(UserDetails)principal;
		  String username = ((UserDetails)principal).getUsername();
		  System.out.println("user inside home mapping istance of principal  "+u);
		 
		  List<GrantedAuthority> roles=(List<GrantedAuthority>) u.getAuthorities();
		  
		  User cUser=userService.findByUserName(u.getUsername(), u.getPassword());
		  List<Role> cUserRoles=new ArrayList<Role>();
		  cUserRoles.addAll(cUser.getRole());
		  List<Page> pages=new ArrayList<Page>();
		  pages.addAll(cUserRoles.get(0).getPages());
		  roleOfUser=cUserRoles.get(0).getRoleName().toLowerCase();
		  model.addAttribute("user",cUser);;
		  model.addAttribute("user_role", cUserRoles.get(0).getRoleName().toLowerCase());
		  model.addAttribute("user_pages", pages);
		  updateSession(cUser,cUserRoles.get(0).getRoleName().toLowerCase(),pages, session);
//		  model.addAttribute("user",userService.findByUserName(u.getUsername(), u.getPassword()));;
		} else {
		  String username = principal.toString();
		  model.addAttribute("user",username);;
		  model.addAttribute("user_role", null);
		  model.addAttribute("user_pages", Arrays.asList(new Page("Home"),new Page("About")));
		  System.out.println("user inside home mapping not an istance of principal  "+username);
		}
		
		
		System.out.println("user inside home mapping "+SecurityContextHolder.getContext().getAuthentication().getName());
		return  "home";
	}
private void updateSession(User user, String userRole, List<Page> pages, HttpSession session) {
		
		session.setAttribute("user",user);
		session.setAttribute("user_role", userRole);
		session.setAttribute("user_pages", pages);
	}
//	@RequestMapping("/login")
//	public String login(ModelMap model) {
//		model.addAttribute("user",SecurityContextHolder.getContext().getAuthentication().getName());
//		model.addAttribute("user_role", null);
//		model.addAttribute("user_pages", Arrays.asList(new Page("Home"),new Page("About")));
//		System.out.println("user inside login mapping "+SecurityContextHolder.getContext().getAuthentication().getName());
//		return "login";
//	}
	
	@RequestMapping("/showRegistrationForm")
	public String showRegistrationForm(ModelMap model) {
		model.addAttribute("newUser", new User());
		return "showRegistrationForm";
	}
	
	
	@RequestMapping (value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest req, HttpServletResponse res, ModelMap model, HttpSession session,@Valid @ModelAttribute("newUser") User newUser, BindingResult result) {
		
		userValidator.validate(newUser, result);

		if(result.hasErrors()) {
			return "showRegistrationForm";
		}
		User cUser=userService.saveUser(req.getParameter("userName"),req.getParameter("password"), req.getParameter("confirmPassword"), "USER");
		System.out.println("user ++++++++++++++++"+cUser.getUserName()+"   "+cUser.getRole());
		
		userService.autoLogin(req.getParameter("userName"),req.getParameter("password"));
		 List<Role> cUserRoles=new ArrayList<Role>();
		  cUserRoles.addAll(cUser.getRole());
		  List<Page> pages=new ArrayList<Page>();
		  pages.addAll(cUserRoles.get(0).getPages());
		  String roleOfUser = cUserRoles.get(0).getRoleName().toLowerCase();
		  model.addAttribute("user",cUser);;
		  model.addAttribute("user_role", cUserRoles.get(0).getRoleName().toLowerCase());
		  model.addAttribute("user_pages", pages);
		  updateSession(cUser,cUserRoles.get(0).getRoleName().toLowerCase(),pages, session);
		return "home";
	}
	
	@RequestMapping({"/About","/about"})
	public String showAbout() {
		return "pageUnderConstruction";
	}
	
	
}
