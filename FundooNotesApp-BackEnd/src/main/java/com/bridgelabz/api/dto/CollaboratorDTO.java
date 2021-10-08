package com.bridgelabz.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.api.model.Note;

import lombok.Data;

@Data
public class CollaboratorDTO {
	
	public LocalDateTime createdDate = LocalDateTime.now();
	
	public Long userId;

	public String userEmail;
	
	public String userFname;
	
	public String userLname;
	
	public List<Note> notes;

}
