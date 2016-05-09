//package com.web.controller;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.users.model.User;
//import com.users.model.UserRole;
//import com.users.service.UserService;
//
//@Controller
//@RequestMapping("admin")
//public class AdminController {
//	@Autowired
//	UserService userService;
//	///////////////// admin///////////////////////
//	@RequestMapping(value = "**", method = RequestMethod.GET)
//	public ModelAndView adminPage() {
//
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Bank Application");
//		model.addObject("message", "Admin page");
//		model.setViewName("admin");
//
//		return model;
//
//	}
//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public ModelAndView usersList() {
//		ModelAndView forSignUp = new ModelAndView();
//		List<User> user = userService.findAll();
//		forSignUp.addObject("users", user);
//		forSignUp.setViewName("admin");
//		return forSignUp;
//	}
//
//	@Transactional
//	@RequestMapping(value = "/edituser/{username}", method = RequestMethod.GET)
//	public ModelAndView editUSER(@PathVariable("username") String username) {
//		User user = userService.findByUsername(username);
//		ModelAndView model = new ModelAndView();
//		model.addObject("user", user);
//		model.setViewName("edituser");
//		return model;
//	}
//
//	@Transactional
//	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
//	public ModelAndView editUSER2(User user) {
//
//		User us = userService.findByUsername(user.getUsername());
//		us.setFirstName(user.getFirstName());
//		us.setLastName(user.getLastName());
//
//		ModelAndView model = new ModelAndView();
//		model.setViewName("redirect:/admin");
//		return model;
//	}
//
//	@Transactional
//	@RequestMapping(value = "/admin/delete/{username}", method = RequestMethod.GET)
//	public String deleteUser(@PathVariable("username") String username) {
//
//		User user = userService.findByUsername(username);
//		userService.deleteUser(user);
//		return "redirect:/admin";
//
//	}
//
//	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
//
//	public ModelAndView signUp() {
//		ModelAndView forSignUp = new ModelAndView();
//		forSignUp.addObject("title", "Bank Application");
//		forSignUp.addObject("message", "Create new user account");
//		User newUser = new User();
//		forSignUp.addObject("user", newUser);
//		forSignUp.setViewName("signUp");
//		return forSignUp;
//
//	}
//
//	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
//	public String signUp(User u1) {
//		Set<UserRole> roleSet = new HashSet<UserRole>(0);
//		UserRole uR = new UserRole(u1, "ROLE_USER");
//		roleSet.add(uR);
//
//		u1.setUserRole(roleSet);
//		u1.setEnabled(true);
//
//		// System.out.println(u1.getUsername());
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		String hash = encoder.encode(u1.getPassword());
//		u1.setPassword(hash);
//
//		userService.insert(u1);
//		userService.insert2(uR);
//
//		return "redirect:/sucCreated";
//	}
//
//}
