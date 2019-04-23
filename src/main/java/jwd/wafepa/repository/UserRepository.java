package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFirstname(String firstname);
	List<User> findByLastnameAndFirstname(String lastname, String firstname);

}
