package com.bridgelabz.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.api.dto.CollaboratorDTO;
import com.bridgelabz.api.model.Collaborator;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.repository.CollabaratorRepository;
import com.bridgelabz.api.util.JWTToken;
import com.bridgelabz.api.util.ResponseDTO;

@Service
public class CollaboratorService implements ICollaboratorService {
	
	@Autowired
	JWTToken myToken;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	CollabaratorRepository colabRepo;

	@Override
	public ResponseDTO addCollaborator(CollaboratorDTO collaboratorDTO) {
		Collaborator colab= new Collaborator(collaboratorDTO);
		return new ResponseDTO(200, "added new collabarator ", colabRepo.save(colab));
	}

	@Override
	public ResponseDTO getCollaborator(String userIdToken, int colabId) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			
			return new ResponseDTO(200, "get collaborator by id", colabRepo.getById(colabId));
			
		}
		return new ResponseDTO(200, "get collaborator by id Failed", null);
	}

	@Override
	public ResponseDTO removeCollaborator(String email, int colabId) {
		Optional<User> user = userService.getUserByEmailId(email);
		if(user.isPresent()) {
			colabRepo.deleteById(colabId);
			return new ResponseDTO(200, "deleted collaborator by id",null);
		}
		return new ResponseDTO(200, "delete collaborator by id failed", null);
	}

	@Override
	public ResponseDTO getAllCollabedUser(String userIdToken) {
		Long userId = myToken.decodeToken(userIdToken);
		
		Optional<User> user = userService.getUserById(userId);
		
		if(user.isPresent()) {
			List<Collaborator> colabList =  colabRepo.findAllByUser(userId);
			colabRepo.deleteAll(colabList);
			return new ResponseDTO(200, "deleted all collaborator ",null);
		}
		return new ResponseDTO(200, "Failed to delete all collaborator ",null);
			
	}

}
