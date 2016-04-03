package com.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.UserService;

@Controller
public class MainController {

	@Autowired
	protected UserService userService;
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Bank Application");
		model.addObject("message", "Main Page");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Bank Application");
		model.addObject("message", "Admin page");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value="/signUp",method=RequestMethod.GET)
	
	public ModelAndView signUp()
	{
		ModelAndView forSignUp= new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new user account");
		User newUser = new User();
		forSignUp.addObject("user", newUser);
		forSignUp.setViewName("signUp");
		return forSignUp;
		
	}

	@RequestMapping(value="/signUp",method=RequestMethod.POST)
	public String signUp(User u1)
	{	Set<UserRole> roleSet=new HashSet<UserRole>(0);
		UserRole uR=new UserRole(u1,"ROLE_USER");
		roleSet.add(uR);
		u1.setUserRole(roleSet);
		u1.setEnabled(true);
		System.out.println(u1.getUsername());
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hash = encoder.encode(u1.getPassword());
		u1.setPassword(hash);
		userService.insert(u1);
		return "redirect:/hello";
	} 
	

	
	
	

}