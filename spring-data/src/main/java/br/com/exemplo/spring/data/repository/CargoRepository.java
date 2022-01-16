package br.com.exemplo.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.exemplo.spring.data.entities.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
	
}
