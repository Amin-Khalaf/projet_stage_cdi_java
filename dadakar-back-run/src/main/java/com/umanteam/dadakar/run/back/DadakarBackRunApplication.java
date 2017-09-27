package com.umanteam.dadakar.run.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.run.back.entities.Address;
import com.umanteam.dadakar.run.back.repository.AddressRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {

	@Autowired
	AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		populateAddresses();
	}
	
	private void populateAddresses(){
		addressRepository.deleteAll();
		Address address = new Address("Biscuiterie","Dakar");
		addressRepository.insert(address);
		address = new Address("Cambérène","Dakar");
		addressRepository.insert(address);
		address = new Address("Dieuppeul-Derklé","Dakar");
		addressRepository.insert(address);
		address = new Address("Fann-Point E-Amitié","Dakar");
		addressRepository.insert(address);
		address = new Address("Gueule Tapée-Fass-Colobane","Dakar");
		addressRepository.insert(address);
		address = new Address("Gorée","Dakar");
		addressRepository.insert(address);
		address = new Address("Grand Yoff","Dakar");
		addressRepository.insert(address);
		address = new Address("Grand Dakar","Dakar");
		addressRepository.insert(address);
		address = new Address("Hann Bel-Air","Dakar");
		addressRepository.insert(address);
		address = new Address("HLM","Dakar");
		addressRepository.insert(address);
		address = new Address("Médina","Dakar");
		addressRepository.insert(address);
		address = new Address("Mermoz-Sacré-Cœur","Dakar");
		addressRepository.insert(address);
		address = new Address("Ngor","Dakar");
		addressRepository.insert(address);
		address = new Address("Ouakam","Dakar");
		addressRepository.insert(address);
		address = new Address("Parcelles Assainies","Dakar");
		addressRepository.insert(address);
		address = new Address("Patte d'Oie","Dakar");
		addressRepository.insert(address);
		address = new Address("Dakar-Plateau","Dakar");
		addressRepository.insert(address);
		address = new Address("Sicap-Liberté","Dakar");
		addressRepository.insert(address);
		address = new Address("Yoff","Dakar");
		addressRepository.insert(address);
		address = new Address("Est","Pikine");
		addressRepository.insert(address);
		address = new Address("Nord","Pikine");
		addressRepository.insert(address);
		address = new Address("Ouest","Pikine");
		addressRepository.insert(address);
		address = new Address("Nord","Thies");
		addressRepository.insert(address);
		address = new Address("Sud","Thies");
		addressRepository.insert(address);
		address = new Address("Diassap","Diassap");
		addressRepository.insert(address);
	}
	
}
