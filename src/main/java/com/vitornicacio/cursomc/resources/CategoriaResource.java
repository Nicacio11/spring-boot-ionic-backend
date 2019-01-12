package com.vitornicacio.cursomc.resources;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vitornicacio.cursomc.domain.Categoria;
import com.vitornicacio.cursomc.dto.CategoriaDTO;
import com.vitornicacio.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

//anotação diretiva do springboot
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> listar(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody Categoria pCategoria){
		pCategoria = service.insert(pCategoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pCategoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria pCategoria, @PathVariable Integer id){
		pCategoria.setId(id);
		pCategoria = service.update(pCategoria);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> lista = service.findAll();
		List<CategoriaDTO> categorias = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		//List<CategoriaDTO> teste = new ArrayList();
		//lista.forEach(obj -> teste.add(new CategoriaDTO(obj)));
		
		return ResponseEntity.ok().body(categorias);	
	}
}
