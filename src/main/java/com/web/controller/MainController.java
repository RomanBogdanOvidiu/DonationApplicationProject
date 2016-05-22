package com.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.users.model.Donation;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.DonationService;
import com.users.service.UserRoleService;
import com.users.service.UserService;

@Controller
// @SessionAttributes({"msg"})
public class MainController {

	@Autowired
	protected UserService userService;

	@Autowired
	protected UserRoleService userRoleService;

	@Autowired
	protected DonationService donationService;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Hospital Application");
		model.addObject("message", "Main Page");
		model.setViewName("hello");
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
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)

	public ModelAndView signUp() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("message", "Create new user account");
		User newUser = new User();
		forSignUp.addObject("user", newUser);
		forSignUp.setViewName("signUp");
		return forSignUp;

	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(User u1) {
		Set<UserRole> roleSet = new HashSet<UserRole>(0);
		UserRole uR = new UserRole(u1, "ROLE_USER");
		roleSet.add(uR);

		u1.setUserRole(roleSet);
		u1.setEnabled(true);

		// System.out.println(u1.getUsername());
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hash = encoder.encode(u1.getPassword());
		u1.setPassword(hash);

		userService.insert(u1);
		userService.insert2(uR);

		return "redirect:/sucCreated";
	}

	/////////////////////// MESSAGES///////////////////////////////

	@Transactional
	@RequestMapping(value = "/sucCreated", method = RequestMethod.GET)
	public String succesfullOperation() {
		return "/sucCreated";

	}

	@Transactional
	@RequestMapping(value = "/insuf", method = RequestMethod.GET)
	public String moneyLess() {
		return "/insuf";

	}

	@RequestMapping(value = { "/check" }, method = RequestMethod.GET)
	public @ResponseBody String checkConsultations() {
		String data = "";
		// List<Consultation> consultations = consultationService.findAll();
		// for (Consultation c : consultations) {
		// if (c.isChecked() == false) {
		// data = "Patient " + data + c.getClient().getFirstName() + " " +
		// c.getClient().getLastName()
		// + " is waiting ";
		//
		// consultationService.save(c);
		// }
		// }
		return data;
	}

	@Transactional
	@RequestMapping(value = "/wrong", method = RequestMethod.GET)
	public String wrong() {
		return "/wrong";

	}

	///////////// DONATIONS CONSTRU
	@RequestMapping(value = "/donation/{username}", method = RequestMethod.GET)
	public ModelAndView createDonationGet(@PathVariable("username") String username) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Donation Application");
		forSignUp.addObject("message", "Add new donation");
		User user = userService.findByUsername(username);

		Donation donation = new Donation();

		donation.setUser(user);

		forSignUp.addObject("donation", donation);
		forSignUp.setViewName("donation");
		return forSignUp;
	}

	@RequestMapping(value = "/donation/{username}", method = RequestMethod.POST)
	public ModelAndView createDonationPost(Donation donation, @PathVariable("username") String username) {
		ModelAndView sucCreated = new ModelAndView();

		User u = userService.findByUsername(username);
		donation.setUser(u);
		donationService.insert(donation);
		sucCreated.setViewName("redirect:/user/donation/");
		return sucCreated;
	}

	@RequestMapping(value = "/user/donation", method = RequestMethod.GET)
	public ModelAndView connectDonationToUser() {
		ModelAndView forSignUp = new ModelAndView();

		List<Donation> donation = donationService.findAll();
		forSignUp.addObject("donations", donation);
		forSignUp.setViewName("donationsList");

		return forSignUp;

	}
	
	@RequestMapping(value = "/user/donation/{username}", method = RequestMethod.GET)
	public ModelAndView connectDonationToUser(@PathVariable("username") String username) {
		ModelAndView forSignUp = new ModelAndView();
		User user = userService.findByUsername(username);
		List<Donation> donation = donationService.findByUser(user);
		forSignUp.addObject("donationes", donation);
		forSignUp.setViewName("personalDonation");

		return forSignUp;

	}

	

	@Transactional
	@RequestMapping(value = "/user/donation/delete/{id}", method = RequestMethod.GET)
	public String deleteDonation(@PathVariable("id") Integer id) {
		
		Donation don = donationService.findByDonationsId(id);
		User user = userService.findByUsername(don.getUser().getUsername());
		List<Donation> donations = new ArrayList<Donation>();
		for (Donation donation : user.getDonation()) {
			if (donation.getDonationsId() != don.getDonationsId()) {
				donations.add(donation);
			}
		}
		user.setDonation(donations);
		userService.insert(user);
		donationService.delete(don);
	
		return "redirect:/user/donation/" + user.getUsername();
	}
	@Transactional
	@RequestMapping(value = "/user/donation/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editAccount(@PathVariable("id") Integer id) {
		Donation don = donationService.findByDonationsId(id);
	
		ModelAndView model = new ModelAndView();
		model.addObject("donation", don);
		model.setViewName("donation");
		return model;
	}
	@Transactional
	@RequestMapping(value = "/user/donation/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewDonation(@PathVariable("id") Integer id) {

		ModelAndView model = new ModelAndView();
		Donation don=donationService.findByDonationsId(id);
		
		model.addObject("donation", don);
		model.setViewName("donationDetails");
		return model;
	}

}
