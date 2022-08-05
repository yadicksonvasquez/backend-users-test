/**
 * 
 */
package com.nisum.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.nisum.dto.PhoneDTO;
import com.nisum.dto.UserDTO;
import com.nisum.dto.UserInfoResponseDTO;
import com.nisum.exception.NisumTestException;
import com.nisum.model.Phone;
import com.nisum.model.User;
import com.nisum.model.converter.UserConverter;
import com.nisum.model.repository.UserRepository;
import com.nisum.security.JwtTokenUtil;

import lombok.extern.log4j.Log4j2;

/**
 * UserService implements
 * 
 * @author yadicksonvasquez@gmail.com
 */
@Service
@Log4j2
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserConverter converter;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationService authenticationService;

	private static final String ERROR = "ERROR";

	public UserDTO loadUserByEmail(String email) throws NisumTestException {

		try {
			log.info("loadUserByEmail:" + email);
			User entity = repository.findByEmail(email);
			if (entity != null) {
				TypeMap<User, UserDTO> typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
				typeMap.setConverter(converter.getConverterUserToDTO());
				UserDTO userDto = modelMapper.map(entity, UserDTO.class);
				userDto.setPhones(entity.getPhones().stream().map(phone -> new PhoneDTO(phone.getId(),
						phone.getNumber(), phone.getCityCode(), phone.getCountryCode())).collect(Collectors.toList()));
				return userDto;
			}
			return null;
		} catch (Exception e) {
			log.error(ERROR, e);
			throw new NisumTestException(e);
		}
	}

	@Override
	@Transactional
	public UserInfoResponseDTO addUser(UserDTO user) throws NisumTestException {
		try {
			// validate if users exists
			UserDTO current = loadUserByEmail(user.getEmail());
			if (current != null) {
				throw new NisumTestException("El correo ya esta registrado");
			} else {
				TypeMap<UserDTO, User> typeMap = modelMapper.createTypeMap(UserDTO.class, User.class);
				typeMap.setConverter(converter.getConverterDTOToUser());
				User entity = modelMapper.map(user, User.class);
				entity.setPhones(user.getPhones().stream().map(
						dto -> new Phone(dto.getId(), dto.getNumber(), dto.getCityCode(), dto.getCountryCode(), entity))
						.collect(Collectors.toList()));
				entity.setCreated(LocalDateTime.now());
				entity.setModified(LocalDateTime.now());
				entity.setIsActive(true);
				User entityUpdated = repository.save(entity);
				String token = this.setTokenJWT(entity.getEmail(), entityUpdated);				
				return new UserInfoResponseDTO(entityUpdated.getId(), entityUpdated.getCreated(), entityUpdated.getModified(), entityUpdated.getLastLogin(), token, entityUpdated.getIsActive());
			}
		} catch (Exception e) {
			log.error(ERROR, e);
			throw new NisumTestException(e);
		}
	}

	/**
	 * Set JWT Token
	 * @param entity
	 * @throws NisumTestException
	 */
	private String setTokenJWT(String email, User entity) throws NisumTestException {
		String token = "";
		try {
			UserDetails user = authenticationService.loadUserByUsername(email);
			//get token
			token = jwtTokenUtil.generateToken(user);
			entity.setTokenJWT(token);
			repository.save(entity);
		} catch (Exception e) {
			log.error(ERROR, e);
			throw new NisumTestException(e);
		}
		return token;
	}

	@Override
	public List<UserDTO> getAllUsers() throws NisumTestException {
		try {
			TypeMap<User, UserDTO> typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
			typeMap.setConverter(converter.getConverterUserToDTO());
			return repository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
					.collect(Collectors.toList());

		} catch (Exception e) {
			log.error(ERROR, e);
			throw new NisumTestException(e);
		}
	}

}
