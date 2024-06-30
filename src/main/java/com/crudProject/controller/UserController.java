package com.crudProject.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudProject.exception.UserNotFoundException;
import com.crudProject.model.User;
import com.crudProject.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/crud")
@Validated
public class UserController {

	@Autowired
	private UserService ser;
	
	@PostMapping("/save")
	public User save(@Valid @RequestBody User user) {
		return ser.saveUser(user);
	}
		
	@GetMapping("/getall")
	public List<User> getAll(){
		return ser.getAllUsers();
	}
	
//	@GetMapping("/getbyid/{id}")
//	public Optional<User> getById(@PathVariable Integer id){
//		return ser.findById(id);
//	}
	
	   @GetMapping("/getbyid/{id}")
	    public ResponseEntity<User> getById(@PathVariable Integer id){
	        User user = ser.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
	        return ResponseEntity.ok(user);
	    }
	
//	@DeleteMapping("/deletebyid/{id}")
//	public void deleteById(@PathVariable Integer id) {
//		return ser.deleteById(id);
//	}
	
	  @DeleteMapping("/deletebyid/{id}")
	    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
	        ser.deleteById(id);
	        return ResponseEntity.ok("Deleted Successfully Id = " + id);
	    }
	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		return ser.updateUser(id, user);
	}
	
	@GetMapping("/getbyrole/{role}")
	public List<User> findByRoleCustomeQuery(@PathVariable String role) {
		return ser.findByRoleCustom(role);
	}
	
	
}
