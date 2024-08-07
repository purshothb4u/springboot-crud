package com.crudProject.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudProject.exception.UserNotFoundException;
import com.crudProject.model.User;
import com.crudProject.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository rep;

	public User saveUser(User user) {
		return rep.save(user);
	}
	
	public List<User> getAllUsers(){
		return rep.findAll();
	}
	
	public Optional<User> findById(Integer id){
		return rep.findById(id);
	}
	
//	public String deleteById(Integer id) {
//		 rep.deleteById(id);
//		 return "Deleted Successfully Id = " + id;
//	}
	
	  public void deleteById(Integer id) {
	        Optional<User> user = rep.findById(id);
	        if (user.isPresent()) {
	            rep.deleteById(id);
	        } else {
	            throw new UserNotFoundException("User not found with id: " + id);
	        }
	    }
	
	public User updateUser(Integer id, User user) {
		Optional<User> userdata = rep.findById(id);
		if(userdata.isPresent()) {
			User u= userdata.get();
			u.setName(user.getName());
			u.setRole(user.getRole());
			return rep.save(u);
		}
		else {
			 throw new UserNotFoundException("User not found with id: " + id);
		}
	}
	
	public List<User> findByRoleCustom(String role) {
		return rep.findByRole(role);
	}
	
	
}
