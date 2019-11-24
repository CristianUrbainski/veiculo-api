package com.urbainski.veiculoapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Cristian Urbainski
 * @since 24/11/2019
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class CalcularPrevisaoGastosViagemDTO {

	private Double precoLitroCombustivel;
	
	private Double totalKmCidade;
	
	private Double totalKmRodovia;
	
}