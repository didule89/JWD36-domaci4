package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Address;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.support.AddressToAddressDTO;
import jwd.wafepa.web.dto.AddressDTO;

@RestController
@RequestMapping(value="/api/addresses")
public class ApiAddressController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private AddressToAddressDTO toDto;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<AddressDTO>> getAddresses(){
		List<Address> addresses = addressService.findAll();
		if (addresses == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<AddressDTO>>(toDto.convert(addresses) ,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<AddressDTO> getAddress(@PathVariable Long id){
		Address address = addressService.findOne(id);
		if (address == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(address) ,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	ResponseEntity<AddressDTO> add(@RequestBody Address address){
		Address saved = addressService.save(address);
		if (saved == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(saved) ,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<AddressDTO> delete(@PathVariable Long id){
		Address deleted = addressService.findOne(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		addressService.delete(id);
		
		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT, consumes="application/json")
	ResponseEntity<AddressDTO> edit(@RequestBody Address address,
			@PathVariable Long id){
		
		if (!id.equals(address.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Address edited = addressService.save(address);
		
		return new ResponseEntity<>(toDto.convert(edited) ,HttpStatus.OK);
	}
}
