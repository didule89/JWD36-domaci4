package jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.UserService;

@Service
public class JpaUserService implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User user = userRepository.findOne(id);
		if (user != null) {
			userRepository.delete(id);
		}
		
	}

	@Override
	public List<User> findByName(String firstname) {
		return userRepository.findByFirstname(firstname);
	}
	
	@Override
	public List<User> findByLastnameAndFirstname(String lastname, String firstname) {
		return userRepository.findByLastnameAndFirstname(lastname, firstname);
	}
	
//	@PostConstruct
	public void init() {
		
		User user = new User();
		User user1 = new User();
		User user2 = new User();
		
		user.setEmail("duka@.com");
		user.setPassword("1312");
		user.setFirstname("Dusan");
		user.setLastname("Ivanisevic");
		
		user1.setEmail("coja@.com");
		user1.setPassword("ACAB");
		user1.setFirstname("Jovana");
		user1.setLastname("Ivanisevic");
		
		user2.setEmail("og@.com");
		user2.setPassword("ACAB1312");
		user2.setFirstname("Ognjena");
		user2.setLastname("Crnogorac");
		
		save(user);
		save(user1);
		save(user2);
	}

	

}
