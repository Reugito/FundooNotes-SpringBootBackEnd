package com.bridgelabz.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.api.model.Note;

import lombok.Data;

@Data
public class LabelDTO {
	
	public String labelName;
	
	public  List<Note> notes;
	
	public LocalDateTime createdDate = LocalDateTime.now();
	

}
