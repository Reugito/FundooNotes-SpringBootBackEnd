package com.bridgelabz.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bridgelabz.api.dto.CollaboratorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "collaborator")
public @Data class Collaborator {
	
	@Id
	@Column(name = "collab_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int collabId;

	@Column(name = "collab_created_date")
	private LocalDateTime  createdDate;

	@Column(name = "collab_updated_date")
	private LocalDateTime updatedDate;
	
	@Column(name = "collab_user_id")
	private Long userId;

	@Column(name = "collab_user_email")
	private String userEmail;
	
	@Column(name = "collab_user_fname")
	private String userFname;
	
	@Column(name = "collab_user_lname")
	private String userLname;

	@JsonIgnoreProperties(value = "collaborators")
	@ManyToMany(mappedBy = "collaborators", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Note> notes;
	
	public Collaborator() {
		super();
	}

	public Collaborator(CollaboratorDTO coollaboratorDTO) {
		this.createdDate = coollaboratorDTO.createdDate;
		this.userId = coollaboratorDTO.userId;
		this.userEmail = coollaboratorDTO.userEmail;
		this.userFname = coollaboratorDTO.userFname;
		this.userLname = coollaboratorDTO.userLname;
		this.notes = coollaboratorDTO.notes;
	}
	
	public void updateCollaborator(CollaboratorDTO coollaboratorDTO) {
		
		this.updatedDate = coollaboratorDTO.createdDate;
		this.userId = coollaboratorDTO.userId;
		this.userEmail = coollaboratorDTO.userEmail;
		this.userFname = coollaboratorDTO.userFname;
		this.userLname = coollaboratorDTO.userLname;
		this.notes = coollaboratorDTO.notes;
	}



	
	
	
}
