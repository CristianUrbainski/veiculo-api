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

import com.urbainski.veiculoapi.annotation.ApiPageable;
import com.urbainski.veiculoapi.model.Modelo;
import com.urbainski.veiculoapi.service.ModeloService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@RestController
@RequestMapping("/modelo")
@Api(value = "Serviço de modelo")
public class ModeloResource {

	@Autowired
	private ModeloService modeloService;
	
	@GetMapping
	@ApiPageable
	@ApiOperation(value = "pesquisar todas os modelos com paginação")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados pesquisados do modelo paginados conforme solicitado")
	})
	public Page<Modelo> findAll(@ApiIgnore Pageable pageable) {
		return modeloService.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "pesquisar um modelo pelo seu identificador")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados do modelo"),
		@ApiResponse(code = 404, message = "caso não seja encontrada um modelo pelo identificador fornecido")
	})
	public ResponseEntity<Modelo> findOne(
			@ApiParam(name = "id", example =  "1",  value = "identificador do modelo") @PathVariable Long id) {
		Modelo modelo = modeloService.findOne(id);
		if (modelo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(modelo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "salvar um novo modelo")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "caso o modelo seja gravado"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Modelo> save(
			@ApiParam(name = "modelo", value = "dados a serem salvos") @Valid @RequestBody Modelo modelo) {
		Modelo modeloSaved = modeloService.save(modelo);
		return ResponseEntity.status(HttpStatus.CREATED).body(modeloSaved);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "atualizar um modelo pelo seu identificador")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "caso o modelo seja atualizado"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Modelo> update(
			@ApiParam(name = "id", example = "1", value = "identificador do modelo") @PathVariable Long id, 
			@ApiParam(name = "modelo", value = "dados a serem salvos") @Valid @RequestBody Modelo modelo) {
		Modelo modeloSaved = modeloService.update(id, modelo);
		return ResponseEntity.ok(modeloSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "remover um modelo pelo seu identificador")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "caso o modelo seja excluído com sucesso"),
			@ApiResponse(code = 404, message = "caso não seja encontrada um modelo com o identificador fornecido") })
	public void delete(
			@ApiParam(name = "id", example = "1", value = "identificador do modelo") @PathVariable Long id) {
		modeloService.delete(id);
	}
	
}