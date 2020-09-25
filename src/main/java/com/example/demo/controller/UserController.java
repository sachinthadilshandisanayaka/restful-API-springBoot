package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("./api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	List<User> getAllUsers(){
		
		return this.userRepository.findAll();
	}
	

}
