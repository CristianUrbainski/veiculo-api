package com.urbainski.veiculoapi.annotation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/springfox/springfox/issues/2623
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
	@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0", example = "0", value = "Número da página que se deseja recuperar do servidor."),
	@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "20", example = "20", value = "Número de registros que se deseja recuperar."),
	@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Definição da ordem dos registros.") 
})
public @interface ApiPageable {
}