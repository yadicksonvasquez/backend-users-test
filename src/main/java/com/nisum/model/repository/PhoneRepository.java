/**
 * 
 */
package com.nisum.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nisum.model.Phone;

/**
 * JpaRepository phone entity
 * @author yadicksonvasquez@gmail.com
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
