package co.edu.unbosque.bogotacup.repository;

import co.edu.unbosque.bogotacup.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    List<Partido> findByFecha(LocalDate fecha);
    List<Partido> findByEstadioPartido(String estadio);

}