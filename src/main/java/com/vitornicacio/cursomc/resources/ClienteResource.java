package com.vitornicacio.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitornicacio.cursomc.domain.Cliente;
import com.vitornicacio.cursomc.dto.ClienteDTO;
import com.vitornicacio.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

		@Autowired
		private ClienteService service;
		
		
		@RequestMapping(value="/{id}", method = RequestMethod.GET)
		public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
			
			Cliente obj = service.find(id);
			return ResponseEntity.ok().body(obj);	
		}
		

		@RequestMapping(value="/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO	 objDTO, @PathVariable Integer id){
			Cliente lCliente = service.fromDto(objDTO);
			lCliente.setId(id);
			lCliente = service.update(lCliente);
			return ResponseEntity.noContent().build();
		}
		@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			
			List<Cliente> lista = service.findAll();
			List<ClienteDTO> categorias = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			
			
			return ResponseEntity.ok().body(categorias);	
		}
		@RequestMapping(value="/page", method = RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findAllPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
			
			Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDTO> categorias = lista.map(obj -> new ClienteDTO(obj));
		
			return ResponseEntity.ok().body(categorias);	
		}
}
