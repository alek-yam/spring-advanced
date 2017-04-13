package ru.epam.spring.cinema.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCalendar implements Converter<String, Calendar> {



	@Override
	public Calendar convert(String arg0) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		try {
			date = dateFormat.parse(arg0);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		Calendar calendarDate = new GregorianCalendar();
		calendarDate.setTime(date);
		return calendarDate;
	}
}
