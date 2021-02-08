package com.vitornicacio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Categoria;
import com.vitornicacio.cursomc.domain.Cliente;
import com.vitornicacio.cursomc.dto.CategoriaDTO;
import com.vitornicacio.cursomc.repositories.CategoriaRepository;
import com.vitornicacio.cursomc.services.expections.DataIntegrityException;
import com.vitornicacio.cursomc.services.expections.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id:"+id+", Tipo: "+Categoria.class.getName()));
		//return obj.orElse(null);
	}
	
	public Categoria insert(Categoria pCategoria) {
		pCategoria.setId(null);
		return repo.save(pCategoria);
	}
	
	public Categoria update(Categoria categoria) {
		Categoria newObj = find(categoria.getId());
		updateData(newObj, categoria);
		return repo.save(newObj);
	}
	public void delete(Integer id) throws DataIntegrityException{
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
