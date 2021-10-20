package com.bridgelabz.api.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
public class UserDTO {

	public String fname;
	
	public String lname;
	
	public String emailid;
	
	public String password;
	
	public String phoneno;
	
	public String dob;
	
	public LocalDateTime registerDate = LocalDateTime.now();
	
	public String profilepic;
	
	

}
