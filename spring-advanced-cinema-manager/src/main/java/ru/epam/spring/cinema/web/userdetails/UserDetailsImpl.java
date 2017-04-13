package ru.epam.spring.cinema.web.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.epam.spring.cinema.domain.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -6591265628170549609L;

	private User user;
	private List<GrantedAuthority> authorities;

	/**
	 * Instantiates a new UserDetailsImpl instance.
	 *
	 * @param user the model user
	 * @param authorities the user authorities
	 */
	public UserDetailsImpl(User user, List<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	/**
	 * Gets the model user.
	 *
	 * @return the model user
	 */
	public User getModelUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
