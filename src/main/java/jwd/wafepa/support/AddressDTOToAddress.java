package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import jwd.wafepa.model.Address;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.web.dto.AddressDTO;

public class AddressDTOToAddress implements Converter<AddressDTO, Address>{
	
	@Autowired
	private AddressService addressSevice;
	
	@Override
	public Address convert(AddressDTO source) {
		Address address = new Address();
		
		if(source.getId()!=null){
			address = addressSevice.findOne(source.getId());
			
			if(address == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant activity");
			}
		}
		
		address.setId(source.getId());
		address.setStreet(source.getStreet());
		address.setNumber(source.getNumber());
		
		return address;
	}
	
	public List<Address> convert (List<AddressDTO> addresses){
		List<Address> addresses1 = new ArrayList<>();
		
		for(AddressDTO dto : addresses){
			addresses1.add(convert(dto));
		}
		
		return addresses1;
	}

}
