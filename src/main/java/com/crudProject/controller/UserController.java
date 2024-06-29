package com.crudProject.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudProject.model.User;
import com.crudProject.service.UserService;

@RestController
@RequestMapping("/crud")
public class UserController {

	@Autowired
	private UserService ser;
	
	@PostMapping("/save")
	public User save(@RequestBody User user) {
		return ser.saveUser(user);
	}
		
	@GetMapping("/getall")
	public List<User> getAll(){
		return ser.getAllUsers();
	}
	
	@GetMapping("/getbyid/{id}")
	public Optional<User> getById(@PathVariable Integer id){
		return ser.findById(id);
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Integer id) {
		return ser.deleteById(id);
	}
	
	@PutMapping("update/{id}")
	public User putMethodName(@PathVariable Integer id, @RequestBody User user) {
		return ser.updateUser(id, user);
	}
	
	@GetMapping("getbyrole/{role}")
	public List<User> findByRoleCustomeQuery(@PathVariable String role) {
		return ser.findByRoleCustom(role);
	}
}
