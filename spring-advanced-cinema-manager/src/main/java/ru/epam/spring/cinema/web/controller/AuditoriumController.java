package ru.epam.spring.cinema.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.epam.spring.cinema.domain.Auditorium;
import ru.epam.spring.cinema.service.AuditoriumService;

@Controller
@RequestMapping("/auditoriums")
public class AuditoriumController {

	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		Set<Auditorium> auditoriums = auditoriumService.getAll();
		model.addAttribute("auditoriums", auditoriums);
		return "auditorium/list";
	}

}
