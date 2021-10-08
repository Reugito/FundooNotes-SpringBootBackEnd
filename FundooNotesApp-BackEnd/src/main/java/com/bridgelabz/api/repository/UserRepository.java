package com.bridgelabz.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	@Query(value = "SELECT * FROM user_details where emailid=:email_Id", nativeQuery = true)
	 public Optional<User> findByEmailid(String email_Id);

}
