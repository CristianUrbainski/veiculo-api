package com.urbainski.veiculoapi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
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
@Table(name = "modelo")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	public Long id;
	
	@NotNull
	@Size(min = 2, max = 100)
	public String nome;
	
	@Valid
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmarca", referencedColumnName = "id")
	private Marca marca;
	
}