package com.vitornicacio.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitornicacio.cursomc.domain.ItemPedido;
import com.vitornicacio.cursomc.domain.PagamentoComBoleto;
import com.vitornicacio.cursomc.domain.Pedido;
import com.vitornicacio.cursomc.domain.enums.EstadoPagamento;
import com.vitornicacio.cursomc.repositories.ItemPedidoRepository;
import com.vitornicacio.cursomc.repositories.PagamentoRepository;
import com.vitornicacio.cursomc.repositories.PedidoRepository;
import com.vitornicacio.cursomc.services.expections.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	@Autowired 
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> lObjeto = repository.findById(id);
		
		return lObjeto.orElseThrow(
				()-> new ObjectNotFoundException(
						"Objeto não encontrado! id:"+id+", Tipo: "+ Pedido.class.getName())
				);
				
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComPoleto(pagto, pedido.getInstante());
		}
		pedido = repository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido ip: pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(pedido);
			ip.setProduto(ip.getProduto());
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}
}
