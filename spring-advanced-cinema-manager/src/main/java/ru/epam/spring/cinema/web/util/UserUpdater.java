package ru.epam.spring.cinema.web.util;

import ru.epam.spring.cinema.domain.User;

public class UserUpdater {

	private User user;

	public UserUpdater(User user) {
		this.user = user;
	}

	public User update(User userData) {
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setBirthday(userData.getBirthday());
		user.setEmail(userData.getEmail());
		return user;
	}

}
