/**
 * 
 */
package com.nisum.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nisum.dto.UserDTO;
import com.nisum.dto.UserInfoResponseDTO;
import com.nisum.exception.NisumTestException;
import com.nisum.service.UserServiceInterface;

/**
 * @author yadickson
 *
 */
@RequestMapping("/users")
@RestController
public class UserRestController {

	@Autowired
	private UserServiceInterface userService;

	@PostMapping
	public ResponseEntity<UserInfoResponseDTO> saveUser(@Valid @RequestBody UserDTO userDto) throws NisumTestException {
		UserInfoResponseDTO response = userService.addUser(userDto);

		return new ResponseEntity<>(response, null, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<UserDTO> getAllUsers() throws NisumTestException {
		return userService.getAllUsers();
	}

}
