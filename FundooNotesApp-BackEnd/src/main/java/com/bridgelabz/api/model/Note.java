package com.bridgelabz.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bridgelabz.api.dto.NoteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "notes")
public @Data class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="NoteDescription")
	private String description;
	
	@Column(name="UserId")
	private long userId;
	
	@Column(name = "registeredDate")
	private LocalDateTime registerDate;
	
	@Column(name = "UpdatedDate")
	private LocalDateTime updateDate;
	
	@Column(name="trash")
	private boolean trash;
	
	@Column(name="archieve")
	private boolean isArchieve;
	
	@Column(name="pin")
	private boolean pin;
	
	@Column(name="labelid")
	private Long labelId;
	
	@Column(name="emailid")
	private String emailid;
	
	@Column(name="color")
	private String color;
	
	@Column(name="reminder")
	private LocalDateTime remindertime;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Label> labels;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Collaborator> collaborators;

	public Note(NoteDTO noteDTO) {
		super();
		this.title = noteDTO.title;
		this.description = noteDTO.description;
		this.registerDate = noteDTO.registerDate;
		this.trash = false;
		this.isArchieve = false;
		this.pin = false;
		this.color = noteDTO.color;
		this.labels = noteDTO.labels;
		this.collaborators = noteDTO.collaborators;
	}
	
	public void updateNote(NoteDTO noteDTO) {
		this.title = noteDTO.title;
		this.description = noteDTO.description;
		this.registerDate = noteDTO.registerDate;
	}

	public Note() {
		super();
	}
	
}
