package com.bridgelabz.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.api.dto.UserDTO;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.util.ResponseDTO;

public interface IUserService {
	
	List<User> getUsers();
	
	Optional<User> getUserByEmailId(String emailId);
	
	Optional<User> getUserById(Long userId);
	
	String getUserById1(String token);
	
	ResponseDTO addUser(UserDTO userDTO) throws IOException;
	
	ResponseDTO login(String email);
	
	ResponseDTO loginUser(String email, String password);
	
	ResponseDTO verifyUser(String email, int otp);
	
	ResponseDTO forgotPassword(String token, String psw);
	
	ResponseDTO updateUser(String token, UserDTO userDTO);
	
	ResponseDTO deleteUser(String token);

}
