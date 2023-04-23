package es.danielNunez.taller.services;

import java.util.List;
import java.util.Optional;

import es.danielNunez.taller.erroHandler.ErrorHandler;
import es.danielNunez.taller.models.User;

public interface UserService {

	List<User> getAllUsers();
	
	Optional<User> getUserById(int id);
	
	String deleteUser(int id);
	
	Object saveUser(User user) throws ErrorHandler;
	
	String updateUser(User user) throws ErrorHandler;
	
}
