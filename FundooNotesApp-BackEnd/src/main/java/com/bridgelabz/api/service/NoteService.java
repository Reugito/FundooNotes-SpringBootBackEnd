package com.bridgelabz.api.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.dto.NoteDTO;
import com.bridgelabz.api.model.Note;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.repository.NoteRepository;
import com.bridgelabz.api.util.JWTToken;
import com.bridgelabz.api.util.ResponseDTO;

@Service
public class NoteService implements INote {
	
	@Autowired
	JWTToken myToken;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	NoteRepository noteRepo;

	@Override
	public ResponseDTO add(String userIdToken, NoteDTO noteDTO) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			
			Note note = new Note(noteDTO);
			note.setUserId(userId);
			;
			return new ResponseDTO(200, "Node added by "+ user.get().getFname(), noteRepo.save(note));
			
		}
		return new ResponseDTO(200, "User is not present ", null);
	}

	@Override
	public ResponseDTO getNoteByUser(String userIdToken, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Note notes = noteRepo.getById(noteId);
			return new ResponseDTO(200, "getNoteByUser call success ", notes);
		}
		return new ResponseDTO(200, "getNoteByUser call Failed user not present ", null);
	}

	@Override
	public ResponseDTO getAllNoteByUser(String userIdToken) {
		System.out.println("decode ==="+ " =="+ userIdToken);
		Long userId = myToken.decodeToken(userIdToken);
		System.out.println("decode ==="+ userId+" =="+ userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			List<Note> notes = noteRepo.findAllByUser(userId);
//			System.out.println("note "+ notes);
			notes.stream().forEach(i -> i.setImage(Base64.getDecoder().decode(i.getImage()).toString()));
			
			System.out.println("note "+ notes);
			return new ResponseDTO(200, "getAllNoteByUser call success ", notes);
		}
		return new ResponseDTO(200, "getAllNoteByUser call Failed user not present ", null);
	}

	@Override
	public ResponseDTO getNoteByTitle(String title) {
		
		return null;
	}

	@Override
	public ResponseDTO updateNote(String userIdToken, NoteDTO noteDTO, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			
			Note note = noteRepo.getById(noteId);
			note.updateNote(noteDTO);
			return new ResponseDTO(200, "Note updated by "+ user.get().getFname(), noteRepo.save(note));
			
		}
		return new ResponseDTO(200, "Note is not updated because User is not present ", null);
	}

	@Override
	public ResponseDTO deleteNote(String userIdToken, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Note note = noteRepo.getById(noteId);
			noteRepo.delete(note);
			return new ResponseDTO(200, "Note deleted by "+ user.get().getFname(),null );
			
		}
		return new ResponseDTO(200, "Note is not deleted because User is not present ", null);
	}
	

	@Override
	public ResponseDTO pinTheNote(String userIdToken, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Note note = noteRepo.getById(noteId);
			note.setPin(true);
			return new ResponseDTO(200, "Note is pinned by "+ user.get().getFname(),noteRepo.save(note));
			
		}
		return new ResponseDTO(200, "Note is not pinned because User is not present ", null);
	}

	@Override
	public ResponseDTO addToArchive(String userIdToken, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Note note = noteRepo.getById(noteId);
			note.setArchieve(true);
			return new ResponseDTO(200, "added to archive by "+ user.get().getFname(),noteRepo.save(note) );
			
		}
		return new ResponseDTO(200, "Note is not added to archive because User is not present ", null);
	}

	@Override
	public ResponseDTO addToTrash(String userIdToken, int noteId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Note note = noteRepo.getById(noteId);
			note.setTrash(true);
			return new ResponseDTO(200, "Note added to trash by "+ user.get().getFname(),noteRepo.save(note));
			
		}
		return new ResponseDTO(200, "Note is not added to trash because User is not present ", null);
	}

	@Override
	public ResponseDTO addLabelToNote(String userIdToken, LabelDTO labelToNoteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO removeLabelFromNote(String userIdToken, LabelDTO labelToNoteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO addReminder(String userIdToken, int noteId, LocalDateTime reminder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO updateReminder(String userIdToken, int noteId, LocalDateTime reminder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO removeReminder(String userIdToken, int noteId) {
		// TODO Auto-generated method stub
		return null;
	}

}
