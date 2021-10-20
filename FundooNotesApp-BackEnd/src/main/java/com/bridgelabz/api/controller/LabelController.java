package com.bridgelabz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.service.ILabelService;
import com.bridgelabz.api.util.ResponseDTO;

@RestController
@RequestMapping("/label")
@CrossOrigin(origins = "http://localhost:4200")
public class LabelController {
	
	@Autowired
	ILabelService labelService;
	
	@GetMapping("")
	public ResponseDTO getAllLabelsByUser(@RequestParam String UserIdToken) {
		return labelService.getAllLabelsByUser(UserIdToken);
	}
	
	@PostMapping("/add")
	public ResponseDTO addLabel(@RequestParam String userIdToken, @RequestBody LabelDTO labelDTO) {
		return labelService.addLabel(userIdToken, labelDTO);
	}
	
	@PutMapping("/update")
	public ResponseDTO updateLabel(@RequestParam String userIdToken, @RequestParam int labelId, @RequestBody LabelDTO labelDTO) {
		return labelService.updateLabel(userIdToken, labelId, labelDTO);
	}
	
	@DeleteMapping("/deletelabel")
	public ResponseDTO deleteLabel(@RequestParam String userIdToken, @RequestParam int labelId) {
		return labelService.deleteLabel(userIdToken, labelId);
		
	}
	
	@DeleteMapping("/deleteall")
	public ResponseDTO removeAllNotesByLabel(@RequestParam String userIdToken, @RequestParam int labelId) {
		return labelService.deleteLabel(userIdToken, labelId);
	}
	
	@DeleteMapping("/delete")
	public ResponseDTO deleteAllLabelByUser(@RequestParam String userIdToken) {
		return labelService.deleteAllLabelByUser(userIdToken);
	}

}
