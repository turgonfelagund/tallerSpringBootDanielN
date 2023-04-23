package es.danielNunez.taller.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.danielNunez.taller.erroHandler.ErrorHandler;
import es.danielNunez.taller.models.User;
import es.danielNunez.taller.services.UserServiceImpl;

@RestController
@RequestMapping("/user/")
public class userController {

	@Autowired
	public UserServiceImpl userServiceImpl;
	
	@GetMapping(value = "test")
	public String saludo() {
		return "Hola mundo";
	}
	
	@GetMapping(value = "getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getAllUsers());
	}
	
	@GetMapping(value = "getuser/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id){
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getUserById(id));
	}
	
	@PostMapping(value = "save")
	public ResponseEntity<Object> saveUser(@RequestBody User user) throws ErrorHandler {
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.saveUser(user));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.deleteUser(id));
	}
	
	@PutMapping(value = "update")
	public ResponseEntity<String> updateUser(@RequestBody User user) throws ErrorHandler {
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.updateUser(user));
	}
	
}
