package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.bogotacup.dto.TorneoDTO;
import co.edu.unbosque.bogotacup.service.TorneoService;

@RestController
@RequestMapping("/torneo")
@CrossOrigin(origins = { "*" })
public class TorneoController {

	@Autowired
	private TorneoService torneoService;

	@GetMapping("/contar")
	public ResponseEntity<Long> contar() {
		return new ResponseEntity<>(torneoService.count(), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestBody TorneoDTO torneoDTO) {
		int estado = torneoService.create(torneoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Torneo creado con éxito", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Error al crear el torneo", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody TorneoDTO torneoDTO) {
		int estado = torneoService.update(id, torneoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Torneo actualizado con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar el torneo", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int status = torneoService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Torneo eliminado con éxito", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Error al eliminar torneo", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<TorneoDTO>> listarTodos() {
		List<TorneoDTO> torneos = torneoService.findAll();
		if (torneos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(torneos, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<TorneoDTO> buscarPorId(@PathVariable Integer id) {
		TorneoDTO torneo = torneoService.findById(id);
		if (torneo != null) {
			return new ResponseEntity<>(torneo, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/nombre")
	public ResponseEntity<List<TorneoDTO>> buscarPorNombre(@RequestParam String nombre) {
		List<TorneoDTO> torneos = torneoService.findByNombre(nombre);
		if (torneos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(torneos, HttpStatus.OK);
	}

	@GetMapping("/buscar/estado")
	public ResponseEntity<List<TorneoDTO>> buscarPorEstado(@RequestParam Character estado) {
		List<TorneoDTO> torneos = torneoService.findByEstado(estado);
		if (torneos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(torneos, HttpStatus.OK);
	}

	@GetMapping("/buscar/anio")
	public ResponseEntity<List<TorneoDTO>> buscarPorAnio(@RequestParam Integer anio) {
		List<TorneoDTO> torneos = torneoService.findByAnio(anio);
		if (torneos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(torneos, HttpStatus.OK);
	}

}
