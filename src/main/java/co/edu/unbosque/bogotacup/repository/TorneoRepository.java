package co.edu.unbosque.bogotacup.repository;

import co.edu.unbosque.bogotacup.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
    List<Torneo> findByNombre(String nombre);
    List<Torneo> findByEstado(Character estado);
    List<Torneo> findByAnio(Integer anio);
}