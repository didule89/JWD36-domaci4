package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User source) {
		UserDTO dto = new UserDTO();
		if (source == null) {
			return null;
		}
		dto.setId(source.getId());
		dto.setFirstname(source.getFirstname());
		dto.setLastname(source.getLastname());
		dto.setEmail(source.getEmail());
		
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> dtos = new ArrayList<>();
		for (User u : users) {
			dtos.add(convert(u));
		}
		
		return dtos;
	}
	
	
}
