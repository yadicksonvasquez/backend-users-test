/**
 * 
 */
package com.nisum.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yadickson
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Long id;
	private int number;

	@JsonProperty("citycode")
	private String cityCode;

	@JsonProperty("contrycode")
	private String countryCode;

}
