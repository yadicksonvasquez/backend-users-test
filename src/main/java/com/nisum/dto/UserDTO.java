/**
 * 
 */
package com.nisum.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User DTO
 * 
 * @author yadicksonvasquez@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;

	@NotNull
	private String password;

	@JsonIgnore
	private String tokenJWT;

	@JsonIgnore
	private LocalDateTime created;

	@JsonIgnore
	private LocalDateTime modified;

	@JsonIgnore
	private LocalDateTime lastLogin;
	
	@JsonIgnore
	private Boolean isActive;

	private List<PhoneDTO> phones = new LinkedList<>();

}
