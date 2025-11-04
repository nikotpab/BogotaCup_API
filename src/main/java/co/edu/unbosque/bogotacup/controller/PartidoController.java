package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.PartidoDTO;
import co.edu.unbosque.bogotacup.service.PartidoService;

@RestController
@RequestMapping("/partido")
@CrossOrigin(origins = { "*" })
public class PartidoController {

	@Autowired
	private PartidoService partidoService;

	@GetMapping("/contar")
	public ResponseEntity<Long> contar() {
		return new ResponseEntity<>(partidoService.count(), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestBody PartidoDTO partidoDTO) {
		int estado = partidoService.create(partidoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Partido creado con éxito", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Error al crear el partido", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody PartidoDTO partidoDTO) {
		int estado = partidoService.update(id, partidoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Partido actualizado con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar el partido", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int status = partidoService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Partido eliminado con éxito", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Error al eliminar partido", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<PartidoDTO>> listarTodos() {
		List<PartidoDTO> partidos = partidoService.findAll();
		if (partidos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(partidos, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<PartidoDTO> buscarPorId(@PathVariable Integer id) {
		PartidoDTO partido = partidoService.findById(id);
		if (partido != null) {
			return new ResponseEntity<>(partido, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/fecha")
	public ResponseEntity<List<PartidoDTO>> buscarPorFecha(@RequestParam String fecha) {
		List<PartidoDTO> partidos = partidoService.findByFecha(java.time.LocalDate.parse(fecha));
		if (partidos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(partidos, HttpStatus.OK);
	}

	@GetMapping("/buscar/estadio")
	public ResponseEntity<List<PartidoDTO>> buscarPorEstadio(@RequestParam String estadio) {
		List<PartidoDTO> partidos = partidoService.findByEstadioPartido(estadio);
		if (partidos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(partidos, HttpStatus.OK);
	}

	@PostMapping("/{partidoId}/agregar-equipo/{equipoId}")
	public ResponseEntity<String> agregarEquipo(@PathVariable Integer partidoId, @PathVariable Integer equipoId) {
	    int estado = partidoService.agregarEquipo(partidoId, equipoId);
	    if (estado == 0) {
	        return new ResponseEntity<>("Equipo agregado al partido con éxito", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Error al agregar equipo al partido", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{partidoId}/remover-equipo/{equipoId}")
	public ResponseEntity<String> removerEquipo(@PathVariable Integer partidoId, @PathVariable Integer equipoId) {
	    int estado = partidoService.removerEquipo(partidoId, equipoId);
	    if (estado == 0) {
	        return new ResponseEntity<>("Equipo removido del partido con éxito", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Error al remover equipo del partido", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{partidoId}/equipos")
	public ResponseEntity<List<EquipoDTO>> obtenerEquiposDelPartido(@PathVariable Integer partidoId) {
	    List<EquipoDTO> equipos = partidoService.obtenerEquiposDelPartido(partidoId);
	    if (equipos.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(equipos, HttpStatus.OK);
	}
	
}
