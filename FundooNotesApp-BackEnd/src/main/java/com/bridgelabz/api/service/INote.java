package com.bridgelabz.api.service;

import java.time.LocalDateTime;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.dto.NoteDTO;
import com.bridgelabz.api.util.ResponseDTO;

public interface INote {
	
	ResponseDTO add(String userIdToken, NoteDTO noteDTO);
	
	ResponseDTO getNoteByUser(String userIdToken, int noteId);
	
	ResponseDTO getAllNoteByUser(String userIdToken);
	
	ResponseDTO getNoteByTitle(String title);
	
	ResponseDTO updateNote(String userIdToken, NoteDTO noteDTO, int noteId);
	
	ResponseDTO deleteNote(String userIdToken, int noteId);
	
	ResponseDTO pinTheNote(String userIdToken, int noteId);
	
	ResponseDTO addToArchive(String userIdToken, int noteId);
	
	ResponseDTO addToTrash(String userIdToken, int noteId);

	ResponseDTO addLabelToNote(String userIdToken, LabelDTO labelToNoteDTO);
	
	ResponseDTO removeLabelFromNote(String userIdToken, LabelDTO labelToNoteDTO);
	
	ResponseDTO addReminder(String userIdToken, int noteId, LocalDateTime reminder);
	
	ResponseDTO updateReminder(String userIdToken, int noteId, LocalDateTime reminder);
	
	ResponseDTO removeReminder(String userIdToken, int noteId);
	
}
