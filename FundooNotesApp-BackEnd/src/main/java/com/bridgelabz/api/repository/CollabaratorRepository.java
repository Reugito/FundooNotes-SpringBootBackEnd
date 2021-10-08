package com.bridgelabz.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.api.model.Collaborator;

public interface CollabaratorRepository extends JpaRepository<Collaborator, Integer> {
	
	@Query(value = "SELECT * FROM collaborator where collab_user_id=:userId", nativeQuery = true)
	 public List<Collaborator> findAllByUser(Long userId);

}
