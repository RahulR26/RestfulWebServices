package com.rahul.SpringRestfulJPA.rahulSpring.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResorce {
	
	@Autowired
	public UserDao userService;
	
	@GetMapping("/users")
	public List<User> retrieveAll(){
		return userService.findAll();
	}
	
	@GetMapping("/users/{uid}")
	public User retrieveUser(@PathVariable int uid) {
		User use = userService.findOne(uid);
		if(use==null) {
			throw new UserNotFoundException("user with id--> "+uid+" not found");
		}
		return use;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody User use) {
		if(use.getId()!=null) {
			if(userService.findOne(use.getId())!=null) {
				throw new UserAlreadyExistsException("This user with Id--> "+use.getId()+" Already exists");
			}
		}
		
		User user =userService.save(use);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{uid}")
	public ResponseEntity<Object> deleteUser(@PathVariable int uid) {
		User delete = userService.delete(uid);
		if(delete==null) {
			throw new UserNotFoundException("Could not find user--> "+uid+" to delete");
		}
		return ResponseEntity.noContent().build();
		
	}
	

}
