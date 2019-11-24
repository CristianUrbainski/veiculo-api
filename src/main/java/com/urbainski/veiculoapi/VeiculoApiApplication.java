package com.urbainski.veiculoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Cristian Urbainski
 * @since 19/11/2019
 *
 */
@SpringBootApplication
public class VeiculoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeiculoApiApplication.class, args);
	}

	@Bean
	public ObjectMapper getMapper() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.mixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
		return builder.build();
	}

	/**
	 * Classe utilizada para adicionar um mixin no object mapper do sistema para que
	 * não serialização de objetos para json as propriedades do hibernate sejam
	 * ignoradas.
	 * 
	 * @author Cristian Urbainski
	 * @since 24/11/2019
	 *
	 */
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private abstract class IgnoreHibernatePropertiesInJackson {
	}

}