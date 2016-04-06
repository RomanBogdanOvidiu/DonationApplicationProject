package com.web.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.users.model.Account;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.AccountService;
import com.users.service.ClientService;
import com.users.service.UserService;

import com.users.model.Client;

@Controller
public class MainController {

	@Autowired
	protected UserService userService;

	@Autowired
	protected AccountService accountService;

	@Autowired
	protected ClientService clientService;

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

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)

	public ModelAndView signUp() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
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

		return "redirect:/BankA1/hello";
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ModelAndView accountC(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new bank account");
		Client client = clientService.findById(id);

		Account acc1 = new Account();

		acc1.setClient(client);

		forSignUp.addObject("account", acc1);
		forSignUp.setViewName("account");
		return forSignUp;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public ModelAndView accountC1(Account a1, @PathVariable ("id") Integer clientId) {
		ModelAndView sucCreated = new ModelAndView();
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$4" + a1.getClient().getId());
		
		Client c = clientService.findById(clientId);
		a1.setClient(c);
		c.getAcc().add(a1);
		clientService.insert(c);
		accountService.insert(a1);
		sucCreated.setViewName("redirect:/client/account/");
		return sucCreated;
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public ModelAndView createClient() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new bank account");
		Client c1 = new Client();

		forSignUp.addObject("client", c1);
		forSignUp.setViewName("client");
		return forSignUp;
	}

	@RequestMapping(value = "/client/edit/{id}", method = RequestMethod.GET)
	public ModelAndView clientEdit(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new bank account");
		Client c1 = clientService.findById(id);

		forSignUp.addObject("client", c1);
		forSignUp.setViewName("client");
		return forSignUp;
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public ModelAndView createClient(Client c1) {
		ModelAndView sucCreated = new ModelAndView();
		System.out.println(
				c1.getFirstName() + " " + c1.getAddress() + " " + c1.getId() + " " + c1.getCnp() + c1.getLastName());
		clientService.insert(c1);
		sucCreated.setViewName("redirect:/clientlist");
		return sucCreated;
	}

	@RequestMapping(value = "/clientlist", method = RequestMethod.GET)
	public ModelAndView clist() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new bank account");
		List<Client> c1 = clientService.findAll();
		forSignUp.addObject("clients", c1);
		forSignUp.setViewName("clientlist");
		return forSignUp;
	}

	@RequestMapping(value = "/client/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id) {
		clientService.deleteById(id);

		return "redirect:/clientlist";
	}

	@RequestMapping(value = "/client/account/{id}", method = RequestMethod.GET)
	public ModelAndView connectAccClient(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Manage Bank Account ");
		Client client = clientService.findById(id);
		List<Account> acc = accountService.findByClient(client);
		forSignUp.addObject("accounts", acc);
		forSignUp.addObject("client", id);
		forSignUp.setViewName("clientaccount");

		return forSignUp;

	}
	@RequestMapping(value = "/clientaccount/delete/{id}", method = RequestMethod.GET)
	public String deleteAccount(@PathVariable("id") Integer id) {
		accountService.deleteById(id);

		return "redirect:/clientaccount";
	}
}
