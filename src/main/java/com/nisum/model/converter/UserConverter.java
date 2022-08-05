/**
 * 
 */
package com.nisum.model.converter;

import org.modelmapper.Converter;

import com.nisum.dto.UserDTO;
import com.nisum.model.User;

/**
 * @author yadickson
 *
 */
public interface UserConverter {
	
	/**
	 * 
	 * @return
	 */
	Converter<UserDTO, User> getConverterDTOToUser();
	
	/**
	 * 
	 * @return
	 */
	Converter<User, UserDTO> getConverterUserToDTO();

}
