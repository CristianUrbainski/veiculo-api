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
import com.urbainski.veiculoapi.model.Veiculo;
import com.urbainski.veiculoapi.service.VeiculoService;

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
@RequestMapping("/veiculo")
@Api(value = "Serviço do veículo")
public class VeiculoResource {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	@ApiPageable
	@ApiOperation(value = "pesquisar todas os veículos com paginação")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados pesquisados do veículo paginados conforme solicitado")
	})
	public Page<Veiculo> findAll(@ApiIgnore Pageable pageable) {
		return veiculoService.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "pesquisar um veículo pelo seu identificador")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados do veículo"),
		@ApiResponse(code = 404, message = "caso não seja encontrada um veículo pelo identificador fornecido")
	})
	public ResponseEntity<Veiculo> findOne(
			@ApiParam(name = "id", example =  "1",  value = "identificador do veículo") @PathVariable Long id) {
		Veiculo veiculo = veiculoService.findOne(id);
		if (veiculo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "salvar um novo veículo")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "caso o veículo seja gravado"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Veiculo> save(
			@ApiParam(name = "veiculo", value = "dados a serem salvos") @Valid @RequestBody Veiculo veiculo) {
		Veiculo veiculoSaved = veiculoService.save(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSaved);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "atualizar um veículo pelo seu identificador")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "caso o veículo seja atualizado"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Veiculo> update(
			@ApiParam(name = "id", example = "1", value = "identificador do veículo") @PathVariable Long id, 
			@ApiParam(name = "veiculo", value = "dados a serem salvos") @Valid @RequestBody Veiculo veiculo) {
		Veiculo veiculoSaved = veiculoService.update(id, veiculo);
		return ResponseEntity.ok(veiculoSaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "remover um veículo pelo seu identificador")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "caso o veículo seja excluído com sucesso"),
			@ApiResponse(code = 404, message = "caso não seja encontrada um veículo com o identificador fornecido") })
	public void delete(
			@ApiParam(name = "id", example = "1", value = "identificador do veículo") @PathVariable Long id) {
		veiculoService.delete(id);
	}
}