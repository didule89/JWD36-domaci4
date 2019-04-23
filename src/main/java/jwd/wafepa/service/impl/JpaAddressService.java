package jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Address;
import jwd.wafepa.repository.AddressRepository;
import jwd.wafepa.service.AddressService;

@Service
public class JpaAddressService implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address delete(Long id) {
		Address address = addressRepository.findOne(id);
		if (address != null) {
			addressRepository.delete(id);
		}
		return address;
	}
	
//	@PostConstruct
	public void init() {
		Address address = new Address();
		Address address1 = new Address();
		
		address.setStreet("Staroiriski put");
		address.setNumber("85");
		
		address1.setStreet("Momcila Tapavice");
		address1.setNumber("36");
		
		addressRepository.save(address);
		addressRepository.save(address1);
	}

}
