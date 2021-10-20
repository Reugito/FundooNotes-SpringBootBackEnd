package com.bridgelabz.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bridgelabz.api.dto.UserDTO;

import lombok.Data;

@Entity
@Table(name = "user_details")
public @Data class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "emailid")
	private String emailid;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phoneno")
	private String phoneno;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "registerDate")
	private LocalDateTime registerDate = LocalDateTime.now();
	
	@Column(name = "updateddate")
	private LocalDateTime updatedDate;
	
	@Column(name = "verification")
	private boolean verify;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String profilepic;
	
	public User(UserDTO userDTO ) {

		this.fname = userDTO.fname;
		this.lname = userDTO.lname;
		this.emailid = userDTO.emailid;
		this.password = userDTO.password;
		this.phoneno = userDTO.phoneno;
		this.dob = userDTO.dob;
		this.registerDate = userDTO.registerDate;
		this.verify = false;
		this.profilepic = userDTO.profilepic;
	}

	public void  UpdateUser(UserDTO userDTO) {
		this.fname = userDTO.fname;
		this.lname = userDTO.lname;
		this.emailid = userDTO.emailid;
		this.phoneno = userDTO.phoneno;
		this.dob = userDTO.dob;
		this.updatedDate = userDTO.registerDate;
		this.profilepic = userDTO.profilepic;
	}

	public User() {
		super();
	}
}
