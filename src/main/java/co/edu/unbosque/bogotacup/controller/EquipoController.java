package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.PartidoDTO;
import co.edu.unbosque.bogotacup.dto.UsuarioDTO;
import co.edu.unbosque.bogotacup.service.EquipoService;

@RestController
@RequestMapping("/equipo")
@CrossOrigin(origins = { "*" })
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping("/contar")
	public ResponseEntity<Long> contar() {
		return new ResponseEntity<>(equipoService.count(), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crear(@RequestBody EquipoDTO equipoDTO) {
		int estado = equipoService.create(equipoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Equipo creado con éxito", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Error al crear el equipo", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody EquipoDTO equipoDTO) {
		int estado = equipoService.update(id, equipoDTO);
		if (estado == 0) {
			return new ResponseEntity<>("Equipo actualizado con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar el equipo", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int status = equipoService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Equipo eliminado con éxito", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Error al eliminar equipo", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<EquipoDTO>> listarTodos() {
		List<EquipoDTO> equipos = equipoService.findAll();
		if (equipos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(equipos, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<EquipoDTO> buscarPorId(@PathVariable Integer id) {
		EquipoDTO equipo = equipoService.findById(id);
		if (equipo != null) {
			return new ResponseEntity<>(equipo, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/nombre")
	public ResponseEntity<EquipoDTO> buscarPorNombre(@RequestParam String nombre) {
		EquipoDTO equipo = equipoService.findByNombre(nombre);
		if (equipo != null) {
			return new ResponseEntity<>(equipo, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/director")
	public ResponseEntity<List<EquipoDTO>> buscarPorDirector(@RequestParam String director) {
		List<EquipoDTO> equipos = equipoService.findByDirectorTecnico(director);
		if (equipos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(equipos, HttpStatus.OK);
	}

	// RELACIONES EQUIPO-USUARIO
	@PostMapping("/{equipoId}/agregar-usuario/{usuarioId}")
	public ResponseEntity<String> agregarUsuario(@PathVariable Integer equipoId, @PathVariable Integer usuarioId) {
		int estado = equipoService.agregarUsuario(equipoId, usuarioId);
		if (estado == 0) {
			return new ResponseEntity<>("Usuario agregado al equipo con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al agregar usuario al equipo", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{equipoId}/remover-usuario/{usuarioId}")
	public ResponseEntity<String> removerUsuario(@PathVariable Integer equipoId, @PathVariable Integer usuarioId) {
		int estado = equipoService.removerUsuario(equipoId, usuarioId);
		if (estado == 0) {
			return new ResponseEntity<>("Usuario removido del equipo con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al remover usuario del equipo", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{equipoId}/usuarios")
	public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosDelEquipo(@PathVariable Integer equipoId) {
		List<UsuarioDTO> usuarios = equipoService.obtenerUsuariosDelEquipo(equipoId);
		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@PostMapping("/{equipoId}/agregar-partido/{partidoId}")
	public ResponseEntity<String> agregarPartido(@PathVariable Integer equipoId, @PathVariable Integer partidoId) {
		int estado = equipoService.agregarPartido(equipoId, partidoId);
		if (estado == 0) {
			return new ResponseEntity<>("Partido agregado al equipo con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al agregar partido al equipo", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{equipoId}/remover-partido/{partidoId}")
	public ResponseEntity<String> removerPartido(@PathVariable Integer equipoId, @PathVariable Integer partidoId) {
		int estado = equipoService.removerPartido(equipoId, partidoId);
		if (estado == 0) {
			return new ResponseEntity<>("Partido removido del equipo con éxito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al remover partido del equipo", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{equipoId}/partidos")
	public ResponseEntity<List<PartidoDTO>> obtenerPartidosDelEquipo(@PathVariable Integer equipoId) {
		List<PartidoDTO> partidos = equipoService.obtenerPartidosDelEquipo(equipoId);
		if (partidos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(partidos, HttpStatus.OK);
	}

}
