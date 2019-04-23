package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.UserRegistrationDTOToUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value="/api/users")
public class ApiUserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserToUserDTO toDto;
	@Autowired
	private UserRegistrationDTOToUser toUser;
	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="lastname", required=false) String lastname){
		List<User> users;
		if (name == null && lastname == null) {
			users = userService.findAll();
		}
		else if(name != null && lastname == null){
			users = userService.findByName(name);
		}
		else {
			users = userService.findByLastnameAndFirstname(lastname, name);
		}
		if(users == null || users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(
			@PathVariable Long id){
		User user = userService.findOne(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(user),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<UserDTO> delete(
			@PathVariable Long id){
		userService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<UserDTO> add(
			@RequestBody UserRegistrationDTO newUser){
		
		User savedUser;
		
		if (newUser.getPassword().equals(newUser.getPasswordValidate())) {
			User persisted = toUser.convert(newUser);
			savedUser = userService.save(persisted);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(savedUser), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<UserDTO> edit(
			@RequestBody UserRegistrationDTO user,
			@PathVariable Long id){
		
		if(id!=user.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User edited;
		
		if (user.getPassword().equals(user.getPasswordValidate())) {
			User persisted = toUser.convert(user);
			edited = userService.save(persisted);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(edited),HttpStatus.OK);
	}
	
	
	
}
