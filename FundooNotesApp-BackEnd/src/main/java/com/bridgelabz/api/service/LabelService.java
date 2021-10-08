package com.bridgelabz.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.model.Label;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.repository.LabelRepository;
import com.bridgelabz.api.util.JWTToken;
import com.bridgelabz.api.util.ResponseDTO;

@Service
public class LabelService implements ILabelService {
	
	@Autowired
	JWTToken myToken;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	LabelRepository labelRepo;

	@Override
	public ResponseDTO addLabel(String userIdToken, LabelDTO labelDTO) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			
			Label label = new Label(labelDTO);
			label.setUserId(userId);
			return new ResponseDTO(200, "added new Label", labelRepo.save(label));
			
		}
		return new ResponseDTO(200, "new Label not added because user not present", null);
	}

	@Override
	public ResponseDTO updateLabel(String userIdToken, int labelId, LabelDTO labelDTO) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Label label = labelRepo.getById(labelId);
			label.updateLabel(labelDTO);
			return new ResponseDTO(200, "updated Label", labelRepo.save(label));
			
		}
		return new ResponseDTO(200, "Label not updated", null);
	}

	@Override
	public ResponseDTO getAllLabelsByUser(String UserIdToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO deleteLabel(String userIdToken, int labelId) {
		
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Label label = labelRepo.getById(labelId);
			labelRepo.delete(label);
			return new ResponseDTO(200, "deleted label ", label);
			
		}
		return new ResponseDTO(200, "not deleted label ", null);
	}

	@Override
	public ResponseDTO removeAllNotesByLabel(String userIdToken, int labelId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			Label label = labelRepo.getById(labelId);
			label.setNotes(null);
			return new ResponseDTO(200, "removed all notes under label", labelRepo.save(label));
			
		}
		return new ResponseDTO(200, "not removed all notes under label", null);
	}

	@Override
	public ResponseDTO deleteAllLabelByUser(String userIdToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
