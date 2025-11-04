package co.edu.unbosque.bogotacup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.bogotacup.model.Cancha;

public interface CanchaRepository extends CrudRepository<Cancha, Integer>{
	
	Optional<Cancha> findById(Integer id);

	Optional<Cancha> findByNombre(String nombre);

	List<Cancha> findByDireccion(String direccion);

}
