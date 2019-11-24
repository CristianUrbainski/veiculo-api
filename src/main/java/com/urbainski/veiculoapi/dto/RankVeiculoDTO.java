package com.urbainski.veiculoapi.dto;

import com.urbainski.veiculoapi.model.Modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Cristian Urbainski
 * @since 24/11/2019
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class RankVeiculoDTO {

	@EqualsAndHashCode.Include
	private Long idVeiculo;
	
	private String nome;
	
	private Modelo modelo;
	
	private Integer anoFabricacao;
	
	private Double totalLitrosCombustivel;
	
	private Double valorTotalCombustivel;
	
}