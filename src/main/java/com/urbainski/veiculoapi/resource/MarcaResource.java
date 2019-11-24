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
import com.urbainski.veiculoapi.model.Marca;
import com.urbainski.veiculoapi.service.MarcaService;

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
@RequestMapping("/marca")
@Api(value = "Serviço de marca")
public class MarcaResource {

	@Autowired
	private MarcaService marcaService;

	@GetMapping
	@ApiPageable
	@ApiOperation(value = "pesquisar todas as marcas com paginação")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados pesquisados da marca paginados conforme solicitado")
	})
	public Page<Marca> findAll(@ApiIgnore Pageable pageable) {
		return marcaService.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "pesquisar uma marca pelo seu identificador")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorna os dados da marca"),
		@ApiResponse(code = 404, message = "caso não seja encontrada uma marca pelo identificador fornecido")
	})
	public ResponseEntity<Marca> findOne(
			@ApiParam(name = "id", example =  "1",  value = "identificador da marca") @PathVariable Long id) {
		Marca marca = marcaService.findOne(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(marca);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "salvar uma nova marca")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "caso a marca seja gravada"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Marca> save(
			@ApiParam(name = "marca", value = "dados a serem salvos") @Valid @RequestBody Marca marca) {
		Marca marcaSaved = marcaService.save(marca);
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaSaved);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "atualizar uma marca pelo seu identificador")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "caso a marca seja atualizada"),
			@ApiResponse(code = 400, message = "caso seja passado algum valor inválido no parâmetro") })
	public ResponseEntity<Marca> update(
			@ApiParam(name = "id", example = "1", value = "identificador da marca") @PathVariable Long id,
			@ApiParam(name = "marca", value = "dados a serem salvos") @Valid @RequestBody Marca marca) {
		Marca marcaSaved = marcaService.update(id, marca);
		return ResponseEntity.ok(marcaSaved);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "remover uma marca pelo seu identificador")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "caso a marca seja excluída com sucesso"),
			@ApiResponse(code = 404, message = "caso não seja encontrada uma marca com o identificador fornecido") })
	public void delete(
			@ApiParam(name = "id", example = "1", value = "identificador da marca") @PathVariable Long id) {
		marcaService.delete(id);
	}

}