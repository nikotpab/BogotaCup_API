package co.edu.unbosque.bogotacup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.bogotacup.model.Arbitro;

public interface ArbitroRepository extends CrudRepository<Arbitro, Integer>{
	
	Optional<Arbitro> findById(Integer id);

	List<Arbitro> findByNombre(String nombre);

	List<Arbitro> findByApellido(String apellido);

}
