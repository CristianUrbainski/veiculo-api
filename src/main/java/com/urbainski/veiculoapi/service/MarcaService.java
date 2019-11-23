package com.urbainski.veiculoapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urbainski.veiculoapi.model.Marca;
import com.urbainski.veiculoapi.repository.MarcaRepository;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repo;
	
	public Marca save(Marca marca) {
		return repo.save(marca);
	}

	public Marca update(Long id, Marca marca) {
		Marca marcaSaved = findById(id);
		BeanUtils.copyProperties(marca, marcaSaved, "id");
		
		return repo.save(marcaSaved);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Marca findOne(Long id) {
		Optional<Marca> marca = repo.findById(id);
		if (marca.isPresent()) {
			return marca.get();
		}
		return null;
	}
	
	public Marca findById(Long id) {
		Optional<Marca> marca = repo.findById(id);
		if (marca.isPresent()) {
			return marca.get();
		}
		throw new EmptyResultDataAccessException(1);
	}
	
	public Page<Marca> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
}