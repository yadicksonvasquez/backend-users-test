/**
 * 
 */
package com.nisum.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.model.User;

/**
 * JpaRepository User entity
 * @author yadicksonvasquez@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = :email_param")
	User findByEmail(@Param("email_param") String email);

}
