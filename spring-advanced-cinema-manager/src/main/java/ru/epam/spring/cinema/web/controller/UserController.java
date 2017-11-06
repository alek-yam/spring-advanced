package ru.epam.spring.cinema.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.epam.spring.cinema.domain.User;
import ru.epam.spring.cinema.service.AccountService;
import ru.epam.spring.cinema.service.UserService;
import ru.epam.spring.cinema.web.exception.ForbiddenException;
import ru.epam.spring.cinema.web.userdetails.UserDetailsImpl;
import ru.epam.spring.cinema.web.util.UserUpdater;
import ru.epam.spring.cinema.web.view.UserViewItem;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/users/profile", method = RequestMethod.GET)
	public String showCurrentProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		User currentUser = userDetails.getModelUser();
		return "redirect:/users/" + currentUser.getId().toString();
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String showUserProfile(@PathVariable String userId,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Map<String,Object> model) {

		User currentUser = userDetails.getModelUser();
		if (!currentUser.getId().toString().equals(userId)) {
			throw new ForbiddenException();
		}

		User user = userService.getById(Long.valueOf(userId));
		Double balance = accountService.getBalance(user);
		model.put("user", user);
		model.put("balance", balance);

		return "user/view";
	}

	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("userId") String userId,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Map<String, Object> model) {

		User currentUser = userDetails.getModelUser();
		if (!currentUser.getId().toString().equals(userId)) {
			throw new ForbiddenException();
		}

		User user = userService.getById(Long.valueOf(userId));
		model.put("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.POST)
	public String updateUser(@PathVariable("userId") String userId,
			@ModelAttribute @Valid User user, BindingResult result,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

		if (result.hasErrors()) {
			return "user/edit";
		}

		User currentUser = userDetails.getModelUser();
		if (!currentUser.getId().toString().equals(userId)) {
			throw new ForbiddenException();
		}

		User otherUser = userService.getByEmail(user.getEmail());
		boolean alredyExists = otherUser != null && !otherUser.getId().equals(user.getId());
		if (alredyExists) {
			model.addAttribute("editError", "User with specified email already exists. Please use another email and try again.");
			return "user/edit";
		}

		UserUpdater updater = new UserUpdater(currentUser);
		User updatedUser = updater.update(user);
		userService.save(updatedUser);
		return "redirect:/users/" + user.getId().toString();
	}

	@RequestMapping(value = "/users/{userId}/refillbalance", method = RequestMethod.GET)
	public String showBalanceForm(@PathVariable("userId") String userId,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Map<String, Object> model) {

		User currentUser = userDetails.getModelUser();
		if (!currentUser.getId().toString().equals(userId)) {
			throw new ForbiddenException();
		}

		Double balance = accountService.getBalance(currentUser);

		model.put("user", currentUser);
		model.put("balance", balance);
		return "user/refill";
	}

	@RequestMapping(value = "/users/{userId}/balance", method = RequestMethod.POST)
	public String updateBalance(@PathVariable("userId") String userId, @RequestParam("sum") String sumArg,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

		boolean invalidFormat = false;
		Double sum = 0.;

		User currentUser = userDetails.getModelUser();
		if (!currentUser.getId().toString().equals(userId)) {
			throw new ForbiddenException();
		}

		try {
			sum = Double.parseDouble(sumArg);
		} catch (NumberFormatException ex) {
			logger.error(ex.getMessage());
			invalidFormat = true;
		}

		if (sum < 0 || invalidFormat) {
			Double balance = accountService.getBalance(currentUser);
			model.addAttribute("user", currentUser);
			model.addAttribute("balance", balance);
			model.addAttribute("refillError", "Invalid sum of replenishment: " + sumArg);
			return "user/refill";
		}

		accountService.putMoney(currentUser, sum);

		return "redirect:/users/" + currentUser.getId().toString();
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		Collection<User> users = userService.getAll();
		List<UserViewItem> viewItems = convertToViewItems(users);
		model.addAttribute("users", viewItems);
		return "user/list";
	}

	private List<UserViewItem> convertToViewItems(Collection<User> users) {
		List<UserViewItem> items = new ArrayList<UserViewItem>();

		/*for (User user : users) {
			UserViewItem item = new UserViewItem();
			item.setId(user.getId());
			item.setFullName(user.getFullName());
			item.setBirthday(user.getBirthday().getTime());
			item.setEmail(user.getEmail());
			items.add(item);
		}*/

		return items;
	}
}
