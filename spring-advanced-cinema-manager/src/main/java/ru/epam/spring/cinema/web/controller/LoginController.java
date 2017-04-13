package ru.epam.spring.cinema.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	//@Autowired
	//UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm() {
		return "login";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String verifyLogin(@RequestParam String email, @RequestParam String password,
//    		HttpSession session, Model model) {
//
//		User user = userService.getByEmail(email);
//		if (user != null && user.getPassword().equals(password)) {
//			session.setAttribute("loggedInUser", user);
//	        return "redirect:/";
//		}
//
//		model.addAttribute("loginError", "Error logging in. Please try again.");
//		return "login";
//    }

//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.removeAttribute("loggedInUser");
//		return "login";
//	}

}
