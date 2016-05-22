package com.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	UserService userService;
	///////////////// admin///////////////////////
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Donations Application");
		model.addObject("message", "Admin page");
		model.setViewName("admin");

		return model;

	}
	@RequestMapping(value = "/adm", method = RequestMethod.GET)
	public ModelAndView usersList() {
		ModelAndView forSignUp = new ModelAndView();
		List<User> user = userService.findAll();
		forSignUp.addObject("users", user);
		forSignUp.setViewName("admin");
		return forSignUp;
	}

	@Transactional
	@RequestMapping(value = "/edituser/{username}", method = RequestMethod.GET)
	public ModelAndView editUSER(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("edituser");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	public ModelAndView editUSER2(User user) {

		User us = userService.findByUsername(user.getUsername());
		us.setFirstName(user.getFirstName());
		us.setLastName(user.getLastName());
		us.setEmail(user.getEmail());

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/adm");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("username") String username) {

		User user = userService.findByUsername(username);
		userService.deleteUser(user);
		return "redirect:/admin/adm";

	}

	

}
