package jwd.wafepa.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.User;
import jwd.wafepa.service.impl.InMemoryUserService;

public class InMemoryUserServiceTest {
	
	private UserService userService;
	
	@Before
	public void setUp() {
		userService = new InMemoryUserService();
		
		String email, password, firstName, lastName;
		String email1, password1, firstName1, lastName1;
		
		email = "duka@.com";
		password = "1312";
		firstName = "Dusan";
		lastName = "Ivanisevic";
		
		email1 = "coja@.com";
		password1= "ACAB";
		firstName1 = "Jovana";
		lastName1 = "Ivanisevic";
		
		
		userService.save(new User(email, password, firstName, lastName));
		userService.save(new User(email1, password1, firstName1, lastName1));
	}
	
	@Test
	public void testFindOne() {
		User user = userService.findOne(1l);
		
		Assert.assertNotNull(user);
		Assert.assertEquals("Dusan", user.getFirstname());
	}
	
	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		
		Assert.assertNotNull(users);
		Assert.assertEquals(2, users.size());
	}
	
	@Test
	public void testSave() {
		String email2, password2, firstName2, lastName2;
		
		email2 = "og@.com";
		password2 = "ACAB1312";
		firstName2 = "Ognjena";
		lastName2 = "Crnogorac";
		
		User user1 = new User(email2, password2, firstName2, lastName2);
		
		User user = userService.save(user1);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(user1, userService.findOne(user1.getId()));
	}
}
