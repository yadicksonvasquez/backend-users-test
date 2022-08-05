/**
 * 
 */
package com.nisum.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.nisum.model.User;
import com.nisum.model.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService implements
 * @author yadicksonvasquez@gmail.com
 */
@Service
@Log4j2
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserBuilder builder = null;		
		try {
			log.info("validating user|email:" + email);
			User user = repository.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException("User " + email + "not found.");
			}
			builder = org.springframework.security.core.userdetails.User.withUsername(email);
			builder.disabled(false);
			builder.password(user.getPassword());
			// set Roles
			builder.authorities(new ArrayList<>());
			return builder.build();

		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + email + "not found.");
		}
	}

}
