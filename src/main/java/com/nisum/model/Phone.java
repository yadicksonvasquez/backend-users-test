/**
 * 
 */
package com.nisum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity phone
 * @author yadicksonvasquez@gmail.com
 */
@Entity
@Table(name = "phone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number", nullable = false)
	private int number;
	
	@Column(name = "city_code", nullable = false)
	private String cityCode;
	
	@Column(name = "country_code", nullable = false)
	private String countryCode;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	
}
