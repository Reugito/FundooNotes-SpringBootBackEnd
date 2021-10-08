package com.bridgelabz.api.service;

import com.bridgelabz.api.dto.CollaboratorDTO;
import com.bridgelabz.api.util.ResponseDTO;

public interface ICollaboratorService {
	
	ResponseDTO addCollaborator(CollaboratorDTO collaboratorDTO);
	
	ResponseDTO getCollaborator(String userIdToken, int colabId);
	
	ResponseDTO removeCollaborator(String email, int colabId);
	
	ResponseDTO getAllCollabedUser(String userIdToken);

}
