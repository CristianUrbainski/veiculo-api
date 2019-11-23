package com.urbainski.veiculoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Cristian Urbainski
 * @since 20/11/2019
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	public Long id;
	
	@NotNull
	@Size(min = 2, max = 100)
	private String nome;
	
	@Valid
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmodelo", referencedColumnName = "id")
	private Modelo modelo;
	
	@NotNull
	@Min(value = 1950)
	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;
	
	@NotNull
	@DecimalMin(value = "0.01")
	@Column(name = "consume_medio_cidade")
	private Double consumoMedioCidade;
	
	@NotNull
	@DecimalMin(value = "0.01")
	@Column(name = "consumo_medio_rodovia")
	private Double consumoMedioRodovia;
	
}