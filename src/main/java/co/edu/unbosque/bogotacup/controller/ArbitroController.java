package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.bogotacup.dto.ArbitroDTO;
import co.edu.unbosque.bogotacup.service.ArbitroService;

@RestController
@RequestMapping("/arbitro")
@CrossOrigin(origins = { "*" })
public class ArbitroController {

	@Autowired
	private ArbitroService arbitroService;

	@GetMapping("/contar")
	public ResponseEntity<Long> contar() {
		return new ResponseEntity<>(arbitroService.count(), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, @RequestParam String apellido) {
		ArbitroDTO nuevo = new ArbitroDTO(nombre, apellido);
		int estado = arbitroService.create(nuevo);
		if (estado == 0) {
			return new ResponseEntity<>("Árbitro creado con éxito", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Error al crear el árbitro", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestParam String nombre,
			@RequestParam String apellido) {
		ArbitroDTO arbitroActualizado = new ArbitroDTO(nombre, apellido);
		int estado = arbitroService.update(id, arbitroActualizado);
		if (estado == 0) {
			return new ResponseEntity<>("Árbitro actualizado con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar el árbitro", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int status = arbitroService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Árbitro eliminado con éxito", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Error al eliminar árbitro", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<ArbitroDTO>> listarTodos() {
		List<ArbitroDTO> arbitros = arbitroService.findAll();
		if (arbitros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(arbitros, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<ArbitroDTO> buscarPorId(@PathVariable Integer id) {
		ArbitroDTO arbitro = arbitroService.findById(id);
		if (arbitro != null) {
			return new ResponseEntity<>(arbitro, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/nombre")
	public ResponseEntity<List<ArbitroDTO>> buscarPorNombre(@RequestParam String nombre) {
		List<ArbitroDTO> arbitros = arbitroService.findByNombre(nombre);
		if (arbitros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(arbitros, HttpStatus.OK);
	}

	@GetMapping("/buscar/apellido")
	public ResponseEntity<List<ArbitroDTO>> buscarPorApellido(@RequestParam String apellido) {
		List<ArbitroDTO> arbitros = arbitroService.findByApellido(apellido);
		if (arbitros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(arbitros, HttpStatus.OK);
	}

}
