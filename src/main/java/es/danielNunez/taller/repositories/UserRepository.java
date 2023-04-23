package es.danielNunez.taller.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.danielNunez.taller.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAll();
	
	Optional<User> findById(int id);
	
	User save(User user);
	
	void deleteById(int id);
	
}
