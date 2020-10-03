package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// get all user
	@GetMapping
	List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	// get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by id :"+ userId));
	}
	
	// create user
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	// update user
	@PutMapping("/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable ("id") long userId) {
		User existing = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by id :"+ userId));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return this.userRepository.save(existing);
	}

	// delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		User existing = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found by id :"+ userId));
		this.userRepository.delete(existing);
		return ResponseEntity.ok().build();
	}
	
}

















