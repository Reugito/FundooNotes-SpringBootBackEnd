package com.bridgelabz.api.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.dto.NoteDTO;
import com.bridgelabz.api.model.Note;
import com.bridgelabz.api.repository.NoteRepository;
import com.bridgelabz.api.service.INote;
import com.bridgelabz.api.util.ImageUtil;
import com.bridgelabz.api.util.ResponseDTO;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {
	
	@Autowired
	INote noteService;
	
	@Autowired
	NoteRepository noteRepo;
	
	@Autowired
	ImageUtil imgu;
	
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
	
	
//	 @PostMapping(value="/upload",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//
//	     public org.springframework.http.ResponseEntity.BodyBuilder uplaodImage(@RequestParam(value="File")  MultipartFile file)  {
//		 System.out.println("Original Image Byte Size - " + file);
//		 Note note = new Note();
//		 
////		try {
//////			note.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
////		} catch (IOException e) {
////			System.out.println("hello");
////		}
//		 noteRepo.save(note);
//	     return ResponseEntity.status(HttpStatus.OK);
//	 }
	@PostMapping(value = "/upload")
	public void postImage(@RequestParam("file") MultipartFile file) throws IOException {
	   
	     Note note = new Note();
	    
    note.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
	     String encodedString = note.getImage();
	     
	     note.setUserId(1);
	     noteRepo.save(note);
	}

}
