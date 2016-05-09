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

import com.users.model.Consultation;
import com.users.model.Notification;
import com.users.model.Patient;
import com.users.model.Report;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.ConsultationService;
import com.users.service.NotificationService;
import com.users.service.PatientService;
import com.users.service.ReportService;
import com.users.service.UserRoleService;
import com.users.service.UserService;

@Controller
// @SessionAttributes({"msg"})
public class MainController {

	@Autowired
	protected NotificationService notificationService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected ConsultationService consultationService;

	@Autowired
	protected PatientService patientService;

	@Autowired
	protected UserRoleService userRoleService;

	@Autowired
	protected ReportService reportService;

	// private static Logger log =
	// Logger.getLogger(MainController.class.getName());

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
	// ///////////////// admin///////////////////////

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Hospital Application");
		model.addObject("message", "Admin page");
		model.setViewName("admin");

		return model;

	}

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

		ModelAndView model = new ModelAndView();
		User user = userService.findByUsername(username);

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
		if (us.getBadRole().trim().equals("") && us.getFirstName().trim().equals("")
				&& us.getLastName().trim().equals("") && us.getPassword().trim().equals("")
				&& us.getUsername().trim().equals("")) {
			model.setViewName("redirect:/wrong");
		}
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

	@RequestMapping(value = "/admin/signUp", method = RequestMethod.GET)

	public ModelAndView signUp() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("message", "Create new user account");
		User newUser = new User();
		forSignUp.addObject("user", newUser);
		forSignUp.setViewName("signUp");
		return forSignUp;

	}

	@RequestMapping(value = "/admin/signUp", method = RequestMethod.POST)
	public String signUp(User u1) {
		if (u1.getFirstName().trim() != "" && u1.getLastName().trim() != "" && u1.getUsername().trim() != ""
				&& u1.getPassword().trim() != "" && u1.getBadRole().trim() != "") {

			Set<UserRole> roleSet = new HashSet<UserRole>(0);
			UserRole uR;
			if (u1.getBadRole().equals("ROLE_SECRETARY")) {
				uR = new UserRole(u1, "ROLE_SECRETARY");
				roleSet.add(uR);
			} else if (u1.getBadRole().equals("ROLE_DOCTOR")) {
				uR = new UserRole(u1, "ROLE_DOCTOR");
				roleSet.add(uR);
			} else
				return "redirect:/wrong";

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
		return "redirect:/wrong";
	}

	/////////////////////////// END OF ADMIN ACCTIONS//////////////

	///////////////////////////////// CONSULTATIONS ///////////////////
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ModelAndView accountC(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("message", "Create new hospital consultation");
		Patient client = patientService.findById(id);

		Consultation acc1 = new Consultation();

		acc1.setClient(client);

		forSignUp.addObject("account", acc1);
		forSignUp.setViewName("account");
		return forSignUp;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public ModelAndView accountC1(Consultation a1, @PathVariable("id") Integer clientId) {
		ModelAndView sucCreated = new ModelAndView();

		Patient c = patientService.findById(clientId);
		if (a1.getConsultationDetails().trim().equals("") || a1.getProgramare().trim().equals("")) {
			sucCreated.setViewName("redirect:/wrong");
			return sucCreated;
		}
		a1.setClient(c);
		consultationService.insert(a1);
		sucCreated.setViewName("redirect:/client/account/" + clientId);
		return sucCreated;
	}

	@RequestMapping(value = "/client/account/{id}", method = RequestMethod.GET)
	public ModelAndView connectAccClient(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("message", "Manage Hospital Consultation ");
		Patient client = patientService.findById(id);
		List<Consultation> acc = consultationService.findByClient(client);
		forSignUp.addObject("accounts", acc);
		forSignUp.addObject("client", id);
		forSignUp.setViewName("clientaccount");

		return forSignUp;

	}

	@Transactional
	@RequestMapping(value = "/client/account/delete/{id}", method = RequestMethod.GET)
	public String deleteAccount(@PathVariable("id") Integer id) {
		Report report = new Report();
		org.springframework.security.core.userdetails.User loggedIn = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		report.setMessage("Patient account was deleted by: " + loggedIn.getUsername());
		reportService.save(report);
		Consultation acc = consultationService.findById(id);
		Patient c = patientService.findById(acc.getClient().getId());
		List<Consultation> accs = new ArrayList<Consultation>();
		for (Consultation account : c.getAcc()) {
			if (account.getId() != acc.getId()) {
				accs.add(account);
			}
		}
		c.setAcc(accs);
		patientService.insert(c);
		consultationService.delete(acc);
		// log.info("INFO: Account was deleted");
		// c.getAcc().remove(acc);
		// clientService.insert(c);
		return "redirect:/client/account/" + c.getId();
	}

	@Transactional
	@RequestMapping(value = "/client/account/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editAccount(@PathVariable("id") Integer id) {
		Consultation acc = consultationService.findById(id);

		ModelAndView model = new ModelAndView();
		if (acc.getConsultationDetails().trim().equals("") || acc.getProgramare().trim().equals("")) {

			model.setViewName("redirect:/wrong");
			return model;
		}

		model.addObject("account", acc);
		model.setViewName("account");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/client/account/notifyDoctor/{id}", method = RequestMethod.GET)
	public String notifyDoctor(@PathVariable("id") Integer id) {
		Consultation acc = consultationService.findById(id);
		List<User> users = userService.findAll();
		Notification notification = new Notification();
		for (User u : users) {
			if (u.getBadRole().equals("ROLE_DOCTOR")) {
				notification.setMessage(
						"The Patient: " + acc.getClient().getFirstName() + " " + acc.getClient().getLastName()
								+ " with the consultation ID : " + acc.getId() + " needs checking by a doctor");
				notification.setPatientId(acc.getId());
				notificationService.save(notification);
			}
		}

		return "redirect:/sucCreated";
	}

	@RequestMapping(value = "/client/notification/delete/{id}", method = RequestMethod.GET)
	public String deleteNotif(@PathVariable("id") Integer id) {
		notificationService.deleteById(id);
		return "redirect:/notification";
	}
	///////////////////////////////// END CONSULTATIONS ///////////////////

	///////////////////////////// PATIENTS///////////////////////
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public ModelAndView createClient() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("message", "Create new Hospital consultation");
		Patient c1 = new Patient();

		forSignUp.addObject("client", c1);
		forSignUp.setViewName("client");
		return forSignUp;
	}

	@Transactional
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public String createClient(Patient c1) {

		Report report = new Report();

		if (c1.getAddress().trim().equals("") || c1.getCnp().trim().equals("") || c1.getDateOfBirth().trim().equals("")
				|| c1.getLastName().trim().equals("") || c1.getFirstName().trim().equals("")
				|| c1.getCnp().length() != 13)
			return "redirect:/wrong";

		else {

			org.springframework.security.core.userdetails.User loggedIn = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			report.setMessage("Client was created by: " + loggedIn.getUsername());
			reportService.save(report);

			patientService.insert(c1);

		}
		// log.info("INFO: Client was created");
		// sucCreated.setViewName("redirect:/clientlist");
		return "redirect:/clientlist";
	}

	@RequestMapping(value = "/client/edit/{id}", method = RequestMethod.GET)
	public ModelAndView clientEdit(@PathVariable("id") Integer id) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("message", "Edit patient");
		Patient c1 = patientService.findById(id);

		if (c1.getAddress().trim().equals("") || c1.getCnp().trim().equals("") || c1.getDateOfBirth().trim().equals("")
				|| c1.getLastName().trim().equals("") || c1.getFirstName().trim().equals("")) {
			forSignUp.setViewName("redirect:/wrong");
			return forSignUp;
		}

		forSignUp.addObject("client", c1);
		forSignUp.setViewName("client");
		return forSignUp;
	}

	@RequestMapping(value = "/clientlist", method = RequestMethod.GET)
	public ModelAndView clist() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Hospital Application");
		forSignUp.addObject("msg", " ");
		List<Patient> c1 = patientService.findAll();
		forSignUp.addObject("clients", c1);
		forSignUp.setViewName("clientlist");
		return forSignUp;
	}

	@RequestMapping(value = "/client/delete/{id}", method = RequestMethod.GET)
	public String deleteClient(@PathVariable("id") Integer id) {
		Report report = new Report();
		org.springframework.security.core.userdetails.User loggedIn = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		report.setMessage("Client was deleted by: " + loggedIn.getUsername());
		reportService.save(report);
		patientService.deleteById(id);
		// log.info("INFO: Client was deleted");
		return "redirect:/clientlist";
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

	@Transactional
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ModelAndView notif() {
		ModelAndView notificationPurpose = new ModelAndView();
		notificationPurpose.addObject("title", "Hospital Application");
		notificationPurpose.addObject("msg", " ");

		List<Notification> c1 = notificationService.findAll();
		notificationPurpose.addObject("notification", c1);
		notificationPurpose.setViewName("notification");
		return notificationPurpose;

	}

	@Transactional
	@RequestMapping(value = "/client/notification/check/{patientId}/{id}", method = RequestMethod.GET)
	public String checkNotif(@PathVariable("patientId") Integer patientId, @PathVariable("id") Integer id) {

		Consultation c = consultationService.findById(patientId);
		Notification n = notificationService.findById(id);
		n.setStatus("Patient seen!");
		notificationService.save(n);
		c.setChecked(true);

		return "redirect:/notification";

	}

	@Transactional
	@RequestMapping(value = "/wrong", method = RequestMethod.GET)
	public String wrong() {
		return "/wrong";

	}

}
