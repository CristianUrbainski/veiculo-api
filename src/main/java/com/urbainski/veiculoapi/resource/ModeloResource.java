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

import com.urbainski.veiculoapi.model.Modelo;
import com.urbainski.veiculoapi.service.ModeloService;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@RestController
@RequestMapping("/modelo")
public class ModeloResource {

	@Autowired
	private ModeloService modeloService;
	
	@GetMapping
	public Page<Modelo> findAll(Pageable pageable) {
		return modeloService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Modelo> findOne(@PathVariable Long id) {
		Modelo modelo = modeloService.findOne(id);
		if (modelo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(modelo);
	}
	
	@PostMapping
	public ResponseEntity<Modelo> save(@Valid @RequestBody Modelo modelo) {
		Modelo modeloSaved = modeloService.save(modelo);
		return ResponseEntity.status(HttpStatus.CREATED).body(modeloSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Modelo> update(@PathVariable Long id, @Valid @RequestBody Modelo modelo) {
		Modelo modeloSaved = modeloService.update(id, modelo);
		return ResponseEntity.ok(modeloSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		modeloService.delete(id);
	}
	
}