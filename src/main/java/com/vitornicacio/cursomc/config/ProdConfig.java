package com.vitornicacio.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vitornicacio.cursomc.services.DbService;

@Configuration
@Profile("prod")
public class ProdConfig {
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		return true;
	}
}
