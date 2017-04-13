package ru.epam.spring.cinema.web.util;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * The Class SecurityUtil.
 */
public class SecurityUtil {

    private SecurityUtil() {
        throw new AssertionError();
    }

    /**
     * Convert user roles into spring authorities.
     *
     * @param roles the roles
     * @return the list of authorities
     */
    public static List<GrantedAuthority> convertRolesIntoSpringAuthorities(Set<String> roles) {
        final String[] arrayOfAuthorityNames = roles.toArray(new String[roles.size()]);
        final List<GrantedAuthority> authoritiesForSpring = AuthorityUtils.createAuthorityList(arrayOfAuthorityNames);
        return authoritiesForSpring;
    }

}
