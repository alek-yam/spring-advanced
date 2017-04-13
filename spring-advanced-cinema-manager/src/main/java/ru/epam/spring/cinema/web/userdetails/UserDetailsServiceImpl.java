package ru.epam.spring.cinema.web.userdetails;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.epam.spring.cinema.domain.User;
import ru.epam.spring.cinema.service.UserService;
import ru.epam.spring.cinema.web.util.SecurityUtil;

/**
 * The UserDetailsService implementation.
 *
 * @author Alex_Yamskov
 */
@Service("securityUserService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getByEmail(email);

		if (user != null) {
			Set<String> roles = user.getRoles();
			List<GrantedAuthority> authorities = SecurityUtil.convertRolesIntoSpringAuthorities(roles);
			return new UserDetailsImpl(user, authorities);
		}

		throw new UsernameNotFoundException("Cannot find user by email: " + email);
	}

}
