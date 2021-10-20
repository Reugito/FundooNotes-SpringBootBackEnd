package com.bridgelabz.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.api.dto.UserDTO;
import com.bridgelabz.api.exception.UserRegisteredException;
import com.bridgelabz.api.model.User;
import com.bridgelabz.api.repository.UserRepository;
import com.bridgelabz.api.util.ImageUtil;
import com.bridgelabz.api.util.JWTToken;
import com.bridgelabz.api.util.ResponseDTO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	JWTToken myToken;
	
	@Autowired
	MyEmailService emailService;
	
	@Autowired
	OTPService otpService;
	
	@Autowired
	ImageUtil imgUtil;

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public ResponseDTO addUser(UserDTO userDTO) throws IOException {
		Optional<User> isPresent = userRepo.findByEmailid(userDTO.getEmailid());
		if(isPresent.isPresent()) {
			throw new UserRegisteredException(400, "User already present");
		} 
			User user = new User (userDTO);
			userRepo.save(user);
			
			String token = myToken.createToken(user.getId());
			
			int otp = otpService.generateOTP(userDTO.emailid);
			
			emailService.sendOTPMessage(userDTO.emailid, "Registration OTP","Token = "+token
																		+"Your OTP = "+otp);
			return new ResponseDTO(200, "User Successfully added", token);
	}

	@Override
	public Optional<User> getUserByEmailId(String emailId) {
		return userRepo.findByEmailid(emailId);
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		return userRepo.findById(userId);
	}

	@Override
	public ResponseDTO loginUser(String email, String password) {
		Optional<User> isPresent = userRepo.findByEmailid(email);
		if(isPresent.isPresent()) {
			if(isPresent.get().getPassword().equals(password)) {
				
				String token = myToken.createToken(isPresent.get().getId());
				return new ResponseDTO(200, "User Login Successfully ", token);
				
			}
		} 
		return new ResponseDTO(400, "User Login Failed", "try again");
	}

	@Override
	public ResponseDTO verifyUser(String email, int otp) {
		Optional<User> isPresent = userRepo.findByEmailid(email);
		if(isPresent.isPresent()) {
			
			User user = isPresent.get();
			if(otpService.getOTP(email) == otp) {
				user.setVerify(true);
				userRepo.save(user);
				return new ResponseDTO(200, "User Successfully verified", isPresent);
			}
		}
		return new ResponseDTO(400, "Wrong OTP", null);
	}

	@Override
	public ResponseDTO forgotPassword(String token, String psw) {
		Optional<User> isUserPresent = userRepo.findByEmailid(token);
		
		if(!isUserPresent.isPresent()) {
			throw new UserRegisteredException(400, "User is not present!!");
		}
		User user = isUserPresent.get();
		user.setPassword(psw);
		userRepo.save(user);
		return new ResponseDTO(200, "pasword updated succsessfully", user);
	
	}

	@Override
	public ResponseDTO updateUser(String token, UserDTO userDTO) {
		Long id = myToken.decodeToken(token);
		Optional<User> isUserPresent = userRepo.findById(id);
		
		if(!isUserPresent.isPresent()) {
			throw new UserRegisteredException(400, "User is not present!!");
		}
		System.out.println("user to update  is"+ isUserPresent.get());
		User user = isUserPresent.get();
		user.UpdateUser(userDTO);
		userRepo.save(user);
		return new ResponseDTO(200, "user updated succsessfull", user);
	}

	@Override
	public ResponseDTO deleteUser(String token) {
		Long id = myToken.decodeToken(token);
		Optional<User> user = Optional.of(userRepo.getById(id));
		if(user.isPresent()) {
			userRepo.delete(user.get());
			return new ResponseDTO(200, "user deleted succsessfully", user);
		}
		return new ResponseDTO(200, "user not present", user);
	}

	@Override
	public ResponseDTO login(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserById1(String token) {
		Long userId = myToken.decodeToken(token);
		User user = userRepo.getById(userId);
		
		return user.getEmailid();
	}

}
