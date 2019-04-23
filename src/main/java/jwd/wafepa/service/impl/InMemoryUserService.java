package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

import org.springframework.stereotype.Service;

//@Service
public class InMemoryUserService implements UserService {

	private Map<Long, User> data = new HashMap<>();
	private Long idCounter = 1L;
	
	@Override
	public User findOne(Long id) {
		return data.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(data.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(idCounter);
			idCounter++;
		}
		data.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Long id) throws IllegalArgumentException {
		User user = data.remove(id);
		if (user == null) {
			throw new IllegalArgumentException("Removing unexisting user with id=" + id);
		}
	}

	@Override
	public List<User> findByName(String name) {
		List<User> users = new ArrayList<>();
		for (User u : data.values()) {
			if (name.equals(u.getFirstname())) {
				users.add(u);
			}
		}
		return users;
	}

	@Override
	public List<User> findByLastnameAndFirstname(String lastname, String firstname) {
		List<User> users = new ArrayList<>();
		for (User u : data.values()) {
			if (lastname.equals(u.getFirstname()) && firstname.equals(u.getFirstname())) {
				users.add(u);
			}
		}
		return users;
	}

}
