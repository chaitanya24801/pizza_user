package com.example.demo.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;


	
@Service
@Transactional
public class UserService {
	
	private final UserRepo userRepo;
	
	
	
	@Autowired
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
		
	}

	public User addNewUser(User user) {
		return userRepo.save(user);
		
	}

	public List<User> viewUsers() {
		return userRepo.findAll();
	}

	public String signin(User user) {
		
		return "Welcome "+user.getUsername()+" ,you have successfully logged in!!";
	}

	public UserRepo getUserRepo() {
		return userRepo;
	}


	public String forgotPassword(User user) {
		User ruser=userRepo.getById(user.getUserId());
		String newpwd=user.getPassword();
		ruser.setPassword(newpwd);
		return "Password reset Successfull";
	
		
	}
	
}