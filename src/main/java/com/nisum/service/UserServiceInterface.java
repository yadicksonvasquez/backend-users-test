/**
 * 
 */
package com.nisum.service;

import java.util.List;
import com.nisum.dto.UserDTO;
import com.nisum.dto.UserInfoResponseDTO;
import com.nisum.exception.NisumTestException;

/**
 * User service interface
 * @author yadicksonvasquez@gmail.com
 */
public interface UserServiceInterface {
	
	/**
	 * Get user by email
	 * @param email
	 * @return
	 * @throws NisumTestException
	 */
	UserDTO loadUserByEmail(String email) throws NisumTestException;
	
	/**
	 * Add a new user
	 * @param user
	 * @return
	 * @throws NisumTestException
	 */
	public UserInfoResponseDTO addUser(UserDTO user) throws NisumTestException;


	/**
	 * Get all users
	 * @return
	 * @throws NisumTestException
	 */
	public List<UserDTO> getAllUsers() throws NisumTestException;

}
