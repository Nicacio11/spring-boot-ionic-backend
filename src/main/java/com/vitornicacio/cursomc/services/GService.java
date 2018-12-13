/*package com.vitornicacio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.repositories.IRepository;

//mudando a forma do curso
@Service
public class GService<T> {
	
	
	public IRepository<T> repository;
	
	
	public T buscar(Integer id) {
		Optional<T> obj = repository.findById(id);
		return obj.orElse(null);
	}
}*/
