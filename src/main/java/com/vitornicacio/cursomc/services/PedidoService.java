package com.vitornicacio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitornicacio.cursomc.domain.Pedido;
import com.vitornicacio.cursomc.repositories.PedidoRepository;
import com.vitornicacio.cursomc.services.expections.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> lObjeto = repository.findById(id);
		
		return lObjeto.orElseThrow(
				()-> new ObjectNotFoundException(
						"Objeto não encontrado! id:"+id+", Tipo: "+ Pedido.class.getName())
				);
				
	}
}
