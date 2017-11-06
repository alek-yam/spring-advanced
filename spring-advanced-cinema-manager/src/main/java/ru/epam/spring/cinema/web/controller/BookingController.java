package ru.epam.spring.cinema.web.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ru.epam.spring.cinema.domain.Auditorium;
import ru.epam.spring.cinema.domain.Event;
import ru.epam.spring.cinema.domain.Seat;
import ru.epam.spring.cinema.service.AuditoriumService;
import ru.epam.spring.cinema.service.BookingService;
import ru.epam.spring.cinema.service.EventService;
import ru.epam.spring.cinema.web.form.TicketForm;
import ru.epam.spring.cinema.web.userdetails.UserDetailsImpl;

@Controller
@RequestMapping("/booking")
@SessionAttributes("ticketForm")
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
	public String showAirDates(@PathVariable Long eventId, Model model) {
		Event event = eventService.getById(eventId);
		TicketForm ticketForm = new TicketForm();
		ticketForm.setEvent(event);
		model.addAttribute("ticketForm", ticketForm);
		logger.info("Selected event: " + ticketForm.getEvent().getName());
		return "booking/dates";
	}

	@RequestMapping(value = "/date", method = RequestMethod.POST)
	public String chooseAirDate(@ModelAttribute TicketForm ticketForm) {

		Calendar date = ticketForm.getDate();
		if (date == null) {
			logger.info("Air date is not specified. Page was rejected.");
			return "booking/dates";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formattedDate = dateFormat.format(date.getTime());
		logger.info("Selected date: " + formattedDate);

		String audName = ticketForm.getAuditoriumName();
		logger.info("Selected auditorium: " + audName);

		Auditorium auditorium = auditoriumService.getByName(audName);
		Collection<Seat> seats = auditorium.getSeats().values();
		logger.info("Auditorium seats: " + Arrays.toString(seats.toArray()));

		Map<Long, String> allSeats = convertToSeatMap(seats);
		ticketForm.setAllSeats(allSeats);

		return "booking/seats";
	}

	@RequestMapping(value = "/seats", method = RequestMethod.POST)
	public String chooseSeats(@ModelAttribute TicketForm ticketForm,	// why I cannot use session.getAttribute("ticketForm") instead ?
			@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

		/*Set<Long> seats = ticketForm.getSeats();
		if (seats == null || seats.isEmpty()) {
			logger.info("Seats are not specified. Page was rejected.");
			return "booking/seats";
		} else {
			logger.info("Selected seats: " + Arrays.toString(seats.toArray()));
		}

		//User user = (User) session.getAttribute("loggedInUser");	// replaced by principal
		//TicketForm ticketForm = (TicketForm) session.getAttribute("ticketForm");	// why it doesn't work? seats == null !!!
		User user = userDetails.getModelUser();

		PriceReport priceReport = bookingService.getFinalPrice(ticketForm.getEvent(), ticketForm.getDate(), user, seats);
		model.addAttribute("priceReport", priceReport);
		logger.info("Price report: " + priceReport.toString());*/

		return "booking/review";
	}

	@RequestMapping(value = "/perform", method = RequestMethod.GET)
	public String bookTickets(@ModelAttribute TicketForm ticketForm,
			@AuthenticationPrincipal UserDetailsImpl userDetails, SessionStatus status) {

		/*
		//User user = (User) session.getAttribute("loggedInUser");
		User user = userDetails.getModelUser();

		BookingReport bookingReport = bookingService.bookTickets(ticketForm.getEvent(),
				ticketForm.getDate(), user, ticketForm.getSeats());
		logger.info("The tickets were booked successfully: " + bookingReport.toString());

		status.setComplete();	// what is the difference with session.invalidate() ?
		*/

		return "redirect:/booking/success";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String showSuccess() {
		return "booking/success";
	}

	// TODO UI related code - should be moved to view
	private Map<Long, String> convertToSeatMap(Collection<Seat> seats) {
		Map<Long, String> map = new TreeMap<Long, String>();

		for (Seat seat : seats) {
			Long key = Long.valueOf(seat.getNumber());
			String value = String.valueOf(seat.getNumber());

			if (seat.isVip()) {
				value += " (VIP)";
			}

			map.put(key, value);
		}

		return map;
	}
}
