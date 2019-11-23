package com.urbainski.veiculoapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urbainski.veiculoapi.model.Modelo;
import com.urbainski.veiculoapi.repository.ModeloRepository;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@Service
public class ModeloService {

	@Autowired
	private ModeloRepository repo;

	public Modelo save(Modelo modelo) {
		return repo.save(modelo);
	}

	public Modelo update(Long id, Modelo modelo) {
		Modelo modeloSaved = findById(id);
		BeanUtils.copyProperties(modelo, modeloSaved, "id");
		
		return repo.save(modeloSaved);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Modelo findOne(Long id) {
		return repo.findById(id).get();
	}
	
	public Modelo findById(Long id) {
		Optional<Modelo> modelo = repo.findById(id);
		if (modelo.isPresent()) {
			return modelo.get();
		}
		throw new EmptyResultDataAccessException(1);
	}
	
	public Page<Modelo> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
}