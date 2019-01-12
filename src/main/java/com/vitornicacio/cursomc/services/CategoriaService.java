package com.vitornicacio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Categoria;
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
	
	public Categoria update(Categoria pCategoria) {
		find(pCategoria.getId());
		return repo.save(pCategoria);
	}
	public void delete(Integer id) throws DataIntegrityException{
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
}
