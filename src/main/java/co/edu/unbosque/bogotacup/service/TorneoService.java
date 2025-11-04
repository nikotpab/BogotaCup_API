package co.edu.unbosque.bogotacup.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.bogotacup.dto.TorneoDTO;
import co.edu.unbosque.bogotacup.model.Torneo;
import co.edu.unbosque.bogotacup.model.Usuario;
import co.edu.unbosque.bogotacup.repository.TorneoRepository;
import co.edu.unbosque.bogotacup.repository.UsuarioRepository;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public long count() {
        return torneoRepository.count();
    }

    public int create(TorneoDTO data) {
        try {
            Usuario usuario = usuarioRepository.findById(data.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + data.getIdUsuario()));

            Torneo entity = new Torneo();
            entity.setNombre(data.getNombre());
            entity.setTipo(data.getTipo());
            entity.setCategoria(data.getCategoria());
            entity.setAnio(data.getAnio());
            entity.setEstado(data.getEstado());
            entity.setUsuario(usuario);

            torneoRepository.save(entity);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public int update(Integer id, TorneoDTO torneoDTO) {
        Optional<Torneo> existingTorneo = torneoRepository.findById(id);
        if (existingTorneo.isPresent()) {
            try {
                Usuario usuario = usuarioRepository.findById(torneoDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + torneoDTO.getIdUsuario()));

                Torneo torneo = existingTorneo.get();
                torneo.setNombre(torneoDTO.getNombre());
                torneo.setTipo(torneoDTO.getTipo());
                torneo.setCategoria(torneoDTO.getCategoria());
                torneo.setAnio(torneoDTO.getAnio());
                torneo.setEstado(torneoDTO.getEstado());
                torneo.setUsuario(usuario);

                torneoRepository.save(torneo);
                return 0;
            } catch (Exception e) {
                return 1;
            }
        }
        return 1;
    }

    public int deleteById(Integer id) {
        Optional<Torneo> found = torneoRepository.findById(id);
        if (found.isPresent()) {
            torneoRepository.delete(found.get());
            return 0;
        }
        return 1;
    }

    private TorneoDTO mapToDTO(Torneo entity) {
        TorneoDTO dto = modelMapper.map(entity, TorneoDTO.class);
        if (entity.getUsuario() != null) {
            dto.setIdUsuario(entity.getUsuario().getIdUsuario());
        }
        return dto;
    }

    public List<TorneoDTO> findAll() {
        List<Torneo> entityList = torneoRepository.findAll();
        return entityList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TorneoDTO findById(Integer id) {
        Optional<Torneo> torneo = torneoRepository.findById(id);
        return torneo.map(this::mapToDTO).orElse(null);
    }

    public List<TorneoDTO> findByNombre(String nombre) {
        List<Torneo> torneos = torneoRepository.findByNombre(nombre);
        return torneos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<TorneoDTO> findByEstado(Character estado) {
        List<Torneo> torneos = torneoRepository.findByEstado(estado);
        return torneos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<TorneoDTO> findByAnio(Integer anio) {
        List<Torneo> torneos = torneoRepository.findByAnio(anio);
        return torneos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}