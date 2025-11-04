package co.edu.unbosque.bogotacup.service;

import co.edu.unbosque.bogotacup.dto.EquipoDTO;
import co.edu.unbosque.bogotacup.dto.UsuarioDTO;
import co.edu.unbosque.bogotacup.model.Equipo;
import co.edu.unbosque.bogotacup.model.Usuario;
import co.edu.unbosque.bogotacup.repository.EquipoRepository;
import co.edu.unbosque.bogotacup.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private UsuarioDTO mapToDTO(Usuario entity) {
        return modelMapper.map(entity, UsuarioDTO.class);
    }

    private EquipoDTO mapEquipoToDTO(Equipo entity) {
        return modelMapper.map(entity, EquipoDTO.class);
    }

    private Usuario mapToEntity(UsuarioDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }

    public long count() {
        return usuarioRepository.count();
    }

    public int create(UsuarioDTO usuarioDTO) {
        try {
            Usuario entity = mapToEntity(usuarioDTO);

            String claveHasheada = passwordEncoder.encode(usuarioDTO.getClave());
            entity.setClave(claveHasheada);

            usuarioRepository.save(entity);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public int update(Integer id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            try {
                Usuario usuario = existingUsuario.get();

                usuario.setNombre(usuarioDTO.getNombre());
                usuario.setApellido(usuarioDTO.getApellido());
                usuario.setCorreo(usuarioDTO.getCorreo());
                usuario.setRol(usuarioDTO.getRol());
                usuario.setNumeroCamiseta(usuarioDTO.getNumeroCamiseta());
                usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());

                if (usuarioDTO.getClave() != null && !usuarioDTO.getClave().isEmpty()) {
                    usuario.setClave(passwordEncoder.encode(usuarioDTO.getClave()));
                } else {
                    usuario.setClave(existingUsuario.get().getClave());
                }

                usuarioRepository.save(usuario);
                return 0;
            } catch (Exception e) {
                return 1;
            }
        }
        return 1;
    }

    @Transactional(readOnly = true)
    public boolean login(String email, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCorreo(email);

        if (optionalUsuario.isEmpty()) {
            return false;
        }

        Usuario usuario = optionalUsuario.get();
        return passwordEncoder.matches(password, usuario.getClave());
    }

    private String generarClaveTemporal() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public boolean handlePasswordRecovery(String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCorreo(email);

        if (optionalUsuario.isPresent()) {
            try {
                String claveTemporal = generarClaveTemporal();
                Usuario usuario = optionalUsuario.get();

                usuario.setClave(passwordEncoder.encode(claveTemporal));
                usuarioRepository.save(usuario);

                emailService.enviarCorreoRecuperacion(usuario.getCorreo(), claveTemporal);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public int deleteById(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return 0;
        }
        return 1;
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findById(Integer id) {
        return usuarioRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    public UsuarioDTO findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).map(this::mapToDTO).orElse(null);
    }

    public UsuarioDTO findByRol(String rol) {
        return usuarioRepository.findByRol(rol).map(this::mapToDTO).orElse(null);
    }

    @Transactional
    public int agregarEquipo(Integer usuarioId, Integer equipoId) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        Optional<Equipo> optionalEquipo = equipoRepository.findById(equipoId);

        if (optionalUsuario.isPresent() && optionalEquipo.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Equipo equipo = optionalEquipo.get();

            if (usuario.getEquipos().contains(equipo)) {
                return 1;
            }

            usuario.agregarEquipo(equipo);
            usuarioRepository.save(usuario);
            return 0;
        }
        return 1;
    }

    @Transactional
    public int removerEquipo(Integer usuarioId, Integer equipoId) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        Optional<Equipo> optionalEquipo = equipoRepository.findById(equipoId);

        if (optionalUsuario.isPresent() && optionalEquipo.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Equipo equipo = optionalEquipo.get();

            if (!usuario.getEquipos().contains(equipo)) {
                return 1;
            }

            usuario.removerEquipo(equipo);
            usuarioRepository.save(usuario);
            return 0;
        }
        return 1;
    }

    @Transactional(readOnly = true)
    public List<EquipoDTO> obtenerEquiposDelUsuario(Integer usuarioId) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return usuario.getEquipos().stream()
                    .map(this::mapEquipoToDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}

