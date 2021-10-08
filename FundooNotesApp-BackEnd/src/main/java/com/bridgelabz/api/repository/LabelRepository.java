package com.bridgelabz.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.api.model.Label;
import com.bridgelabz.api.model.Note;

public interface LabelRepository extends JpaRepository<Label, Integer> {

//	@Query(value = "SELECT * FROM labels where user_id=:userId", nativeQuery = true)
//	 public List<Label> findAllByUser(Long userId);
	
}
