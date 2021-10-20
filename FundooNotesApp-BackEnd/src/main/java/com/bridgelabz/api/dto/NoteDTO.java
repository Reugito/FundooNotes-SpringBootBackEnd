package com.bridgelabz.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.api.model.Collaborator;
import com.bridgelabz.api.model.Label;

import lombok.Data;

@Data
public class NoteDTO {
	
	public String title;
	
	public String description;

	public String color;
	
	public String image;
	
	public LocalDateTime registerDate = LocalDateTime.now();
	
	public boolean isPin;
	
	public boolean isArchive;
	
	public List<Label> labels;
	
	public List<Collaborator> collaborators;

}
