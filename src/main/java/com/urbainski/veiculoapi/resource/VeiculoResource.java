package com.urbainski.veiculoapi.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.urbainski.veiculoapi.model.Veiculo;
import com.urbainski.veiculoapi.service.VeiculoService;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@RestController
@RequestMapping("/veiculo")
public class VeiculoResource {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public Page<Veiculo> findAll(Pageable pageable) {
		return veiculoService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> findOne(@PathVariable Long id) {
		Veiculo veiculo = veiculoService.findOne(id);
		if (veiculo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculo);
	}
	
	@PostMapping
	public ResponseEntity<Veiculo> save(@Valid @RequestBody Veiculo veiculo) {
		Veiculo veiculoSaved = veiculoService.save(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> update(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
		Veiculo veiculoSaved = veiculoService.update(id, veiculo);
		return ResponseEntity.ok(veiculoSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		veiculoService.delete(id);
	}
}