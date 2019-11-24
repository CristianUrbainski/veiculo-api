package com.urbainski.veiculoapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urbainski.veiculoapi.dto.CalcularPrevisaoGastosViagemDTO;
import com.urbainski.veiculoapi.dto.RankVeiculoDTO;
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

	public List<RankVeiculoDTO> calcularPrevisaoGastos(CalcularPrevisaoGastosViagemDTO dto) {

		final List<RankVeiculoDTO> listaRetorno = new ArrayList<RankVeiculoDTO>();
		List<Veiculo> listaVeiculo = repo.findAll();

		BigDecimal precoLitroCombustivel = BigDecimal.valueOf(dto.getPrecoLitroCombustivel());
		BigDecimal totalKmCidade = BigDecimal.valueOf(dto.getTotalKmCidade());
		BigDecimal totalKmRodovia = BigDecimal.valueOf(dto.getTotalKmRodovia());

		listaVeiculo.parallelStream().forEach(veiculo -> {
			BigDecimal consumoMedioCidade = BigDecimal.valueOf(veiculo.getConsumoMedioCidade());
			BigDecimal consumoMedioRodovia = BigDecimal.valueOf(veiculo.getConsumoMedioRodovia());

			BigDecimal litrosCombustivelCidade = totalKmCidade.divide(consumoMedioCidade, 6, RoundingMode.HALF_UP);
			BigDecimal litrosCombustivelRodovia = totalKmRodovia.divide(consumoMedioRodovia, 6, RoundingMode.HALF_UP);

			BigDecimal totalLitrosCombustivel = litrosCombustivelCidade.add(litrosCombustivelRodovia);
			totalLitrosCombustivel.setScale(2, RoundingMode.HALF_UP);

			BigDecimal valorTotalCombustivel = totalLitrosCombustivel.multiply(precoLitroCombustivel);
			valorTotalCombustivel.setScale(2, RoundingMode.HALF_UP);

			RankVeiculoDTO rankVeiculoDto = RankVeiculoDTO.builder().idVeiculo(veiculo.getId()).nome(veiculo.getNome())
					.modelo(veiculo.getModelo()).anoFabricacao(veiculo.getAnoFabricacao())
					.totalLitrosCombustivel(totalLitrosCombustivel.doubleValue())
					.valorTotalCombustivel(valorTotalCombustivel.doubleValue()).build();
			listaRetorno.add(rankVeiculoDto);
		});

		return listaRetorno.parallelStream()
				.sorted((o1, o2) -> o1.getValorTotalCombustivel().compareTo(o2.getValorTotalCombustivel()))
				.collect(Collectors.toList());
	}

}