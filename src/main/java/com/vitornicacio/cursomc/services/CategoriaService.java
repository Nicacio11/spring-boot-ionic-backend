package com.vitornicacio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Categoria;
import com.vitornicacio.cursomc.expections.ObjectNotFoundException;
import com.vitornicacio.cursomc.repositories.CategoriaRepository;


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
}
