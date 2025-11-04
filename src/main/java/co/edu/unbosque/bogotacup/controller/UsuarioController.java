package co.edu.unbosque.bogotacup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.UsuarioDTO;
import co.edu.unbosque.bogotacup.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    record LoginRequest(String email, String password) {}
    record ForgotPasswordRequest(String email) {}

    @GetMapping("/contar")
    public ResponseEntity<Long> contar() {
        return new ResponseEntity<>(usuarioService.count(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody UsuarioDTO usuarioDTO) {
        int estado = usuarioService.create(usuarioDTO);
        if (estado == 0) {
            return new ResponseEntity<>("Usuario creado con éxito", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error al crear el usuario", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        int estado = usuarioService.update(id, usuarioDTO);
        if (estado == 0) {
            return new ResponseEntity<>("Usuario actualizado con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int status = usuarioService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Usuario eliminado con éxito", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Error al eliminar usuario", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioDTO usuario = usuarioService.findById(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/correo")
    public ResponseEntity<UsuarioDTO> buscarPorCorreo(@RequestParam String correo) {
        UsuarioDTO usuario = usuarioService.findByCorreo(correo);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/rol")
    public ResponseEntity<UsuarioDTO> buscarPorRol(@RequestParam String rol) {
        UsuarioDTO usuario = usuarioService.findByRol(rol);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{usuarioId}/agregar-equipo/{equipoId}")
    public ResponseEntity<String> agregarEquipo(@PathVariable Integer usuarioId, @PathVariable Integer equipoId) {
        int estado = usuarioService.agregarEquipo(usuarioId, equipoId);
        if (estado == 0) {
            return new ResponseEntity<>("Equipo agregado al usuario con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al agregar equipo al usuario", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{usuarioId}/remover-equipo/{equipoId}")
    public ResponseEntity<String> removerEquipo(@PathVariable Integer usuarioId, @PathVariable Integer equipoId) {
        int estado = usuarioService.removerEquipo(usuarioId, equipoId);
        if (estado == 0) {
            return new ResponseEntity<>("Equipo removido del usuario con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al remover equipo del usuario", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{usuarioId}/equipos")
    public ResponseEntity<List<EquipoDTO>> obtenerEquiposDelUsuario(@PathVariable Integer usuarioId) {
        List<EquipoDTO> equipos = usuarioService.obtenerEquiposDelUsuario(usuarioId);
        if (equipos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        boolean loginExitoso = usuarioService.login(loginRequest.email(), loginRequest.password());

        if (loginExitoso) {
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/recuperar-clave")
    public ResponseEntity<String> recuperarClave(@RequestBody ForgotPasswordRequest request) {

        usuarioService.handlePasswordRecovery(request.email());

        return new ResponseEntity<>("Solicitud enviada. Si el correo existe, recibirás instrucciones.", HttpStatus.OK);
    }
}

