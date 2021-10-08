package com.bridgelabz.api.model;

import java.time.LocalDateTime;
import java.util.Date;
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

import com.bridgelabz.api.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "labels")
public @Data class Label {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private int labelId;

	@Column(name = "label_name")
	private String labelName;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "note_id")
	private int noteId;

	@Column(name = "label_created_date")
	private LocalDateTime createdDate;


	@Column(name = "label_updated_date")
	private LocalDateTime updatedDate;

	@JsonIgnoreProperties(value = "labels")
	@ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Note> notes;

	public Label(LabelDTO labelDTO) {
	
		this.labelName = labelDTO.labelName;
		this.createdDate = labelDTO.createdDate;
		this.notes = labelDTO.notes;
	}
	
	public void  updateLabel(LabelDTO labelDTO) {
		
		this.labelName = labelDTO.labelName;
		this.updatedDate = labelDTO.createdDate;
		this.notes = labelDTO.notes;
	}

	public Label() {
		super();
	}

	
	

}
