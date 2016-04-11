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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.users.model.Account;
import com.users.model.Bills;
import com.users.model.Client;
import com.users.model.TransferObject;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.AccountService;
import com.users.service.BillsService;
import com.users.service.ClientService;
import com.users.service.UserRoleService;
import com.users.service.UserService;

@Controller
// @SessionAttributes({"msg"})
public class MainController {

	@Autowired
	protected UserService userService;

	@Autowired
	protected AccountService accountService;

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected UserRoleService userRoleService;

	@Autowired
	protected BillsService billsService;

	// private static Logger log =
	// Logger.getLogger(MainController.class.getName());

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

		return "redirect:/sucCreated";
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
	public ModelAndView accountC1(Account a1, @PathVariable("id") Integer clientId) {
		ModelAndView sucCreated = new ModelAndView();

		Client c = clientService.findById(clientId);
		a1.setClient(c);
		// c.getAcc().add(a1);
		// clientService.insert(c);
		accountService.insert(a1);
		// log.info("INFO: Account was created");
		sucCreated.setViewName("redirect:/client/account/" + clientId);
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

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public ModelAndView createClient(Client c1) {
		ModelAndView sucCreated = new ModelAndView();

		if (c1.getCnp().length() != 13)
			sucCreated.addObject("error", "CNP MUST BE OF LENGTH 13");

		clientService.insert(c1);
		// log.info("INFO: Client was created");
		sucCreated.setViewName("redirect:/clientlist");
		return sucCreated;
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

	@RequestMapping(value = "/clientlist", method = RequestMethod.GET)
	public ModelAndView clist() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Bank Application");
		forSignUp.addObject("message", "Create new bank account");
		forSignUp.addObject("msg", " ");
		List<Client> c1 = clientService.findAll();
		forSignUp.addObject("clients", c1);
		forSignUp.setViewName("clientlist");
		return forSignUp;
	}

	@RequestMapping(value = "/client/delete/{id}", method = RequestMethod.GET)
	public String deleteClient(@PathVariable("id") Integer id) {
		clientService.deleteById(id);
		// log.info("INFO: Client was deleted");
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

	@Transactional
	@RequestMapping(value = "/client/account/delete/{id}", method = RequestMethod.GET)
	public String deleteAccount(@PathVariable("id") Integer id) {
		Account acc = accountService.findById(id);
		Client c = clientService.findById(acc.getClient().getId());
		List<Account> accs = new ArrayList<Account>();
		for (Account account : c.getAcc()) {
			if (account.getId() != acc.getId()) {
				accs.add(account);
			}
		}
		c.setAcc(accs);
		clientService.insert(c);
		accountService.delete(acc);
		// log.info("INFO: Account was deleted");
		// c.getAcc().remove(acc);
		// clientService.insert(c);
		return "redirect:/client/account/" + c.getId();
	}

	@Transactional
	@RequestMapping(value = "/client/account/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editAccount(@PathVariable("id") Integer id) {
		Account acc = accountService.findById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("account", acc);
		model.setViewName("account");
		return model;
	}
	///////////////// admin////////////////

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView usersList() {
		ModelAndView forSignUp = new ModelAndView();
		List<User> user = userService.findAll();
		forSignUp.addObject("users", user);
		forSignUp.setViewName("admin");
		return forSignUp;
	}

	@Transactional
	@RequestMapping(value = "/admin/edituser/{username}", method = RequestMethod.GET)
	public ModelAndView editUSER(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("edituser");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST)
	public ModelAndView editUSER2(User user) {

		User us = userService.findByUsername(user.getUsername());
		us.setFirstName(user.getFirstName());
		us.setLastName(user.getLastName());

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/admin/delete/{username}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("username") String username) {

		User user = userService.findByUsername(username);
		userService.deleteUser(user);
		return "redirect:/admin";

	}

	@RequestMapping(value = "/client/account/transfer/{id}", method = RequestMethod.GET)
	public ModelAndView transferAmount(@PathVariable("id") Integer id) {
		ModelAndView transfer = new ModelAndView();
		Account acc = accountService.findById(id);
		TransferObject obj = new TransferObject();

		obj.setAmount(0.0);
		obj.setDestAccountNo("");
		obj.setSrcAccountNo(acc.getAccountNo());
		obj.setClientId(acc.getClient().getId());
		transfer.addObject("transfer", obj);
		transfer.setViewName("transferPage");

		return transfer;
	}

	@Transactional
	@RequestMapping(value = "/client/account/transfer", method = RequestMethod.POST)
	public ModelAndView transferAmount2(TransferObject obj) {

		ModelAndView transfer = new ModelAndView();
		transfer.setViewName("redirect:/client/account/" + obj.getClientId());
		Account srcAcc = accountService.findByAccountNo(obj.getSrcAccountNo());
		Account destAcc = accountService.findByAccountNo(obj.getDestAccountNo());
		if ((srcAcc == null) || (destAcc == null)) {
			transfer.addObject("msg", "Error: One of the accounts no. not good.!");
			return transfer;
		}
		if ((srcAcc.getAmount() >= obj.getAmount()) && (obj.getAmount() > 0)) {
			srcAcc.withdraw(obj.getAmount());
			destAcc.deposit(obj.getAmount());
			transfer.addObject("msg", "Transfer was succesfull");
			return transfer;
		} else {
			transfer.addObject("msg", "Error: Wrong amount");
			return transfer;
		}
	}

	@Transactional
	@RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
	public ModelAndView bill(@PathVariable("id") Integer id) {
		ModelAndView forBill = new ModelAndView();
		forBill.addObject("title", "Bank Application");
		forBill.addObject("message", "Pay your bills");

	//	Client c = clientService.findById(id);
		List<Bills> bill = billsService.findByClientId(id);

		//if (c.getId() == bill.getClientId())
			// bill.setClient(c);
			forBill.addObject("bills", bill);
		forBill.setViewName("bills");
		return forBill;
	}
	
	@Transactional
	@RequestMapping(value = "/paybill/{id}/{clientid}/{billCost}", method = RequestMethod.GET)
	public String paybill(@PathVariable("id") Integer id,@PathVariable("clientid") Integer clientid, @PathVariable("billCost") Double billCost) {
		Client c=clientService.findById(clientid);
		List<Account> accs=new ArrayList<Account>();
		
		for (Account account : c.getAcc()) {
			if(account.getAmount()>billCost){
				account.setAmount(account.getAmount()-billCost); break;
			}
			else return "redirect:/insuf";
		}
		Bills bill=billsService.findById(id);
		int a=clientid;
		billsService.deleteBill(bill);
		
	
		return "redirect:/bills/"+a;
	}
}
