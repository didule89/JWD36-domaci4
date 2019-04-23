package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOToUser implements Converter<UserRegistrationDTO, User>{

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserRegistrationDTO source) {
		User user;
		if (source.getId() == null) {
			user = new User();
		}
		else {
			user = userService.findOne(source.getId());
		}
		
		user.setEmail(source.getEmail());
		user.setFirstname(source.getFirstname());
		user.setLastname(source.getLastname());
		user.setPassword(source.getPassword());
		
		return user;
	}
	
	public List<User> convert(List<UserRegistrationDTO> source){
		List<User> users = new ArrayList<>();
		for (UserRegistrationDTO dto : source) {
			users.add(convert(dto));
		}
		
		return users;
	}

}
