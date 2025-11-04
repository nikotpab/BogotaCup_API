package co.edu.unbosque.bogotacup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.bogotacup.model.Equipo;

public interface EquipoRepository extends CrudRepository<Equipo, Integer>{
	
	Optional<Equipo> findById(Integer id);

	Optional<Equipo> findByNombre(String nombre);

	List<Equipo> findByDirectorTecnico(String directorTecnico);

}
