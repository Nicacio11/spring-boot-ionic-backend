package com.vitornicacio.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vitornicacio.cursomc.domain.Categoria;
import com.vitornicacio.cursomc.services.CategoriaService;

//anotação diretiva do springboot
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);	
	}
}
