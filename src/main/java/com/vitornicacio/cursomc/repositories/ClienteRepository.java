package com.vitornicacio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vitornicacio.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{

	/*
	 * @Transaction readonly n precisa ser envolvida como uma transação de banco de dados
	 * deixa mais rapida e diminui o lock in no gerenciamento de banco de dados
	 */
	@Transactional(readOnly =true)
	Cliente findByEmail(String email);
}
