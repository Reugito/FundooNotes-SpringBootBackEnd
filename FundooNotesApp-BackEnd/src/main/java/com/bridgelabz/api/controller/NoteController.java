package com.bridgelabz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.dto.NoteDTO;
import com.bridgelabz.api.service.INote;
import com.bridgelabz.api.util.ResponseDTO;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	INote noteService;
	
	@GetMapping(value ={ "getall/{token}", "/{token}"})
	public ResponseDTO getAllNotesByUser(@PathVariable String token) {
		return noteService.getAllNoteByUser(token);
	}
	
	@GetMapping(value ={ "", "/"})
	public ResponseDTO getNoteByUser(@RequestParam String token, @RequestParam int noteId) {
		return noteService.getNoteByUser(token, noteId);
	}
	
	@PostMapping("/add/{token}")
	public ResponseDTO addNotes(@PathVariable String token, @RequestBody NoteDTO nodeDTO) {
		return noteService.add(token, nodeDTO);
	}
	
	@PutMapping("/update")
	public ResponseDTO updateNote(@RequestParam String token, @RequestParam int noteId, @RequestBody NoteDTO nodeDTO) {
		return noteService.updateNote(token, nodeDTO, noteId);
	}
	
	@PostMapping("/pin")
	public ResponseDTO pinTheNote(@RequestParam String userIdToken, @RequestParam int noteId) {
		return noteService.pinTheNote(userIdToken, noteId);
	}
	
	@PostMapping("/archive")
	public ResponseDTO addToArchive(@RequestParam String userIdToken, @RequestParam int noteId) {
		return noteService.addToArchive(userIdToken, noteId);
	}
	
	@PostMapping("/trash")
	public ResponseDTO addToTrash(@RequestParam String userIdToken, @RequestParam int noteId) {
		return noteService.addToTrash(userIdToken, noteId);
	}
	
	@PostMapping("/addlabel")
	public ResponseDTO addLabelToNote(@RequestParam String userIdToken, @RequestParam LabelDTO labelToNoteDTO) {
		return noteService.addLabelToNote(userIdToken, labelToNoteDTO);
	}
	
	@DeleteMapping("/delete")
	public ResponseDTO deleteNote(@RequestParam String userIdToken, @RequestParam int noteId) {
		return noteService.deleteNote(userIdToken, noteId);
	}
	
	

}
