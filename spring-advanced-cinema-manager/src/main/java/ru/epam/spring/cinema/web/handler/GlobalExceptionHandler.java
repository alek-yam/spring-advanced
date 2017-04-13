package ru.epam.spring.cinema.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception.toString());
	    String message = exception.getMessage();
	    mav.addObject("details", message != null ? message : "");
	    mav.setViewName("error");
	    return mav;
	}

}
