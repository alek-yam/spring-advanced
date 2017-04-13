package ru.epam.spring.cinema.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.epam.spring.cinema.domain.User;
import ru.epam.spring.cinema.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

//  Why I get "Could not autowire field" exception for "passwordEncoder" bean ???
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute @Valid User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "user/register";
		}

		boolean alredyExists = userService.getByEmail(user.getEmail()) != null;
		if (alredyExists) {
			model.addAttribute("regError", "User with specified email already exists. Please use another email and try again.");
			return "user/register";
		}

		// setting default role
		Set<String> roles = new HashSet<String>();
		roles.add("REGISTERED_USER");
		user.setRoles(roles);

		// setting password
		String hashCode = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashCode);

		User registeredUser = userService.save(user);
		redirectAttributes.addFlashAttribute("user", registeredUser);
		return "redirect:/register/success";
	}

	@RequestMapping(value = "/register/success", method = RequestMethod.GET)
	public String showRegisteredUser() {
		return "user/view";
	}
}
