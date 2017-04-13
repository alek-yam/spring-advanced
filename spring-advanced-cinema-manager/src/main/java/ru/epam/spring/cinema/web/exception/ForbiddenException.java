package ru.epam.spring.cinema.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ForbiddenException.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 159534064853895640L;

	/**
	 * Instantiates a new forbidden exception.
	 */
	public ForbiddenException() {
		super();
	}

	/**
	 * Instantiates a new forbidden exception.
	 *
	 * @param message the message
	 */
	public ForbiddenException(String message) {
		super(message);
	}
}
