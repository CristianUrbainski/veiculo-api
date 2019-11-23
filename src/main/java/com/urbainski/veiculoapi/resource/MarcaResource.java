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

import com.urbainski.veiculoapi.model.Marca;
import com.urbainski.veiculoapi.service.MarcaService;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@RestController
@RequestMapping("/marca")
public class MarcaResource {

	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	public Page<Marca> findAll(Pageable pageable) {
		return marcaService.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> findOne(@PathVariable Long id) {
		Marca marca = marcaService.findOne(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(marca);
	}
	
	@PostMapping
	public ResponseEntity<Marca> save(@Valid @RequestBody Marca marca) {
		Marca marcaSaved = marcaService.save(marca);
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Marca> update(@PathVariable Long id, @Valid @RequestBody Marca marca) {
		Marca marcaSaved = marcaService.update(id, marca);
		return ResponseEntity.ok(marcaSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		marcaService.delete(id);
	}
	
}