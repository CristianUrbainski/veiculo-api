package com.urbainski.veiculoapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urbainski.veiculoapi.model.Veiculo;
import com.urbainski.veiculoapi.repository.VeiculoRepository;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repo;
	
	public Veiculo save(Veiculo veiculo) {
		return repo.save(veiculo);
	}

	public Veiculo update(Long id, Veiculo veiculo) {
		Veiculo veiculoSaved = findById(id);
		BeanUtils.copyProperties(veiculo, veiculoSaved, "id");
		
		return repo.save(veiculoSaved);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Veiculo findOne(Long id) {
		return repo.findById(id).get();
	}
	
	public Veiculo findById(Long id) {
		Optional<Veiculo> veiculo = repo.findById(id);
		if (veiculo.isPresent()) {
			return veiculo.get();
		}
		throw new EmptyResultDataAccessException(1);
	}
	
	public Page<Veiculo> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
}