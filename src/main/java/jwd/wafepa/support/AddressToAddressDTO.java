package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Address;
import jwd.wafepa.web.dto.AddressDTO;

@Component
public class AddressToAddressDTO implements Converter<Address, AddressDTO>{

	@Override
	public AddressDTO convert(Address source) {
		if (source == null) {
			return null;
		}
		
		AddressDTO dto = new AddressDTO();
		
		dto.setId(source.getId());
		dto.setStreet(source.getStreet());
		dto.setNumber(source.getNumber());
		
		return dto;
	}
	
	public List<AddressDTO> convert(List<Address> addresses){
		List<AddressDTO> ret = new ArrayList<>();
		
		for(Address a: addresses){
			ret.add(convert(a));
		}
		
		return ret;
	}
	
	

}
