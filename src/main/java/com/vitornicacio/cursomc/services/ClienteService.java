package com.vitornicacio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Cliente;
import com.vitornicacio.cursomc.dto.ClienteDTO;
import com.vitornicacio.cursomc.repositories.ClienteRepository;
import com.vitornicacio.cursomc.services.expections.DataIntegrityException;
import com.vitornicacio.cursomc.services.expections.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	
	public Cliente find(Integer id) throws ObjectNotFoundException{
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado! id:"+id+", Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente update(Cliente pCliente) {
		Cliente newObj = find(pCliente.getId());
		updateData(newObj, pCliente);
		return repo.save(newObj);
	}
	public void delete(Integer id) throws DataIntegrityException{
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possivel excluir por que há entidades relacionadas");
		}
		
	}
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
