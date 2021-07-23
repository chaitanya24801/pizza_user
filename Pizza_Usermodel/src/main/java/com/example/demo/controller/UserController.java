package com.example.demo.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	private final UserRepo userRepo;
    protected static final String DEFAULT_ROLE = "ROLE_USER";	
    protected static final String[] ADMIN_ACCESS = {"ROLE_ADMIN"};
    
	public UserController(UserService userService, UserRepo userRepo) {
		this.userService=userService;
		this.userRepo = userRepo;
	}
	
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addNewUser(@RequestBody UserDto user){
		user.getUser().setRoles(DEFAULT_ROLE);
		User newuser = userService.addNewUser(user.getUser());
			return new ResponseEntity <>(newuser,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity <List<User>>viewUsers(){
		List<User> users=userService.viewUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	


	@PostMapping("/login")
	public ResponseEntity<String> signin(@RequestBody UserDto user){
		String msg = userService.signin(user.getUser());
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<User> signout(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<String>forgotPassword(@RequestBody UserDto user){
		String msg=userService.forgotPassword(user.getUser());
		return new ResponseEntity <> (msg,HttpStatus.OK);
	}
	
   

	 
}
