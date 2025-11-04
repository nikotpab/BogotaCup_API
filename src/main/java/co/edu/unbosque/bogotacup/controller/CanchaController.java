package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.bogotacup.dto.CanchaDTO;
import co.edu.unbosque.bogotacup.service.CanchaService;

@RestController
@RequestMapping("/cancha")
@CrossOrigin(origins = { "*" })
public class CanchaController {

	@Autowired
	private CanchaService canchaService;

	@GetMapping("/contar")
	public ResponseEntity<Long> contar() {
		return new ResponseEntity<>(canchaService.count(), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, @RequestParam String direccion) {
		CanchaDTO nuevo = new CanchaDTO(direccion, nombre);
		int estado = canchaService.create(nuevo);
		if (estado == 0) {
			return new ResponseEntity<>("Cancha creada con éxito", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Error al crear la cancha", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestParam String nombre,
			@RequestParam String direccion) {
		CanchaDTO canchaActualizada = new CanchaDTO(direccion, nombre);
		int estado = canchaService.update(id, canchaActualizada);
		if (estado == 0) {
			return new ResponseEntity<>("Cancha actualizada con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar la cancha", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int status = canchaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Cancha eliminada con éxito", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Error al eliminar cancha", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<CanchaDTO>> listarTodos() {
		List<CanchaDTO> canchas = canchaService.findAll();
		if (canchas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(canchas, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<CanchaDTO> buscarPorId(@PathVariable Integer id) {
		CanchaDTO cancha = canchaService.findById(id);
		if (cancha != null) {
			return new ResponseEntity<>(cancha, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/nombre")
	public ResponseEntity<CanchaDTO> buscarPorNombre(@RequestParam String nombre) {
		CanchaDTO cancha = canchaService.findByNombre(nombre);
		if (cancha != null) {
			return new ResponseEntity<>(cancha, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/direccion")
	public ResponseEntity<List<CanchaDTO>> buscarPorDireccion(@RequestParam String direccion) {
		List<CanchaDTO> canchas = canchaService.findByDireccion(direccion);
		if (canchas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(canchas, HttpStatus.OK);
	}

}
