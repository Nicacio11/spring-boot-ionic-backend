package com.vitornicacio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vitornicacio.cursomc.domain.Categoria;
import com.vitornicacio.cursomc.domain.Cidade;
import com.vitornicacio.cursomc.domain.Cliente;
import com.vitornicacio.cursomc.domain.Endereco;
import com.vitornicacio.cursomc.domain.Estado;
import com.vitornicacio.cursomc.domain.ItemPedido;
import com.vitornicacio.cursomc.domain.Pagamento;
import com.vitornicacio.cursomc.domain.PagamentoComBoleto;
import com.vitornicacio.cursomc.domain.PagamentoComCartao;
import com.vitornicacio.cursomc.domain.Pedido;
import com.vitornicacio.cursomc.domain.Produto;
import com.vitornicacio.cursomc.domain.enums.EstadoPagamento;
import com.vitornicacio.cursomc.domain.enums.TipoCliente;
import com.vitornicacio.cursomc.repositories.CategoriaRepository;
import com.vitornicacio.cursomc.repositories.CidadeRepository;
import com.vitornicacio.cursomc.repositories.ClienteRepository;
import com.vitornicacio.cursomc.repositories.EnderecoRepository;
import com.vitornicacio.cursomc.repositories.EstadoRepository;
import com.vitornicacio.cursomc.repositories.ItemPedidoRepository;
import com.vitornicacio.cursomc.repositories.PagamentoRepository;
import com.vitornicacio.cursomc.repositories.PedidoRepository;
import com.vitornicacio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
