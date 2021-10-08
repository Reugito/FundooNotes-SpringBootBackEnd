package com.bridgelabz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.api.dto.CollaboratorDTO;
import com.bridgelabz.api.service.ICollaboratorService;
import com.bridgelabz.api.util.ResponseDTO;

@RestController
@RequestMapping("/collabarator")
public class CollabaratorController {
	
	@Autowired
	ICollaboratorService colabService;
	
	@PostMapping("/add")
	public ResponseDTO addCollaborator(@RequestBody CollaboratorDTO collaboratorDTO) {
		return colabService.addCollaborator(collaboratorDTO);
	}
	
	@GetMapping(value = {"", "/"})
	public ResponseDTO getCollaborator(@RequestParam String userIdToken, @RequestParam int colabId) {
		return colabService.getCollaborator(userIdToken, colabId);
	}
	
	@DeleteMapping("/delete")
	public ResponseDTO removeCollaborator(@RequestParam String email, @RequestParam int colabId) {
		return colabService.removeCollaborator(email, colabId);
	}
	
	@GetMapping
	public ResponseDTO getAllCollabedUser(@RequestParam String userIdToken) {
		return colabService.getAllCollabedUser(userIdToken);
	}

}
