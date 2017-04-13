package ru.epam.spring.cinema.web.form;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

import ru.epam.spring.cinema.domain.Event;

public class TicketForm {
	private Event event;
	private Long time;
	private Set<Long> seats;
	private Map<Long, String> allSeats;
	private Calendar date;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
		updateDate();
	}

	public Set<Long> getSeats() {
		return seats;
	}

	public void setSeats(Set<Long> seats) {
		this.seats = seats;
	}

	public Map<Long, String> getAllSeats() {
		return allSeats;
	}

	public void setAllSeats(Map<Long, String> allSeats) {
		this.allSeats = allSeats;
	}

	public Calendar getDate() {
		return date;
	}

	public String getAuditoriumName() {
		return event.getAuditoriums().get(date);
	}

	private void updateDate() {
		if (date == null) {
			date = new GregorianCalendar();
		}

		date.setTimeInMillis(time.longValue());
	}
}
