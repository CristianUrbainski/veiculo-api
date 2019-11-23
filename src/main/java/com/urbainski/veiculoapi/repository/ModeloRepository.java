package com.urbainski.veiculoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urbainski.veiculoapi.model.Modelo;

/**
 * 
 * @author Cristian Urbainski
 * @since 21/11/2019
 *
 */
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}