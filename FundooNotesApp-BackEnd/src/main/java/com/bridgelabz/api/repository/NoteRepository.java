package com.bridgelabz.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.api.model.Note;


public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	@Query(value = "SELECT * FROM notes where user_id=:userId", nativeQuery = true)
	 public List<Note> findAllByUser(Long userId);
	
	@Query(value = "SELECT * FROM notes where title=:title", nativeQuery = true)
	 public List<Note> findAllByTitle(String title);

}
