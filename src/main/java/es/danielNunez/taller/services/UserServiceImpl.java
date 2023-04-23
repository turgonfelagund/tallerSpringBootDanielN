package es.danielNunez.taller.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.danielNunez.taller.erroHandler.ErrorHandler;
import es.danielNunez.taller.models.User;
import es.danielNunez.taller.repositories.UserRepository;

@Service
public class UserServiceImpl extends ErrorHandler implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
	
		return userRepository.findById(id);
	}
	
	public Object saveUser(User newUser) throws ErrorHandler {
		try {
			String msg="";
			
			if (newUser.getName() == null || newUser.getName().isEmpty()) {
				return nonValidFields("'name' no puede ser null");
			}
			
			if (newUser.getBirthdate() == null || newUser.getBirthdate().after(new Date())) {
				 return nonValidFields("'birthDate' no puede ser null o posterior a la fecha actual");
			}
			
			User user = userRepository.save(newUser);
			
			if (user!= null) {
				msg = "Se ha insertado con éxito al usuario " + user.getName() + " con birthdate " + user.getBirthdate();
			}else {
				msg = "No se ha podido insertar al usuario " + newUser.getName();
			}
			
			 return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			throw handleDefaultError(e);
		}
		
	} 

	
	public String updateUser(User updatingUser) throws ErrorHandler {
		
		try {
			String msg = "";
			
			Optional<User> user = getUserById(updatingUser.getId());
			
			if (user.isPresent()) {
				userRepository.save(updatingUser);
				msg = "Usuario actualizado satisfactoriamente. name: " + updatingUser.getName() + " birthdate: " + updatingUser.getBirthdate();
			} else {
				msg = "El usuario "+ updatingUser.getId() +" no existe en la BBDD" + user;
			}
			
			return msg;
			
		} catch (Exception e) {
			
			throw  handleDefaultError(e);
		}
		
		
		
	}
	
	public String deleteUser(int id) {
		
		userRepository.deleteById(id);
		
		String msg = null;
		
		if (getUserById(id).isPresent()) {
			msg = "No se pudo eliminar al usuario " + id;
		} else {
			msg = "Se ha borrado el usuario " + id;
		}
		
		return msg;
	}

	
}
