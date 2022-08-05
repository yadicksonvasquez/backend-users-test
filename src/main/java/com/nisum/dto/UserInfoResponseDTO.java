/**
 * 
 */
package com.nisum.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User info response DTO
 * 
 * @author yadicksonvasquez@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	@JsonProperty("last_login")
	private LocalDateTime lastLogin;
	
	private String token;
	
	@JsonProperty("isactive")
	private Boolean isActive;
	
}
