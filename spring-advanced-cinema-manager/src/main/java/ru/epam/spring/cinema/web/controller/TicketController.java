package ru.epam.spring.cinema.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.epam.spring.cinema.domain.Ticket;
import ru.epam.spring.cinema.domain.TicketPrice;
import ru.epam.spring.cinema.domain.User;
import ru.epam.spring.cinema.service.BookingService;
import ru.epam.spring.cinema.service.EventService;
import ru.epam.spring.cinema.service.UserService;
import ru.epam.spring.cinema.web.userdetails.UserDetailsImpl;
import ru.epam.spring.cinema.web.view.TicketViewItem;

@Controller
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public String showPriceList(Model model) {
		List<TicketPrice> priceList = bookingService.getPriceList();
		model.addAttribute("priceList", priceList);
		return "ticket/price";
	}

	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public String showBookedTickets(HttpServletRequest request,
			@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		logger.info("In showBookedTickets");

		//User user = (User) session.getAttribute("loggedInUser");
		User user = userDetails.getModelUser();
		Set<Ticket> tickets = bookingService.getPurchasedTicketsForUser(user);
		List<TicketViewItem> viewItems = convertToViewItems(tickets);
		model.addAttribute("tickets", viewItems);

		if (isPdfFormat(request)) {
			logger.info("Returning PDF view...");
			return "booked-tickets-pdf";
		}

		logger.info("Returning HTML view...");
		return "ticket/booked";
	}

	private boolean isPdfFormat(HttpServletRequest request) {
		String acceptHeader = request.getHeader("Accept");
		if (acceptHeader != null && acceptHeader.contains("application/pdf")) {
			logger.info("Request header \"Accept=application/pdf\" found.");
			return true;
		} else {
			logger.info("Request header \"Accept=application/pdf\" not found.");
		}

		String formatParam = request.getParameter("format");
		if (formatParam != null && formatParam.equals("pdf")) {
			logger.info("Request parameter \"format=pdf\" found.");
			return true;
		} else {
			logger.info("Request parameter \"format=pdf\" not found.");
		}

		return false;
	}

	private List<TicketViewItem> convertToViewItems(Set<Ticket> tickets) {
		List<TicketViewItem> items = new ArrayList<TicketViewItem>();

		/*for (Ticket ticket : tickets) {
			User user = userService.getById(ticket.getUserId());
			Event event = eventService.getById(ticket.getEventId());
			TicketViewItem item = new TicketViewItem();
			item.setUserName(user.getFullName());
			item.setEventName(event.getName());
			item.setDate(ticket.getDate().getTime());
			item.setSeat(ticket.getSeat());
			items.add(item);
		}*/

		return items;
	}
}
