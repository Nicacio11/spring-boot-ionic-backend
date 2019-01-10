package com.vitornicacio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Cliente;
import com.vitornicacio.cursomc.expections.ObjectNotFoundException;
import com.vitornicacio.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	
	public Cliente find(Integer id) throws ObjectNotFoundException{
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado! id:"+id+", Tipo: "+Cliente.class.getName()));
	}
}
