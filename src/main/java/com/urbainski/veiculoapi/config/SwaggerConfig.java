package com.urbainski.veiculoapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuração do swagger, para documentação da api.
 * 
 * @author Cristian Urbainski
 * @since 23/11/2019
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		// @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(getPaths())
          .build()
          .apiInfo(getApiInfo())
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, getDefaultResponseMessageForHttpMethodGet());
     // @formatter:on
	}
	
	private List<ResponseMessage> getDefaultResponseMessageForHttpMethodGet() {
		List<ResponseMessage> list = new ArrayList<ResponseMessage>();
		list.add(new ResponseMessageBuilder().code(500).message("Error").build());
		list.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
		return list;
	}
	
	private Predicate<String> getPaths() {
		return Predicates.not(PathSelectors.regex("/error"));
	}

	private ApiInfo getApiInfo() {
		String description = "API para controlar informações dos veículos da empresa, "
				+ "e poder fazer uma previsão de gastos conforme distância a ser percorrida "
				+ "com base nas informações dew média dos veículos cadastrados.";
		// @formatter:off
		return new ApiInfoBuilder()
				.title("Veículo API")
				.description(description)
				.version("1.0.0")
				.license("MIT")
				.build();
		// @formatter:on
	}

}