/**
 * 
 */
package com.nisum.model.converter;

import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.nisum.dto.PhoneDTO;
import com.nisum.dto.UserDTO;
import com.nisum.model.Phone;
import com.nisum.model.User;
import lombok.extern.log4j.Log4j2;

/**
 * UserService implements
 * 
 * @author yadicksonvasquez@gmail.com
 */
@Component
@Log4j2
public class UserConverterImpl implements UserConverter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Converter<UserDTO, User> getConverterDTOToUser() {

		return (MappingContext<UserDTO, User> context) -> {

			UserDTO source = context.getSource();
			User destine = new User();

			try {
				destine.setId(source.getId());
				destine.setName(source.getName());
				destine.setPassword(passwordEncoder.encode(source.getPassword()));
				destine.setPhones(source.getPhones().stream().map(phone -> new Phone(phone.getId(), phone.getNumber(),
						phone.getCityCode(), phone.getCountryCode(), destine)).collect(Collectors.toList()));
				destine.setCreated(source.getCreated());
				destine.setLastLogin(source.getLastLogin());
				destine.setModified(source.getModified());
				destine.setTokenJWT(source.getTokenJWT());
				destine.setEmail(source.getEmail());
				destine.setIsActive(source.getIsActive());
			} catch (Exception e) {
				log.warn(e.getMessage());
			}

			return destine;

		};
	}

	@Override
	public Converter<User, UserDTO> getConverterUserToDTO() {

		return (MappingContext<User, UserDTO> context) -> {

			User source = context.getSource();
			UserDTO destine = new UserDTO();

			try {
				destine.setId(source.getId());
				destine.setName(source.getName());
				destine.setEmail(source.getEmail());
				destine.setPassword(source.getPassword());
				destine.setPhones(source.getPhones().stream().map(phone -> new PhoneDTO(phone.getId(),
						phone.getNumber(), phone.getCityCode(), phone.getCountryCode())).collect(Collectors.toList()));
				destine.setTokenJWT(source.getTokenJWT());
				destine.setCreated(source.getCreated());
				destine.setModified(source.getModified());
				destine.setLastLogin(source.getLastLogin());
				destine.setIsActive(source.getIsActive());
			} catch (Exception e) {
				log.warn(e.getMessage());
			}

			return destine;

		};
	}

}
