package com.bridgelabz.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.api.dto.UserDTO;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.service.IUserService;
import com.bridgelabz.api.util.ResponseDTO;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/getall")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	@GetMapping("get/{token}")
	public ResponseDTO getUsers(@PathVariable("token") String token){
		return new ResponseDTO(200, "", userService.getUserById1(token));
	}
	
	@GetMapping(value = {"user/{emailId}"})
	public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
		 return new ResponseEntity<ResponseDTO>( new
		 ResponseDTO(200,"Get Call By Id Success",
		 userService.getUserByEmailId(emailId)), HttpStatus.OK);
	}
	
	@PostMapping("/profile/{pic}")
	public String profilePic(@PathVariable("pic") MultipartFile pic) {
		return "got strin";
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO addUser( @RequestBody UserDTO userDTO) throws IOException{
		System.out.println("hello"+ userDTO);
		
		ResponseDTO response = userService.addUser(userDTO);
		return response;
	}
	
	@PostMapping("/login")
	public ResponseDTO login(@RequestParam(name = "email") String email_id, @RequestParam String psw) {
		return userService.loginUser(email_id, psw);
	}
	
	@PostMapping("/forgotpsw")
	public ResponseDTO forgotPassword(@RequestParam(name = "token") String token, @RequestParam String psw) {
		return userService.forgotPassword(token, psw);
	}
	
	@PostMapping("/verify")
	public ResponseDTO verifyUser(@RequestParam(name = "emailId") String emailId, @RequestParam int otp) {
		return userService.verifyUser(emailId, otp);
	}
	
	@PutMapping("/update/{token}")
	public ResponseDTO updateUser(@PathVariable("token") String token, @RequestBody UserDTO userDTO) {
		return userService.updateUser(token, userDTO);
	}
	
	@DeleteMapping("/delete/{token}")
	public ResponseDTO deleteUser(@PathVariable("token") String token) {
		return userService.deleteUser(token);
	}
	
	

}
